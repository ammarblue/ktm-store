package org.ktm.domain.order;

import org.ktm.exception.KTMException;

public enum ESalesChannel {
    INTERNET("channel.internet"), TELEPHONE("channel.telephone"), EMAIL("channel.email"), SHOP("channel.shop");

    private String channel;

    private ESalesChannel(String channel) {
        this.channel = channel;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public ESalesChannel parse(String value) throws KTMException {
        ESalesChannel result = null;
        if (value != null && !value.isEmpty()) {
            for (int i = 0; i < values().length; i++) {
                if (value.equals(values()[i].toString())) { return values()[i]; }
            }
        } else {
            throw new KTMException("ERR_can_t_parse_enum_value");
        }
        return result;
    }

    @Override
    public String toString() {
        return channel;
    }

}
