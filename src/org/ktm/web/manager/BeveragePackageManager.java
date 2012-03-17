package org.ktm.web.manager;

import java.util.List;
import org.ktm.web.form.FrmBeveragePackage;

public interface BeveragePackageManager extends FormManager {

    public Integer getProductCount(List<FrmBeveragePackage> myProductTypes);

    public FrmBeveragePackage findById(List<FrmBeveragePackage> myProductTypes, int id);

}
