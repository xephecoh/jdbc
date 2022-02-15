package org.khamutov.entities;

import java.util.ArrayList;
import java.util.List;

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
