package org.eclipse.scout.apps.contacts.client.organization;

import org.eclipse.scout.apps.contacts.client.common.AbstractAddressBox;
import org.eclipse.scout.apps.contacts.client.common.AbstractNotesBox;
import org.eclipse.scout.apps.contacts.client.common.AbstractUrlImageField;
import org.eclipse.scout.apps.contacts.client.organization.OrganizationForm.MainBox.CancelButton;
import org.eclipse.scout.apps.contacts.client.organization.OrganizationForm.MainBox.OkButton;
import org.eclipse.scout.apps.contacts.shared.organization.CreateOrganizationPermission;
import org.eclipse.scout.apps.contacts.shared.organization.IOrganizationService;
import org.eclipse.scout.apps.contacts.shared.organization.OrganizationFormData;
import org.eclipse.scout.apps.contacts.shared.organization.UpdateOrganizationPermission;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.client.ui.form.fields.tabbox.AbstractTabBox;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.StringUtility;

@ClassId("7794d84a-85ed-42ed-9bee-bce4ccf3c3c6")
@FormData(value = OrganizationFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class OrganizationForm extends AbstractForm {
  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Organization");
  }

  @FormData
  public String getOrganizationId() {
    return organizationId;
  }

  @FormData
  public void setOrganizationId(String organizationId) {
    this.organizationId = organizationId;
  }

  private String organizationId;

  public MainBox.DetailsBox.ContactInfoBox.AddressBox getAddressBox() {
    return getFieldByClass(MainBox.DetailsBox.ContactInfoBox.AddressBox.class);
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  public MainBox.GeneralBox getGeneralBox() {
    return getFieldByClass(MainBox.GeneralBox.class);
  }

  public OkButton getOkButton() {
    return getFieldByClass(OkButton.class);
  }

  public CancelButton getCancelButton() {
    return getFieldByClass(CancelButton.class);
  }


  public MainBox.DetailsBox getDetailsBox() {
    return getFieldByClass(MainBox.DetailsBox.class);
  }

  public MainBox.GeneralBox.HomepageField getHomepageField() {
    return getFieldByClass(MainBox.GeneralBox.HomepageField.class);
  }

  public MainBox.GeneralBox.NameField getNameField() {
    return getFieldByClass(MainBox.GeneralBox.NameField.class);
  }

  public MainBox.DetailsBox.NotesBox getNotesBox() {
    return getFieldByClass(MainBox.DetailsBox.NotesBox.class);
  }

  @Order(1000)
  @ClassId("6f0b438a-d84e-40c6-b581-71df8da95973")
  public class MainBox extends AbstractGroupBox {
    @Order(1000)
    @ClassId("fda55f0f-22e7-4819-967e-487f9318da78")
    public class GeneralBox extends AbstractGroupBox {

      @Order(10)
      public class PictureField extends AbstractUrlImageField {
      }

      @Order(1000)
      @ClassId("e4929c43-fcd1-4c8f-ae40-cc96cb0830f9")
      public class NameField extends AbstractStringField {
        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Name");
        }

        private void execValidateValue() {
          if (!StringUtility.hasText(getNameField().getValue())) {
            throw new VetoException(TEXTS.get("Company must have a name!"));
          }
        }
      }

      @Order(2000)
      @ClassId("74c6fdaf-fce8-4e62-bff4-05cc5d8d1480")
      public class HomepageField extends AbstractStringField {
        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Homepage");
        }
      }
    }

    @Order(2000)
    @ClassId("a07baec9-f6a0-4fb3-a255-bb9abacbe425")
    public class DetailsBox extends AbstractTabBox {
      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Details");
      }

      @Order(1000)
      @ClassId("8f996b0b-b819-434d-bd7d-194237ca8ec3")
      public class ContactInfoBox extends AbstractGroupBox {
        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Contacts");
        }

        @Order(1000)
        @ClassId("436bd8eb-b75b-47f7-b97e-319a2820d0ba")
        public class AddressBox extends AbstractAddressBox {
        }

        @Order(2000)
        public class PhoneField extends AbstractStringField {
          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("Phone");
          }
        }

        @Order(2000)
        public class EmailField extends AbstractStringField {
          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("Email");
          }
        }
      }

      @Order(2000)
      @ClassId("d085b1f4-1ac1-45ef-a1ee-83875f3b02b8")
      public class NotesBox extends AbstractNotesBox {
      }
    }

    @Order(3000)
    @ClassId("86efdc8f-33c6-4ac1-82fe-16fa0b31b794")
    public class OkButton extends AbstractOkButton {

    }

    @Order(4000)
    @ClassId("4e53da7d-cd2f-4b88-a54d-bd83231fe8ae")
    public class CancelButton extends AbstractCancelButton {

    }
  }

  public void startModify() {
    startInternalExclusive(new ModifyHandler());
  }

  public void startNew() {
    startInternal(new NewHandler());
  }

  public class NewHandler extends AbstractFormHandler {
    @Override
    protected void execStore() {
      OrganizationFormData formData = new OrganizationFormData();
      exportFormData(formData);
      formData = BEANS.get(IOrganizationService.class).create(formData);
      importFormData(formData);
    }
    //@Override
    //protected void execDirtyStatusChanged(boolean dirty) {
    //getForm().setSubTitle(calculateSubTitle());
    //}
  }


  public class ModifyHandler extends AbstractFormHandler {
    @Override
    protected void execLoad() {
      OrganizationFormData formData = new OrganizationFormData();
      exportFormData(formData);
      formData = BEANS.get(IOrganizationService.class).load(formData);
      importFormData(formData);
      setEnabledPermission(new UpdateOrganizationPermission());

      getForm().setSubTitle(calculateSubTitle());
    }

    @Override
    protected void execStore() {
      OrganizationFormData formData = new OrganizationFormData();
      exportFormData(formData);
      BEANS.get(IOrganizationService.class).store(formData);
    }
  }

  private String calculateSubTitle() {
    return getNameField().getValue();
  }
}
