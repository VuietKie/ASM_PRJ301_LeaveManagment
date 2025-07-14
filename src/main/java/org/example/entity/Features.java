package org.example.entity;

public class Features {
    private int featureId;
    private String featureName;
    private String entrypoint;

    public Features() {}
    public Features(int featureId, String featureName, String entrypoint) {
        this.featureId = featureId;
        this.featureName = featureName;
        this.entrypoint = entrypoint;
    }
    public int getFeatureId() { return featureId; }
    public void setFeatureId(int featureId) { this.featureId = featureId; }
    public String getFeatureName() { return featureName; }
    public void setFeatureName(String featureName) { this.featureName = featureName; }
    public String getEntrypoint() { return entrypoint; }
    public void setEntrypoint(String entrypoint) { this.entrypoint = entrypoint; }
} 