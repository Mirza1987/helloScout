package org.eclipse.scout.apps.contacts.client.common;


import org.eclipse.scout.apps.contacts.client.Icons;
import org.eclipse.scout.apps.contacts.shared.common.PictureFormData;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.htmlfield.AbstractHtmlField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.platform.html.HTML;
import org.eclipse.scout.rt.platform.text.TEXTS;

@ClassId("86a7b65d-2f2d-4ce0-b454-1f4a60fc45a1")
@FormData(value = PictureFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class PictureUrlForm extends AbstractForm {
  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("PictureUrl");
  }

  public MainBox.InfoField getInfoField() {
    return getFieldByClass(MainBox.InfoField.class);
  }

  public MainBox.UrlBox.UrlField getUrlField() {
    return getFieldByClass(MainBox.UrlBox.UrlField.class);
  }

  @Order(1000)
  @ClassId("07141a60-65c2-4a50-aec2-ac0ff8442c18")
  public class MainBox extends AbstractGroupBox {

    @Order(1000)
    @ClassId("ac356e72-0696-49af-9440-ab2553895875")
    public class UrlBox extends AbstractGroupBox {

      @Order(1000)
      @ClassId("2e896c45-523c-49da-886b-fe713dd7e887")
      public class UrlField extends AbstractStringField {
        @Override
        protected boolean getConfiguredLabelVisible() {
          return false;
        }

        @Override
        protected boolean getConfiguredStatusVisible() {
          return false;
        }

        @Override
        protected int getConfiguredGridW() {
          return 2;
        }
      }
    }

    @Order(2000)
    @ClassId("544a70af-449b-4c87-8517-8b04157b999d")
    public class InfoField extends AbstractHtmlField {

      @Override
      protected boolean getConfiguredLabelVisible() {
        return false;
      }

      @Override
      protected boolean getConfiguredStatusVisible() {
        return false;
      }

      @Override
      protected int getConfiguredGridW() {
        return 2;
      }

      @Override
      protected boolean getConfiguredGridUseUiHeight() {
        return true;
      }

      @Override
      protected void execInitField() {
        setValue(HTML.fragment(HTML.icon(Icons.Info), HTML.bold(" " + TEXTS.get("PleaseNote") + ": "), TEXTS.get("SecurityUrlRestrictedMsg")).toHtml());
      }

    }

    @Order(3000)
    @ClassId("adc16124-8b0e-40fa-8d73-f97150fe3e84")
    public class OkButton extends AbstractOkButton {

    }

    @Order(4000)
    @ClassId("9b0d0a9f-b3ca-406c-a6a9-613b7b46b0a7")
    public class CancelButton extends AbstractCancelButton {

    }

  }

  public void startModify() {
    startInternalExclusive(new ModifyHandler());
  }

  public class ModifyHandler extends AbstractFormHandler {
    @Override
    protected void execLoad() {
    }
  }
}
