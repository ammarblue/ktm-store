package org.ktm.domain.order;

import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import org.ktm.domain.KTMEntity;

@Entity
@Table(name="Orders")
public class Order extends KTMEntity {

    private static final long        serialVersionUID = 1L;

    private Integer                  uniqueId;
    private Integer                  version;
    private OrderIdentifier          identifier;
    private Date                     dateCreated;
    private String                   termAndConditions;
    private PartySummary             partySummary;
    private ESalesChannel            salesChannel;
    private EPartySummaryRoleInOrder partySummaryRoleInOrder;
    private Set<OrderLine>           orderLines;

    @Id
    @GeneratedValue
    @Column(name = "uniqueId", nullable = false)
    public Integer getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(Integer uniqueId) {
        this.uniqueId = uniqueId;
    }

    @Version
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @OneToOne(cascade = CascadeType.ALL)
    public OrderIdentifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(OrderIdentifier identifier) {
        this.identifier = identifier;
    }

    @Column
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Column
    public String getTermAndConditions() {
        return termAndConditions;
    }

    public void setTermAndConditions(String termAndConditions) {
        this.termAndConditions = termAndConditions;
    }

    @OneToOne(cascade = CascadeType.ALL)
    public PartySummary getPartySummary() {
        return partySummary;
    }

    public void setPartySummary(PartySummary partySummary) {
        this.partySummary = partySummary;
    }

    @Enumerated(EnumType.STRING)
    public ESalesChannel getSalesChannel() {
        return salesChannel;
    }

    public void setSalesChannel(ESalesChannel salesChannel) {
        this.salesChannel = salesChannel;
    }

    @Enumerated(EnumType.STRING)
    public EPartySummaryRoleInOrder getPartySummaryRoleInOrder() {
        return partySummaryRoleInOrder;
    }

    public void setPartySummaryRoleInOrder(EPartySummaryRoleInOrder partySummaryRoleInOrder) {
        this.partySummaryRoleInOrder = partySummaryRoleInOrder;
    }

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    public Set<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(Set<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

}
