package com.travelai.travelai.config;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.BucketConfiguration;
import io.github.bucket4j.Refill;
import io.github.bucket4j.distributed.proxy.ProxyManager;
import io.github.bucket4j.redis.jedis.cas.JedisBasedProxyManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import redis.clients.jedis.JedisPool;

@Component
public class DailyRateLimitFilter extends OncePerRequestFilter {
    private final ProxyManager<byte[]> proxyManager;

    public DailyRateLimitFilter(JedisPool jedisPool) {
        this.proxyManager = JedisBasedProxyManager.builderFor(jedisPool)
                .build();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String clientIp = getClientIp(request);
        byte[] key = clientIp.getBytes(StandardCharsets.UTF_8); // ← convert String ke byte[]

        BucketConfiguration config = BucketConfiguration.builder()
                .addLimit(Bandwidth.classic(5, Refill.intervally(5, Duration.ofDays(1))))
                .build();

        var bucket = proxyManager.builder().build(key, () -> config); // ← pakai byte[] key

        if (bucket.tryConsume(1)) {
            filterChain.doFilter(request, response);
        } else {
            response.setStatus(429);
            response.setContentType("application/json");
            response.getWriter().write(
                    "{\"error\": \"Daily limit of 5 requests has been reached, please try again tomorrow\"}");
        }
    }

    private String getClientIp(HttpServletRequest request) {
        String xForwardedFor = request.getHeader("X-Forwarded-For");
        return xForwardedFor != null ? xForwardedFor.split(",")[0].trim() : request.getRemoteAddr();
    }

}
