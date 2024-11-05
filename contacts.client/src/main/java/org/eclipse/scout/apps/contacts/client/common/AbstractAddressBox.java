package org.eclipse.scout.apps.contacts.client.common;

import org.eclipse.scout.apps.contacts.shared.common.AbstractAddressBoxData;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.sequencebox.AbstractSequenceBox;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.StringUtility;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

@FormData(value = AbstractAddressBoxData.class,
  sdkCommand = FormData.SdkCommand.CREATE,
  defaultSubtypeSdkCommand = FormData.DefaultSubtypeSdkCommand.CREATE)
public class AbstractAddressBox extends AbstractGroupBox {
  public LocationBox.CityField getCityField() {
    return getFieldByClass(LocationBox.CityField.class);
  }

  public LocationBox.CountryField getCountryField() {
    return getFieldByClass(LocationBox.CountryField.class);
  }

  public LocationBox getLocationBox() {
    return getFieldByClass(LocationBox.class);
  }

  public StreetField getStreetField() {
    return getFieldByClass(StreetField.class);
  }

  @Override
  protected boolean getConfiguredBorderVisible() {
    return false;
  }

  @Override
  protected int getConfiguredGridH() { //Makes the address box to occupy 1 column and 3 rows.
    return 3;
  }

  @Override
  protected int getConfiguredGridW() { //Makes the address box to occupy 1 column and 3 rows.
    return 1;
  }

  @Override
  protected int getConfiguredGridColumnCount() { //	The content in the address box will use a single column layout.
    return 1;
  }

  @Order(1000)
  @ClassId("f39dbed0-1bad-4c69-bc0b-f36b8cdaa595")
  public class StreetField extends AbstractStringField {
    @Override
    protected String getConfiguredLabel() {
      return TEXTS.get("Street");
    }

    @Override
    protected int getConfiguredMaxLength() {
      return 128;
    }
  }

  @Order(2000)
  @ClassId("30eb74ec-3301-4947-afe1-ef5f196fb879")
  public class LocationBox extends AbstractSequenceBox {

    @Override
    protected String getConfiguredLabel() {
      return TEXTS.get("Location");
    }

    @Override
    protected boolean getConfiguredAutoCheckFromTo() {
      return false;
    }

    @Order(1000)
    @ClassId("a1cd2d27-beea-486c-bc3d-77c3154a9e67")
    public class CityField extends AbstractStringField {
      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("City");
      }

      @Override
      protected int getConfiguredMaxLength() {
        return 128;
      }
    }

    @Order(2000)
    @ClassId("1d25647e-46aa-4bf8-9221-721bae2c86b1")
    public class CountryField extends AbstractSmartField<String> {
      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Country");
      }

      @Override
      protected void execChangedValue() {
        verifyAllFields();
      }

      @Override
      protected byte getConfiguredLabelPosition() {
        return LABEL_POSITION_ON_FIELD;
      }

      @Override
      protected Class<? extends ILookupCall<String>> getConfiguredLookupCall() {
        return CountryLookupCall.class;
      }
    }
  }

  protected void verifyAllFields() {
    boolean hasStreet = StringUtility.hasText(getStreetField().getValue());
    boolean hasCity = StringUtility.hasText(getCityField().getValue());

    getCityField().setMandatory(hasStreet);
    getCountryField().setMandatory(hasStreet || hasCity);
  }
  // tag::template[]

}
