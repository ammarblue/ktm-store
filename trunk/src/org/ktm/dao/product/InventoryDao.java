package org.ktm.dao.product;

import java.util.List;
import java.util.Set;
import org.ktm.dao.Dao;
import org.ktm.domain.product.Inventory;
import org.ktm.domain.product.ProductIdentifier;
import org.ktm.domain.product.ProductType;

public interface InventoryDao extends Dao {

    /*
     * Adds a ProductType to the ProductCatalog by creating a unique
     * CatalogEntry for it
     */
    void addProductType(ProductType productType);

    /*
     * Adds a ProductType to the ProductCatalog by assigning it to an existing
     * CatalogEntry
     */
    void addProductType(ProductType productType, String catalogIdentifier);

    /*
     * Remove a ProductType from the ProductCatalog Removes the CatalogEntry
     * provided there are no more ProductTypes associated with that entry
     */
    void removeProductType(ProductIdentifier id);

    /*
     * Return a set of ProductTypes that have the given catalogIdentifier. Where
     * a business system only has one ProductType per CatalogEntry, this set
     * contains a single ProductType
     */
    ProductType findProductTypeByCatalogIdentifier(String catalogIdentifier);

    /*
     * Returns a single ProductType that has the given ProductIdentifier
     */
    ProductType findProductTypeByProductIdentifier(ProductIdentifier id);

    /*
     * Return a set of ProductType in the specified category
     */
    Set<ProductType> findProductTypeByCategory(String category);

    /*
     * Returns a set of ProductTypes--will return a single ProductType if every
     * ProductType has a different name (the usual case)
     */
    Set<ProductType> findProductTypeByName(String name);

    List<Inventory> findByInventoryType(EInventoryType type);

}
