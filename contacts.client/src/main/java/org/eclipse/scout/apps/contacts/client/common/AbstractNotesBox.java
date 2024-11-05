package org.eclipse.scout.apps.contacts.client.common;

import org.eclipse.scout.apps.contacts.shared.common.AbstractNotesBoxData;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.platform.text.TEXTS;

@FormData(value = AbstractNotesBoxData.class,
  sdkCommand = FormData.SdkCommand.CREATE,
  defaultSubtypeSdkCommand = FormData.DefaultSubtypeSdkCommand.CREATE)
public class AbstractNotesBox extends AbstractGroupBox {

  public NotesField getNotesField() {
    return getFieldByClass(NotesField.class);
  }

  @Override
  protected String getConfiguredLabel() {
    return TEXTS.get("Notes");
  }

  @Order(1000)
  @ClassId("2fb3311a-703b-41e4-b8ff-50ea91b72bbe")
  public class NotesField extends AbstractStringField {


    @Override
    protected int getConfiguredGridH() {
      return 4;
    }

    @Override
    protected boolean getConfiguredLabelVisible() {
      return false;
    }

    @Override
    protected boolean getConfiguredMultilineText() {
      return true;
    }
  }

}
