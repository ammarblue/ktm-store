package org.ktm.domain.order;

public enum ESalesChannel {
    INTERNET(1), TELEPHONE(2), MAIL(3), SHOP(4);
    
    private Integer id;
    
    private ESalesChannel(Integer id) {
        this.setId(id);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
