package com.travelai.travelai.dto;

public class TravelRecomendationRequest {
    private String conversationId;
    private String destination;
    private String budget;
    private String duration;
    private String additionalPreferences;

    public TravelRecomendationRequest(String conversationId, String destination, String budget, String duration,
            String additionalPreferences) {
        this.conversationId = conversationId;
        this.destination = destination;
        this.budget = budget;
        this.duration = duration;
        this.additionalPreferences = additionalPreferences;
    }

    // Getters and setters
    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getAdditionalPreferences() {
        return additionalPreferences;
    }

    public void setAdditionalPreferences(String additionalPreferences) {
        this.additionalPreferences = additionalPreferences;
    }

    @Override
    public String toString() {
        return "TravelRecomendationRequest [conversationId=" + conversationId + ", destination=" + destination
                + ", budget=" + budget + ", duration=" + duration + ", additionalPreferences=" + additionalPreferences
                + "]";
    }

}
