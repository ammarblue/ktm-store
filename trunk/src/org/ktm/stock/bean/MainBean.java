package org.ktm.stock.bean;

import java.util.Collection;

public class MainBean extends FormBean {

    private String page;
    private String pageContent;

    public String getPageContent() {
        return pageContent;
    }

    public void setPageContent(String pageContent) {
        this.pageContent = pageContent;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    @Override
    public void loadFormCollection(Collection<?> entitys) {

    }

}
