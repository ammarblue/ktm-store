package org.ktm.domain.party;

public enum EAddressType {
    EMAIL("email"), TELEPHONE("phone"), FAX("fax"), GEOGRAPHICS("geographics"), WEBPAGE("web");

    private String type;

    private EAddressType(String type) {
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

    public EAddressType parse(String parseValue) {
        return Enum.valueOf(EAddressType.class, parseValue);
    }
}
