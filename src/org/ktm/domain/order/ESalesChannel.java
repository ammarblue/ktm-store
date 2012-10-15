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
        return Enum.valueOf(ESalesChannel.class, value);
    }

    @Override
    public String toString() {
        return channel;
    }

}
