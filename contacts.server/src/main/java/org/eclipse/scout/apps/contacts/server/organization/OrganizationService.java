package org.eclipse.scout.apps.contacts.server.organization;

import org.eclipse.scout.apps.contacts.server.sql.SQLs;
import org.eclipse.scout.apps.contacts.shared.organization.*;
import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.StringUtility;
import org.eclipse.scout.rt.security.ACCESS;
import org.eclipse.scout.rt.server.jdbc.SQL;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

import java.util.UUID;

public class OrganizationService implements IOrganizationService {
  @Override //loads data into the organization table page view, triggerd by OrganizationTablePage.execloadData()
  public OrganizationTablePageData getOrganizationTableData(SearchFilter filter) {
    OrganizationTablePageData pageData = new OrganizationTablePageData();
    String sql = SQLs.ORGANIZATION_PAGE_SELECT + SQLs.ORGANIZATION_PAGE_DATA_SELECT_INTO; // <1>
    SQL.selectInto(sql, new NVPair("page", pageData)); // <2>

    return pageData;
  }

  @Override
  public OrganizationFormData create(OrganizationFormData formData) {
    if (!ACCESS.check(new CreateOrganizationPermission())) {
      throw new VetoException(TEXTS.get("InsufficientPrivileges"));
    }

    if (StringUtility.isNullOrEmpty(formData.getOrganizationId())) {
      formData.setOrganizationId(UUID.randomUUID().toString());
    }

    SQL.insert(SQLs.ORGANIZATION_INSERT, formData);

    return store(formData);
  }

  //loads data into organization form!!!!
  // triggerd by OrganizationForm.ModifyHandler.execLoad()
  @Override
  public OrganizationFormData load(OrganizationFormData formData) {
    if (!ACCESS.check(new ReadOrganizationPermission())) {
      throw new VetoException(TEXTS.get("InsufficientPrivileges"));
    }

    SQL.selectInto(SQLs.ORGANIZATION_SELECT, formData);

    return formData;
  }

  @Override
  public OrganizationFormData store(OrganizationFormData formData) {
    if (!ACCESS.check(new UpdateOrganizationPermission())) {
      throw new VetoException(TEXTS.get("InsufficientPrivileges"));
    }

    SQL.update(SQLs.ORGANIZATION_UPDATE, formData);

    return formData;
  }
}
