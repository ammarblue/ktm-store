package org.ktm.web.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.ktm.dao.KTMEMDaoFactory;
import org.ktm.dao.party.OrganizationDao;
import org.ktm.dao.party.SupplierDao;
import org.ktm.domain.party.AddressEType;
import org.ktm.domain.party.AddressProperties;
import org.ktm.domain.party.GeographicAddress;
import org.ktm.domain.party.Organization;
import org.ktm.domain.party.PartyRoleIdentifier;
import org.ktm.domain.party.Supplier;
import org.ktm.domain.party.TelephoneAddress;
import org.ktm.exception.CreateException;
import org.ktm.exception.UpdateException;
import org.ktm.web.form.FrmDomain;
import org.ktm.web.form.FrmSupplier;

public class SupplierManagerImpl extends FrmManagerAbstractImpl implements SupplierManager {

    private static SupplierManagerImpl theInstance = null;

    public static SupplierManager getInstance() {
        if (theInstance == null) {
            theInstance = new SupplierManagerImpl();
        }
        return theInstance;
    }

    @Override
    public FrmDomain get(Serializable tryId) {
        FrmSupplier form = new FrmSupplier();
        SupplierDao dao = KTMEMDaoFactory.getInstance().getSupplierDao();
        Supplier sup = (Supplier) dao.get(tryId);
        if (sup != null) {
            syncToForm(sup, form);
        }
        return form;
    }

    @Override
    public List<?> findAll() {
        List<FrmDomain> result = new ArrayList<FrmDomain>();
        SupplierDao dao = KTMEMDaoFactory.getInstance().getSupplierDao();
        if (dao != null) {
            Collection<?> objs = dao.findAll();
            for (Object obj : objs) {
                if (obj instanceof Supplier) {
                    FrmSupplier form = new FrmSupplier();
                    syncToForm((Supplier) obj, form);
                    result.add(form);
                }
            }
        }
        return result;
    }

    private void syncToForm(Supplier obj, FrmSupplier form) {
        if (obj.getUniqueId() != null) {
            form.setId(obj.getUniqueId());
        }
        if (obj.getIdentifier() != null) {
            form.setIdentifier(obj.getIdentifier().getIdentifier());
        }
        if (obj.getDescription() != null) {
            form.setDesc(obj.getDescription());
        }
        if (obj.getPayMethod() != null) {
            form.setPayMethod(String.valueOf(obj.getPayMethod()));
        }
        if (obj.getPayDuration() != null) {
            form.setPayDuration(String.valueOf(obj.getPayDuration()));
        }
        if (obj.getMark() != null) {
            form.setMark(obj.getMark());
        }
        Organization org = (Organization) obj.getParty();
        if (org != null) {
            Set<AddressProperties> addrps = org.getAddresses();
            if (addrps != null) {
                for (AddressProperties addrp : addrps) {
                    if (AddressEType.GEOGRAPHICS.equals(addrp.getUseage())) {
                        GeographicAddress ga = (GeographicAddress) addrp.getAddress();
                        if (ga != null) {
                            form.setAddr1(ga.getAddressLine1());
                            form.setAddr2(ga.getAddressLine2());
                            form.setAddr3(ga.getAddressLine3());
                        }
                    } else if (AddressEType.TELEPHONE.equals(addrp.getUseage())) {
                        TelephoneAddress tel = (TelephoneAddress) addrp.getAddress();
                        if (tel != null) {
                            form.setTel(tel.getNumber());
                        }
                    } else if (AddressEType.FAX.equals(addrp.getUseage())) {
                        TelephoneAddress fax = (TelephoneAddress) addrp.getAddress();
                        if (fax != null) {
                            form.setFax(fax.getNumber());
                        }
                    }
                }
            }
        }
    }

    @Override
    public Integer delete(Serializable tryId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer delete(FrmDomain toDelete) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer addOrUpdate(FrmDomain toAdd) {
        Integer id = null;
        Supplier sup = null;
        if (toAdd != null) {
            FrmSupplier form = (FrmSupplier) toAdd;
            SupplierDao supplierDao = KTMEMDaoFactory.getInstance().getSupplierDao();
            OrganizationDao orgDao = KTMEMDaoFactory.getInstance().getOrganizationDao();

            if (toAdd != null) {
                if (toAdd.isNew()) {
                    sup = new Supplier();
                } else {
                    sup = (Supplier) supplierDao.get(toAdd.getId());
                }

                if (sup.getIdentifier() == null) {
                    sup.setIdentifier(new PartyRoleIdentifier());
                }
                sup.getIdentifier().setIdentifier(form.getIdentifier());
                sup.setDescription(form.getDesc());
                sup.setPayMethod(Integer.parseInt(form.getPayMethod()));
                sup.setPayDuration(Integer.parseInt(form.getPayDuration()));
                sup.setContactName(form.getContactName());
                sup.setMark(form.getMark());

                Organization org = (Organization) sup.getParty();
                if (org == null) {
                    org = new Organization();
                }
                Set<AddressProperties> addrps = org.getAddresses();
                if (addrps == null) {
                    org.setAddresses(new HashSet<AddressProperties>());
                    addrps = org.getAddresses();
                }
                if (addrps.size() <= 0) {
                    AddressProperties addrp = new AddressProperties();
                    addrp.setParty(org);
                    addrp.setUseage(AddressEType.GEOGRAPHICS);
                    GeographicAddress adds = new GeographicAddress();
                    addrp.setAddress(adds);
                    addrps.add(addrp);

                    addrp = new AddressProperties();
                    addrp.setParty(org);
                    addrp.setUseage(AddressEType.TELEPHONE);
                    TelephoneAddress tel = new TelephoneAddress();
                    addrp.setAddress(tel);
                    addrps.add(addrp);

                    addrp = new AddressProperties();
                    addrp.setParty(org);
                    addrp.setUseage(AddressEType.FAX);
                    TelephoneAddress fax = new TelephoneAddress();
                    addrp.setAddress(fax);
                    addrps.add(addrp);
                }

                // search for GeographicsAddress
                for (AddressProperties addrp : addrps) {
                    if (AddressEType.GEOGRAPHICS.equals(addrp.getUseage())) {
                        GeographicAddress ga = (GeographicAddress) addrp.getAddress();
                        ga.setAddressLine1(form.getAddr1());
                        ga.setAddressLine2(form.getAddr2());
                        ga.setAddressLine3(form.getAddr3());
                    } else if (AddressEType.TELEPHONE.equals(addrp.getUseage())) {
                        TelephoneAddress ta = (TelephoneAddress) addrp.getAddress();
                        ta.setNumber(form.getTel());
                    } else if (AddressEType.FAX.equals(addrp.getUseage())) {
                        TelephoneAddress fa = (TelephoneAddress) addrp.getAddress();
                        fa.setNumber(form.getFax());
                    }
                }

                try {
                    id = (Integer) supplierDao.create(sup);
                    sup = (Supplier) supplierDao.get(id);

                    Integer orgId = (Integer) orgDao.create(org);
                    org = (Organization) orgDao.get(orgId);

                    org.getRoles().add(sup);
                    sup.setParty(org);

                    orgDao.update(org);

                } catch (CreateException e) {
                    e.printStackTrace();
                } catch (UpdateException e) {
                    e.printStackTrace();
                }
            }
        }
        return id;
    }

}
