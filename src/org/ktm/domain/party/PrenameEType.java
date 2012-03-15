package org.ktm.domain.party;

public enum PrenameEType {
    Mr("Mr"), Mrs("Mrs"), Miss("Miss");

    private String type;

    private PrenameEType(String type) {
        this.setType(type);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean equals(String type) {
        return this.type.equals(type);
    }

    public String toString() {
        return this.type;
    }
}
