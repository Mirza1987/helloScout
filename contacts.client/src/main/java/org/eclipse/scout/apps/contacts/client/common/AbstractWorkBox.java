package org.eclipse.scout.apps.contacts.client.common;

import org.eclipse.scout.apps.contacts.shared.common.AbstractWorkBoxData;
import org.eclipse.scout.apps.contacts.shared.organization.OrganizationLookupCall;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

@FormData(value = AbstractWorkBoxData.class,
  sdkCommand = FormData.SdkCommand.CREATE,
  defaultSubtypeSdkCommand = FormData.DefaultSubtypeSdkCommand.CREATE)
public class AbstractWorkBox extends AbstractGroupBox {

  private static final long serialVersionUID = 1L;

  protected String getConfiguredLabel() {
    return "Work";
  }

  public EmailWorkField getEmailWorkField() {
    return getFieldByClass(EmailWorkField.class);
  }

  public OrganizationField getOrganizationField() {
    return getFieldByClass(OrganizationField.class);
  }

  public PhoneWorkField getPhoneWorkField() {
    return getFieldByClass(PhoneWorkField.class);
  }

  public PositionField getPositionField() {
    return getFieldByClass(PositionField.class);
  }

  @Order(1000)
  @ClassId("fb9757d2-40a7-454b-9df3-ad50ccce37cf")
  public class PositionField extends AbstractStringField {
    @Override
    protected String getConfiguredLabel() {
      return TEXTS.get("Position");
    }
  }


  /*@Order(2000)
  @ClassId("8d1998ca-48d2-44d5-aeaf-8247558210bf")
  public class OrganizationField extends AbstractStringField {
    @Override
    protected String getConfiguredLabel() {
      return TEXTS.get("Organization");
    }
  }*/


  @Order(2000)
  @ClassId("b9a42a6f-7ac7-4055-bc3a-9eaae2ba22b7")
  public class OrganizationField extends AbstractSmartField<String> {
    @Override
    protected String getConfiguredLabel() {
      return TEXTS.get("Organization");
    }

    @Override
    protected Class<? extends ILookupCall<String>> getConfiguredLookupCall() {
      return OrganizationLookupCall.class;
    }
  }

  @Order(3000)
  @ClassId("0fdea723-63e0-4d20-8d6e-821b8404934f")
  public class PhoneWorkField extends AbstractStringField {
    @Override
    protected String getConfiguredLabel() {
      return TEXTS.get("PhoneWork");
    }
  }

  @Order(4000)
  @ClassId("3f79101c-c072-4096-8e55-d81be6a1e841")
  public class EmailWorkField extends AbstractStringField {
    @Override
    protected String getConfiguredLabel() {
      return TEXTS.get("EmailWork");
    }
  }


}
