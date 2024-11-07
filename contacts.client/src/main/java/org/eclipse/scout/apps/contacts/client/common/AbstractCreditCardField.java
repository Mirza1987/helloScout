package org.eclipse.scout.apps.contacts.client.common;

import org.eclipse.scout.rt.client.ui.form.fields.IFormField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.StringUtility;

public abstract class AbstractCreditCardField extends AbstractStringField {

  @Override
  protected int getConfiguredMaxLength() {
    return 4;
  }

  @Override
  protected void execChangedDisplayText(){
    if(getDisplayText().length()==4){
      getForm().requestFocus(getNextField());
    }
    else if(getDisplayText().length()==0){
      getForm().requestFocus(getPreviousField());
    }
  }

  @Override
  protected String execValidateValue(String rawValue) {
    if (StringUtility.isNullOrEmpty(rawValue)) {
      return super.execValidateValue(rawValue);
    }
    try {
      Integer.parseInt(rawValue);
    }
    catch (NumberFormatException e) {
      throw new VetoException(TEXTS.get("InvalidNumberMessageX", rawValue));
    }
    return super.execValidateValue(rawValue);
  }

  protected abstract IFormField getNextField();

  protected abstract IFormField getPreviousField();
}
