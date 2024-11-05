package org.eclipse.scout.apps.contacts.client.person;

import org.eclipse.scout.apps.contacts.client.common.*;
import org.eclipse.scout.apps.contacts.shared.person.GenderCodeType;
import org.eclipse.scout.apps.contacts.shared.person.IPersonService;
import org.eclipse.scout.apps.contacts.shared.person.PersonFormData;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.IForm;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.datefield.AbstractDateField;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.radiobuttongroup.AbstractRadioButtonGroup;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.client.ui.form.fields.tabbox.AbstractTabBox;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.StringUtility;
import org.eclipse.scout.rt.shared.services.common.code.ICodeType;

@ClassId("19d38f73-5713-4a17-8ca6-548375f6cb82")
@FormData(value = PersonFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class PersonForm extends AbstractForm {

  private String personId;

  @FormData
  //The annotation @FormData on the getter and setter method define the personId as a property that will be included in the form data.
  public void setPersonId(String personId) {
    this.personId = personId;
  }

  @FormData
  public String getPersonId() {
    return personId;
  }

  @Override
  //The object returned by this method is used by the framework to verify if a specific entity is already opened in some other form.
  public Object computeExclusiveKey() {
    return getPersonId();
  }

  @Override
  //onfigure this form to be opened in the view mode. Views are opened in the bench area of the user interface.
  protected int getConfiguredDisplayHint() {
    return IForm.DISPLAY_HINT_VIEW;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Person");
  }

  /*public MainBox.DetailsBox.ContactInfoBox.AddressBox.StreetField getStreetField() {
    return getFieldByClass(MainBox.DetailsBox.ContactInfoBox.AddressBox.StreetField.class);
  }*/

  /*public MainBox.DetailsBox.ContactInfoBox.AddressBox.LocationBox.CityField getCityField() {
    return getFieldByClass(MainBox.DetailsBox.ContactInfoBox.AddressBox.LocationBox.CityField.class);
  }*/

  /*public MainBox.DetailsBox.ContactInfoBox.AddressBox.LocationBox.CountryField getCountryField() {
    return getFieldByClass(MainBox.DetailsBox.ContactInfoBox.AddressBox.LocationBox.CountryField.class);
  }*/

  public MainBox.GeneralBox.DateOfBirthField getDateOfBirthField() {
    return getFieldByClass(MainBox.GeneralBox.DateOfBirthField.class);
  }

  public MainBox.DetailsBox getDetailsBox() {
    return getFieldByClass(MainBox.DetailsBox.class);
  }

  public MainBox.DetailsBox.ContactInfoBox.EmailField getEmailField() {
    return getFieldByClass(MainBox.DetailsBox.ContactInfoBox.EmailField.class);
  }

  /*public MainBox.DetailsBox.WorkBox.EmailWorkField getEmailWorkField() {
    return getFieldByClass(MainBox.DetailsBox.WorkBox.EmailWorkField.class);
  }*/

  public MainBox.GeneralBox.FirstNameField getFirstNameField() {
    return getFieldByClass(MainBox.GeneralBox.FirstNameField.class);
  }

  public MainBox.GeneralBox.GenderGroup getGenderGroup() {
    return getFieldByClass(MainBox.GeneralBox.GenderGroup.class);
  }

  public MainBox.GeneralBox getGeneralBox() {
    return getFieldByClass(MainBox.GeneralBox.class);
  }

  public MainBox.GeneralBox.LastNameField getLastNameField() {
    return getFieldByClass(MainBox.GeneralBox.LastNameField.class);
  }

  /*public MainBox.DetailsBox.ContactInfoBox.AddressBox.LocationBox getLocationBox() {
    return getFieldByClass(MainBox.DetailsBox.ContactInfoBox.AddressBox.LocationBox.class);
  }*/

  public MainBox.DetailsBox.ContactInfoBox.MobileField getMobileField() {
    return getFieldByClass(MainBox.DetailsBox.ContactInfoBox.MobileField.class);
  }

  public MainBox.DetailsBox.WorkBox.OrganizationField getOrganizationField() {
    return getFieldByClass(MainBox.DetailsBox.WorkBox.OrganizationField.class);
  }

  public MainBox.DetailsBox.ContactInfoBox.PhoneField getPhoneField() {
    return getFieldByClass(MainBox.DetailsBox.ContactInfoBox.PhoneField.class);
  }

  public MainBox.DetailsBox.WorkBox.PhoneWorkField getPhoneWorkField() {
    return getFieldByClass(MainBox.DetailsBox.WorkBox.PhoneWorkField.class);
  }

  public MainBox.DetailsBox.WorkBox.PositionField getPositionField() {
    return getFieldByClass(MainBox.DetailsBox.WorkBox.PositionField.class);
  }

  @Override
  protected boolean execValidate() { //This method is called during the form validation and before the form is stored/closed.

    boolean noFirstName = StringUtility.isNullOrEmpty(getFirstNameField().getValue());
    boolean noLastname = StringUtility.isNullOrEmpty(getLastNameField().getValue());

    if (noFirstName && noLastname) {
      getFirstNameField().requestFocus(); // 	Place the focus on the first name field.
      throw new VetoException(TEXTS.get("MissingName")); // In case both the first name and the last name fields are empty throw a VetoException, this will fail the validation.
    }
    return true;
  }


  @Order(1000)
  @ClassId("33a196a5-db1a-4941-bd14-dc1906193007")
  public class MainBox extends AbstractGroupBox {

    @Order(1000)
    //The GeneralBox (GroupBox) will hold the picture field, first name and last names, the date of birth and the gender.
    @ClassId("51bc67e3-edd5-46ef-906a-98ffa2e6b3fd")
    public class GeneralBox extends AbstractGroupBox {
      @Order(10)
      @ClassId("6366a23e-f8ba-4b50-b814-202e63daffc8")
      public class PictureField extends AbstractUrlImageField {
      }

      @Order(2000)
      @ClassId("4c4833f8-f2b9-4c68-96aa-0652e8eb87f8")
      public class FirstNameField extends AbstractStringField {
        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("FirstName");
        }
      }

      @Order(3000)
      @ClassId("f99585a3-301d-4232-afe0-79bfa53fce1e")
      public class LastNameField extends AbstractStringField {
        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("LastName");
        }
      }

      @Order(4000)
      @ClassId("6599fc8d-1ded-458a-a691-7ec1696347f0")
      public class DateOfBirthField extends AbstractDateField {
        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("DateOfBirth");
        }
      }

      @Order(5000)
      @ClassId("0b52dc07-4f3c-4473-8cd1-8888919f15d6")
      public class GenderGroup extends AbstractRadioButtonGroup<String> {
        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Gender");
        }

        @Override
        //	The codes defined in GenderCodeType will be used to determine the actual radio buttons to add to the gender field.
        protected Class<? extends ICodeType<?, String>> getConfiguredCodeType() {
          return GenderCodeType.class;
        }
      }

    }

    @Order(1500) //The DetailsBox tab box will contain the various tabs implemented in inner group boxes.
    @ClassId("c85a229d-7e7e-4cda-8ca0-74d34eb45fe4")
    public class DetailsBox extends AbstractTabBox {
      @Order(10) //The containers ContactInfoBox, WorkBox and Notes represent the three tabs of the tab box.
      @ClassId("2081b483-3d6e-4239-b7da-b6e2d2aa3b7a")
      public class ContactInfoBox extends AbstractGroupBox {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("ContactInfo");
        }

        @Order(10)
        @ClassId("736450dd-ba89-43cd-ba52-bcd31196b462")
        public class AddressBox extends AbstractAddressBox {}

        @Order(5000)
        @ClassId("d6b64b33-d164-4239-a0fc-2db3e76ebbee")
        public class PhoneField extends AbstractStringField {
          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("Phone");
          }

          @Override
          protected int getConfiguredMaxLength() {
            return 128;
          }
        }

        @Order(6000)
        @ClassId("ee6240d8-73c4-4417-b3e0-86bd2aae8544")
        public class MobileField extends AbstractStringField {
          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("Mobile");
          }

          @Override
          protected int getConfiguredMaxLength() {
            return 128;
          }
        }

        @Order(7000)
        @ClassId("090d70d2-953b-4b79-b82c-b2be67f094d6")
        public class EmailField extends AbstractEmailField {
        }
      }

      @Order(20)
      @ClassId("8e18a673-aca5-44a2-898f-60a744e4467a")
      public class WorkBox extends AbstractWorkBox {}

      @ClassId("fcb5b155-2c89-4ef8-9a96-ac41e9032107")
      public class NotesBox extends AbstractNotesBox {}

    }

    @Order(3000)
    @ClassId("53ba679c-2f91-4042-bb39-083ec69ae2e7")
    public class OkButton extends AbstractOkButton {

    }

    @Order(4000)
    @ClassId("f3966a13-1b16-4392-9f30-adc17c76e5df")
    public class CancelButton extends AbstractCancelButton {

    }
  }

  public void startModify() {
    startInternalExclusive(new ModifyHandler());
  }

  public void startNew() {
    startInternal(new NewHandler());
  }

  //===============================================================
  //===============================================================
  //AND THIS IS HOW I TALK TO THE DATABASE (load, modify and store)
  //===============================================================
  //===============================================================
  public class ModifyHandler extends AbstractFormHandler {
    @Override
    protected void execLoad() {
      IPersonService service = BEANS.get(IPersonService.class); //Obtains a reference to the person service located on the Scout backend application.
      PersonFormData formData = new PersonFormData();
      // All form field values are transferred to the form data. In this case the person primary key property will be transferred to the form data.
      // Remember that we have set this key in the "Edit" context menu.
      exportFormData(formData);
      formData = service.load(formData); //The form data (including the person primary key) is sent to the load method. The load method returns the person data from the backend.
      importFormData(formData); //	The field values in the form data are loaded into the form fields of the person form.

      getForm().setSubTitle(calculateSubTitle()); //	The sub title on the view tab of the form is updated to reflect the name of the person.

    }

    @Override
    protected void execStore() {
      IPersonService service = BEANS.get(IPersonService.class);
      PersonFormData formData = new PersonFormData();
      exportFormData(formData);
      service.store(formData); // Calls the store method of the person service providing the updated person data.
    }
  }

  public class NewHandler extends AbstractFormHandler {
    @Override
    protected void execStore() {
      IPersonService service = BEANS.get(IPersonService.class);
      PersonFormData formData = new PersonFormData();
      exportFormData(formData);
      formData = service.create(formData); // Calls the create method of the person service providing the new person data.
      importFormData(formData);
    }
  }

  protected String calculateSubTitle() {
    return StringUtility.join(" ",
      getFirstNameField().getValue(),
      getLastNameField().getValue());
  }
}
