package org.eclipse.scout.apps.contacts.client.common;


import org.eclipse.scout.apps.contacts.shared.common.AbstractUrlImageFieldData;
import org.eclipse.scout.apps.contacts.client.Icons;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.action.menu.IMenuType;
import org.eclipse.scout.rt.client.ui.action.menu.ImageFieldMenuType;
import org.eclipse.scout.rt.client.ui.form.fields.imagefield.AbstractImageField;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.CollectionUtility;
import org.eclipse.scout.rt.platform.util.StringUtility;

import java.util.Set;

@FormData(value = AbstractUrlImageFieldData.class,
  sdkCommand = FormData.SdkCommand.CREATE,
  defaultSubtypeSdkCommand = FormData.DefaultSubtypeSdkCommand.CREATE) //	The link to the corresponding field data class.
public class AbstractUrlImageField extends AbstractImageField {


  private String url;

  @FormData //
  public String getUrl() {
    return url;
  }

  @FormData
  //Field PictureUrlField is refactored into the property url value. To transfer the content of this property to the field data object we need to add annotation @FormData to its getter and setter methods.
  public void setUrl(String url) {
    this.url = url;
    updateImage();
  }

  @Override
  protected boolean getConfiguredLabelVisible() {
    return false;
  }

  @Override
  protected int getConfiguredGridH() {
    return 5;
  }

  @Override
  protected boolean getConfiguredAutoFit() {
    return true;
  }

  @Override
  protected String getConfiguredImageId() {
    return Icons.User;
  }


  protected void updateImage() {
    setImageUrl(this.url);
  }

  @Order(1000)
  @ClassId("af5eb5fa-cffc-40a6-b3a5-7170ddeb0388")
  public class EditURLMenu extends AbstractMenu {
    @Override
    protected String getConfiguredText() {
      return TEXTS.get("EditURL");
    }

    @Override
    protected Set<? extends IMenuType> getConfiguredMenuTypes() {
      return CollectionUtility.<IMenuType>hashSet(
        ImageFieldMenuType.ImageUrl,
        ImageFieldMenuType.ImageId,
        ImageFieldMenuType.Null);
    }

    @Override
    protected void execAction() {
      PictureUrlForm form = new PictureUrlForm();
      String oldUrl = getUrl();

      if (StringUtility.hasText(oldUrl)) { //	If we already have an URL for the picture prefill the url field in the form with its value.
        form.getUrlField().setValue(oldUrl);
      }

      form.startModify();
      form.waitFor(); //Method waitFor makes the application wait until the user has closed the form.

      if (form.isFormStored()) { //Only store the new URL if the user has saved a new value. Storing the value will refresh the picture in the user interface.
        setUrl(form.getUrlField().getValue());
        getForm().touch();
      }
    }
  }

}
