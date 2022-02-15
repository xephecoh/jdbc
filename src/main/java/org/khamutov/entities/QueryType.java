package org.khamutov.entities;


public enum QueryType {
    SELECT("SELECTED"),DELETE("deleted"),UPDATE("updated"),INSERT("inserted");

    final String participle;

    public String getParticiple() {
        return participle;
    }

    QueryType(String participle) {
        this.participle = participle;
    }

}
