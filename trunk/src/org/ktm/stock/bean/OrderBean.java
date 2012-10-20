package org.ktm.stock.bean;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;
import org.ktm.domain.KTMEntity;
import org.ktm.domain.order.EPartySummaryRoleInOrder;
import org.ktm.domain.order.ESalesChannel;
import org.ktm.domain.order.Order;
import org.ktm.domain.order.OrderLine;
import org.ktm.utils.DateUtils;
import org.ktm.web.bean.FormBean;

public abstract class OrderBean extends FormBean {

    private String                   identifier;
    private String                   dateCreated;
    private String                   termAndConditions;
    private PartySummaryBean         partySummary = new PartySummaryBean();
    private ESalesChannel            salesChannel;
    private EPartySummaryRoleInOrder partySummaryRoleInOrder;
    private Set<OrderLineBean>       orderLines   = new HashSet<OrderLineBean>(0);

    @Override
    public void loadToForm(KTMEntity entity) {
        if (entity != null) {
            if (entity instanceof Order) {
                super.loadToForm(entity);
                Order order = (Order) entity;

                this.getPartySummary().loadToForm(order);

                this.setIdentifier(order.getIdentifier() == null ? "" : order.getIdentifier().getIdentifier());
                try {
                    this.setDateCreated(DateUtils.formatDate(order.getDateCreated()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                this.setTermAndConditions(order.getTermAndConditions());
                this.setSalesChannel(order.getSalesChannel());
                this.setPartySummaryRoleInOrder(order.getPartySummaryRoleInOrder());

                this.loadOrderLineCollection(order.getOrderLines());
            }
        }
    }

    private void loadOrderLineCollection(Set<OrderLine> orderLines) {
        if (orderLines != null && orderLines.size() > 0) {
            Set<OrderLineBean> orderLinesBean = this.getOrderLines();
            if (orderLinesBean == null) {
                orderLinesBean = new HashSet<OrderLineBean>();
                this.setOrderLines(orderLinesBean);
            }

            for (OrderLine orderLine : orderLines) {
                OrderLineBean bean = new OrderLineBean();
                bean.loadToForm(orderLine);
                bean.setOrder(this);
                if (!orderLinesBean.contains(bean)) {
                    orderLinesBean.add(bean);
                }
            }
        }
    }

    @Override
    public void syncToEntity(KTMEntity entity) {

    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getTermAndConditions() {
        return termAndConditions;
    }

    public void setTermAndConditions(String termAndConditions) {
        this.termAndConditions = termAndConditions;
    }

    public PartySummaryBean getPartySummary() {
        return partySummary;
    }

    public void setPartySummary(PartySummaryBean partySummary) {
        this.partySummary = partySummary;
    }

    public ESalesChannel getSalesChannel() {
        return salesChannel;
    }

    public void setSalesChannel(ESalesChannel salesChannel) {
        this.salesChannel = salesChannel;
    }

    public EPartySummaryRoleInOrder getPartySummaryRoleInOrder() {
        return partySummaryRoleInOrder;
    }

    public void setPartySummaryRoleInOrder(EPartySummaryRoleInOrder partySummaryRoleInOrder) {
        this.partySummaryRoleInOrder = partySummaryRoleInOrder;
    }

    public Set<OrderLineBean> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(Set<OrderLineBean> orderLines) {
        this.orderLines = orderLines;
    }

}
