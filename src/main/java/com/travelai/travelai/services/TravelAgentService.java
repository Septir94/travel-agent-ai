package com.travelai.travelai.services;

import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.travelai.travelai.dto.ChatResponse;
import com.travelai.travelai.dto.TravelRecomendationRequest;
import com.travelai.travelai.dto.TravelResponse;
import com.travelai.travelai.util.PromptTemplateUtil;

@Service
public class TravelAgentService {
    private final ChatClient chatClient;

    public TravelAgentService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public ChatResponse chat(
            @RequestParam(defaultValue = "Hello, Gemini. Briefly introduce yourself.") String message,
            @RequestParam String conversationId) {

        String reply = chatClient.prompt()
                .user(message)
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, conversationId))
                .call()
                .content();

        return new ChatResponse(message, reply);
    }

    public TravelResponse getRecommendation(TravelRecomendationRequest request) {
        return chatClient.prompt()
                .user(u -> u.text(PromptTemplateUtil.TRAVEL_AGENT_PROMPT_TEXT)
                        .param("province", request.getDestination())
                        .param("budget", request.getBudget())
                        .param("duration", request.getDuration())
                        .param("additionalPreferences", request.getAdditionalPreferences())
                        .param("conversationId", request.getConversationId()))
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, request.getConversationId()))
                .call()
                .entity(TravelResponse.class);
    }

    public List<TravelResponse> getRecommendationList(TravelRecomendationRequest request) {
        return chatClient.prompt()
                .user(u -> u.text(PromptTemplateUtil.TRAVEL_AGENT_PROMPT_TEXT)
                        .param("province", request.getDestination())
                        .param("budget", request.getBudget())
                        .param("duration", request.getDuration())
                        .param("additionalPreferences", request.getAdditionalPreferences())
                        .param("conversationId", request.getConversationId()))
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, request.getConversationId()))
                .call()
                .entity(new ParameterizedTypeReference<List<TravelResponse>>() {
                });
    }

}
