package com.auraXP.aura.api;

public class GeminiResponse {
    private String description;
    private float confidence;

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setConfidence(float confidence) {
        this.confidence = confidence;
    }

    public float getConfidence() {
        return confidence;
    }
}