package org.ktm.stock.bean;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.ktm.domain.KTMEntity;
import org.ktm.domain.money.Money;
import org.ktm.domain.money.Price;
import org.ktm.domain.product.CatalogEntryType;
import org.ktm.domain.product.MeasuredProductType;
import org.ktm.domain.product.ProductIdentifier;
import org.ktm.utils.DateUtils;
import org.ktm.web.bean.FormBean;
import org.ktm.web.tags.Functions;

public class ProductTypeBean extends FormBean {

    private String                     name;
    private String                     description;
    private String                     identifier;
    private String                     unit;
    private Double                     quantity;
    private Double                     packAmount;
    private String                     newDatePrice;
    private String                     newDatePriceUser;
    private String                     catalogEntryTypeName;
    private String                     toDay;
    private Double                     costPrice;
    private Money                      price                      = new Money(0.0);

    private String                     selectedCatalogEntryType;

    private List<ProductTypeBean>      productTypeCollection      = new ArrayList<ProductTypeBean>(0);
    private List<CatalogEntryTypeBean> catalogEntryTypeCollection = new ArrayList<CatalogEntryTypeBean>(0);

    @Override
    public void reset() {
        super.reset();
        productTypeCollection.clear();
        catalogEntryTypeCollection.clear();
        unit = "";
        price.setAmount(0.0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPrice() {
        return String.valueOf(price.getAmount());
    }

    public void setPrice(String price) {
        this.price.setAmount(Double.parseDouble(price));
    }

    public String getUnitPrice() {
        return price.getUnitName();
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getPackAmount() {
        return packAmount;
    }

    public void setPackAmount(Double packAmount) {
        this.packAmount = packAmount;
    }

    public Double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    public String getNewDatePrice() {
        return newDatePrice;
    }

    public void setNewDatePrice(String newDatePrice) {
        this.newDatePrice = newDatePrice;
    }

    public String getNewDatePriceUser() {
        return newDatePriceUser;
    }

    public void setNewDatePriceUser(String newDatePriceUser) {
        this.newDatePriceUser = newDatePriceUser;
    }

    public String getCatalogEntryTypeName() {
        return catalogEntryTypeName;
    }

    public void setCatalogEntryTypeName(String catalogEntryTypeName) {
        this.catalogEntryTypeName = catalogEntryTypeName;
    }

    public String getToDay() {
        try {
            toDay = DateUtils.formatDate(new Date());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return toDay;
    }

    public void setToDay(String toDay) {
        this.toDay = toDay;
    }

    public List<ProductTypeBean> getProductTypeCollection() {
        return productTypeCollection;
    }

    public void setProductTypeCollection(List<ProductTypeBean> productTypeCollection) {
        this.productTypeCollection = productTypeCollection;
    }

    public List<CatalogEntryTypeBean> getCatalogEntryTypeCollection() {
        return catalogEntryTypeCollection;
    }

    public void setCatalogEntryTypeCollection(List<CatalogEntryTypeBean> catalogEntryTypeCollection) {
        this.catalogEntryTypeCollection = catalogEntryTypeCollection;
    }

    public String getSelectedCatalogEntryType() {
        return selectedCatalogEntryType;
    }

    public void setSelectedCatalogEntryType(String selectedCatalogEntryType) {
        this.selectedCatalogEntryType = selectedCatalogEntryType;
    }

    @Override
    public void loadFormCollection(Collection<?> entitys) {
        if (entitys != null) {
            for (Object entity : entitys) {
                if (entity instanceof MeasuredProductType) {
                    ProductTypeBean bean = new ProductTypeBean();
                    bean.loadToForm((MeasuredProductType) entity);
                    productTypeCollection.add(bean);
                }
            }
        }
    }

    public void loadToForm(MeasuredProductType ptype) {
        if (ptype != null) {
            if (ptype.getIdentifier() != null) {
                this.setIdentifier(ptype.getIdentifier().getIdentifier());
            }
            this.setUniqueId(String.valueOf(ptype.getUniqueId()));
            this.setName(ptype.getName());
            this.setDescription(ptype.getDescription());
            this.setUnit(ptype.getUnit() == null ? "" : ptype.getUnit());
            this.setQuantity(ptype.getQuantity() == null ? 0.0 : ptype.getQuantity());
            this.setPackAmount(ptype.getPackAmount() == null ? 0.0 : ptype.getPackAmount());
            this.setCostPrice(ptype.getCostPrice() == null ? 0.0 : ptype.getCostPrice());
            Set<Price> prices = ptype.getPrices();
            if (prices == null) {
                prices = new HashSet<Price>();
                ptype.setPrices(prices);
            }
            if (prices.size() > 0) {
                for (Price entityPrice : prices) {
                    if (entityPrice.getValidTo() == null) {
                        try {
                            this.setPrice(String.valueOf(entityPrice.getAmount().getAmount()));
                        } catch (NumberFormatException nef) {
                            this.setPrice("0.0");
                            nef.printStackTrace();
                        }
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void syncToEntity(KTMEntity entity) {
        if (entity != null && entity instanceof MeasuredProductType) {
            MeasuredProductType productType = (MeasuredProductType) entity;
            if (!this.getUniqueId().isEmpty()) {
                productType.setUniqueId(Integer.valueOf(this.getUniqueId()));
            }
            if (!this.getIdentifier().isEmpty()) {
                if (productType.getIdentifier() == null) {
                    ProductIdentifier id = new ProductIdentifier();
                    id.setIdentifier(this.getIdentifier());
                    productType.setIdentifier(id);
                } else {
                    productType.getIdentifier().setIdentifier(this.getIdentifier());
                }
            }
            productType.setName(this.getName());
            productType.setDescription(this.getDescription());
            productType.setUnit(this.getUnit());
            productType.setQuantity(this.getQuantity());
            productType.setPackAmount(this.getPackAmount());
            productType.setCostPrice(this.getCostPrice());

            if (!Functions.isEmpty(getNewDatePrice()) && !Functions.isEmpty(getNewDatePriceUser())) {
                Set<Price> prices = productType.getPrices();
                if (prices == null) {
                    prices = new HashSet<Price>();
                    productType.setPrices(prices);
                }
                Price pprice = null;
                for (Price p : prices) {
                    if (p.getValidTo() == null) {
                        pprice = p;
                        pprice.setValidTo(new Date());

                        Price newPrice = new Price();
                        newPrice.setProductType(productType);
                        newPrice.setValidFrom(new Date());
                        newPrice.setAutherName(getNewDatePriceUser());
                        prices.add(newPrice);
                        pprice = newPrice;
                        break;
                    }
                }
                if (pprice == null) {
                    pprice = new Price();
                    pprice.setValidFrom(new Date());
                    pprice.setProductType(productType);
                    prices.add(pprice);
                }

                Money money = pprice.getAmount();
                if (money == null) {
                    money = new Money();
                    pprice.setAmount(money);
                }
                try {
                    money.setAmount(Double.parseDouble(this.getPrice()));
                } catch (NumberFormatException nfe) {
                    money.setAmount(0.0);
                }
            }
        }
    }

    public void loadCatalogEntryTypeFormCollection(List<CatalogEntryType> cEntrys) {
        if (cEntrys != null) {
            for (CatalogEntryType cEntry : cEntrys) {
                CatalogEntryTypeBean bean = new CatalogEntryTypeBean();
                bean.loadToForm(cEntry);
                catalogEntryTypeCollection.add(bean);
            }
        }
    }

}
