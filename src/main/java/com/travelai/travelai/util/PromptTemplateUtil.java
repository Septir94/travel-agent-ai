package com.travelai.travelai.util;

public class PromptTemplateUtil {
    private PromptTemplateUtil() {
        /* This utility class should not be instantiated */
    }

    public static final String TRAVEL_AGENT_PROMPT_TEXT = """
                    A user wants to travel to {province} with a budget of Rp{budget} and duration of {duration} days.
                    Additional preferences from the user: {additionalPreferences}
                    (if empty, ignore this and give general recommendations)
                   
                    Your task:
                    1. Pick 2-5 specific destination within or near {province} that best
                       fits this budget (not the province/city name itself, but a specific
                       area or tourist spot — for example, if the province is West Java
                       and the area is Bandung, a specific destination could be "Ciwidey"
                       or "Lembang", not just "Bandung").
                    2. Provide the total estimated cost for this trip.
                    3. Provide a short description of why this destination fits the budget.
                    4. Provide practical travel tips.
                    5. Provide a list of 3-5 REAL and SPECIFIC activities/places at that
                       destination (actual named attractions, not generic categories like
                       "nature tourism" or "local cuisine"). and a "category" (one of: Nature, Culinary, Photo Spot, Adventure,Culture, Shopping).
                    """;
}
