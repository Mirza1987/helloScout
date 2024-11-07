package org.eclipse.scout.apps.contacts.client.common;

import org.eclipse.scout.rt.client.ui.form.fields.IFormField;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.sequencebox.AbstractSequenceBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.platform.text.TEXTS;

import java.util.AbstractSequentialList;

public class AbstractCreditCardBox extends AbstractSequenceBox {

  public GroupFourField getGroupFourField() {
    return getFieldByClass(GroupFourField.class);
  }

  public GroupTwoField getGroupTwoField() {
    return getFieldByClass(GroupTwoField.class);
  }

  public GroupOneField getGroupOneField() {
    return getFieldByClass(GroupOneField.class);
  }

  public GroupThreeField getGroupThreeField() {
    return getFieldByClass(GroupThreeField.class);
  }



  @Order(1000)
  @ClassId("6348828d-879b-4787-b880-161b756d397c")
  public class GroupOneField extends AbstractCreditCardField {

    @Override
    protected IFormField getNextField() {
      return getGroupTwoField();
    }

    @Override
    protected IFormField getPreviousField() {
      return null;
    }
  }


  @Order(2000)
  @ClassId("d51b4d3f-fc66-4a15-a607-c39fa6d673d7")
  public class GroupTwoField extends AbstractCreditCardField {
    @Override
    protected IFormField getNextField() {
      return getGroupThreeField();
    }

    @Override
    protected IFormField getPreviousField() {
      return getGroupOneField();
    }
  }

  @Order(3000)
  @ClassId("4c0051ea-a61c-4d0f-b3a1-04458b87c15f")
  public class GroupThreeField extends AbstractCreditCardField {
    @Override
    protected IFormField getNextField() {
      return getGroupFourField();
    }

    @Override
    protected IFormField getPreviousField() {
      return getGroupTwoField();
    }
  }

  @Order(4000)
  @ClassId("06b32dc9-9075-4ceb-877e-2d7b82a4e133")
  public class GroupFourField extends AbstractCreditCardField {
    @Override
    protected IFormField getNextField() {
      return null;
    }

    @Override
    protected IFormField getPreviousField() {
      return getGroupThreeField();
    }
  }
}
