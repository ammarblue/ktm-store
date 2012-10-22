package org.ktm.stock.bean;

import java.text.ParseException;
import java.util.Collection;
import java.util.Set;
import org.ktm.domain.KTMEntity;
import org.ktm.domain.order.OrderLine;
import org.ktm.utils.DateUtils;
import org.ktm.web.bean.FormBean;

public class OrderLineBean extends FormBean {

    private String      orderIdentifier;
    private String      productTypeIdentifier;
    private Set<String> serialNumber;
    private String      description;
    private String      comment;
    private Integer     numberOrdered;
    private Double      money_amount;
    private String      money_metricName;
    private String      money_symbol;
    private String      money_definition;
    private String      expectedDeliveryDate;
    private OrderBean   order;

    @Override
    public void loadFormCollection(Collection<?> arg0) {

    }

    @Override
    public void loadToForm(KTMEntity entity) {
        if (entity != null && entity instanceof OrderLine) {
            OrderLine orderLine = (OrderLine) entity;
            super.loadToForm(orderLine);
            this.setOrderIdentifier(orderLine.getIdentifier() == null ? "" : orderLine.getIdentifier().getIdentifier());
            this.setProductTypeIdentifier(orderLine.getProductType() == null ? "" : orderLine.getProductType().getIdentifier());
            this.setDescription(orderLine.getDescription());
            this.setComment(orderLine.getComment());
            this.setNumberOrdered(orderLine.getNumberOrdered());
            this.setMoney_amount(orderLine.getUnitPrice() == null ? 0.0 : orderLine.getUnitPrice().getAmount());
            this.setMoney_metricName(orderLine.getUnitPrice() == null ? "" : orderLine.getUnitPrice().getMetric().getMetricName());
            this.setMoney_symbol(orderLine.getUnitPrice() == null ? "" : orderLine.getUnitPrice().getMetric().getSymbol());
            this.setMoney_definition(orderLine.getUnitPrice() == null ? "" : orderLine.getUnitPrice().getMetric().getDefinition());
            try {
                this.setExpectedDeliveryDate(DateUtils.formatDate(orderLine.getExpectedDeliveryDate()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public String getOrderIdentifier() {
        return orderIdentifier;
    }

    public void setOrderIdentifier(String orderIdentifier) {
        this.orderIdentifier = orderIdentifier;
    }

    public String getProductTypeIdentifier() {
        return productTypeIdentifier;
    }

    public void setProductTypeIdentifier(String productTypeIdentifier) {
        this.productTypeIdentifier = productTypeIdentifier;
    }

    public Set<String> getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Set<String> serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getNumberOrdered() {
        return numberOrdered;
    }

    public void setNumberOrdered(Integer numberOrdered) {
        this.numberOrdered = numberOrdered;
    }

    public Double getMoney_amount() {
        return money_amount;
    }

    public void setMoney_amount(Double money_amount) {
        this.money_amount = money_amount;
    }

    public String getMoney_metricName() {
        return money_metricName;
    }

    public void setMoney_metricName(String money_metricName) {
        this.money_metricName = money_metricName;
    }

    public String getMoney_symbol() {
        return money_symbol;
    }

    public void setMoney_symbol(String money_symbol) {
        this.money_symbol = money_symbol;
    }

    public String getMoney_definition() {
        return money_definition;
    }

    public void setMoney_definition(String money_definition) {
        this.money_definition = money_definition;
    }

    public String getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public void setExpectedDeliveryDate(String expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    public OrderBean getOrder() {
        return order;
    }

    public void setOrder(OrderBean order) {
        this.order = order;
    }

    public void mergeEntity(KTMEntity entity, KTMEntity orderLine) {
        // TODO Auto-generated method stub

    }

}
