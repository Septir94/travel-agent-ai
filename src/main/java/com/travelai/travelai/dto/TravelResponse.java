package com.travelai.travelai.dto;

import java.util.List;

public record TravelResponse(String destination, String estimatedCost, String description, String travelTips,
        List<TravelRecomendedActivities> recommendedActivities) {
}


