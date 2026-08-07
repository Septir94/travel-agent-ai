package com.travelai.travelai.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travelai.travelai.dto.ChatResponse;
import com.travelai.travelai.dto.TravelRecomendationRequest;
import com.travelai.travelai.dto.TravelResponse;
import com.travelai.travelai.services.TravelAgentService;


@RestController
public class ChatController {
    private final TravelAgentService travelAgentService;

    public ChatController(TravelAgentService travelAgentService) {
        this.travelAgentService = travelAgentService;
    }

    @GetMapping("/chat")
    public ChatResponse chat(
            @RequestParam(defaultValue = "Hello, Gemini. Briefly introduce yourself.") String message,
            @RequestParam String conversationId) {

        return travelAgentService.chat(message, conversationId);
    }

    @GetMapping("/travel")
    public TravelResponse travel(@RequestBody TravelRecomendationRequest request) {
        return travelAgentService.getRecommendation(request);
    }

    @GetMapping("/travel/list")
    public List<TravelResponse> travelList(@RequestBody TravelRecomendationRequest request) {
        return travelAgentService.getRecommendationList(request);
    }

}
