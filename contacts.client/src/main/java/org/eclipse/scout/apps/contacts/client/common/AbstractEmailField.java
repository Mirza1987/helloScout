package org.eclipse.scout.apps.contacts.client.common;

import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.platform.text.TEXTS;

import java.util.regex.Pattern;


/*@FormData(value= AbstractEmailField.class, sdkCommand = FormData.SdkCommand.CREATE,
  defaultSubtypeSdkCommand = FormData.DefaultSubtypeSdkCommand.CREATE
)*/


public class AbstractEmailField extends AbstractStringField {

  private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

  @Override
  protected String execValidateValue(String rawValue) {
    if (rawValue != null && !Pattern.matches(EMAIL_PATTERN, rawValue)) {
      throw new VetoException(TEXTS.get("BadEmailAddress")); //	If the value violates any business rules, a VetoException should be thrown.
    }
    return rawValue;
  }

  @Override
  protected String getConfiguredLabel() {
    return TEXTS.get("Email");
  }

}
