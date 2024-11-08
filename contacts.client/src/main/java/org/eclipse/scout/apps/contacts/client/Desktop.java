package org.eclipse.scout.apps.contacts.client;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.scout.rt.client.session.ClientSessionProvider;
import org.eclipse.scout.rt.client.ui.action.keystroke.IKeyStroke;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.action.menu.IMenu;
import org.eclipse.scout.rt.client.ui.desktop.AbstractDesktop;
import org.eclipse.scout.rt.client.ui.desktop.notification.NativeNotificationDefaults;
import org.eclipse.scout.rt.client.ui.desktop.outline.AbstractOutlineViewButton;
import org.eclipse.scout.rt.client.ui.desktop.outline.IOutline;
import org.eclipse.scout.rt.client.ui.form.ScoutInfoForm;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.CollectionUtility;
import org.eclipse.scout.rt.platform.util.StringUtility;
import org.eclipse.scout.rt.security.IAccessControlService;

import org.eclipse.scout.apps.contacts.client.Desktop.UserProfileMenu.ThemeMenu.DarkThemeMenu;
import org.eclipse.scout.apps.contacts.client.Desktop.UserProfileMenu.ThemeMenu.DefaultThemeMenu;
import org.eclipse.scout.apps.contacts.client.search.SearchOutline;
import org.eclipse.scout.apps.contacts.client.contact.ContactOutline;
import org.eclipse.scout.apps.contacts.shared.Icons;

/**
 * @author ADMIVBDM
 */
public class Desktop extends AbstractDesktop {

  public Desktop() {
    addPropertyChangeListener(PROP_THEME, this::onThemeChanged);
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("ApplicationTitle");
  }

  @Override
  protected String getConfiguredLogoId() {
    return Icons.AppLogo;
  }

  @Override
  protected NativeNotificationDefaults getConfiguredNativeNotificationDefaults() {
    return super.getConfiguredNativeNotificationDefaults().withIconId("application_logo.png");
  }

  @Override
  protected List<Class<? extends IOutline>> getConfiguredOutlines() {
    return CollectionUtility.<Class<? extends IOutline>> arrayList(ContactOutline.class, SearchOutline.class);

  }

  @Override
  protected void execDefaultView() {
    selectFirstVisibleOutline();
  }

  protected void selectFirstVisibleOutline() {
    for (IOutline outline : getAvailableOutlines()) {
      if (outline.isEnabled() && outline.isVisible()) {
        setOutline(outline.getClass());
        return;
      }
    }
  }

  protected void onThemeChanged(PropertyChangeEvent evt) {
    IMenu darkMenu = getMenuByClass(DarkThemeMenu.class);
    IMenu defaultMenu = getMenuByClass(DefaultThemeMenu.class);
    String newThemeName = (String) evt.getNewValue();
    if (DarkThemeMenu.DARK_THEME.equalsIgnoreCase(newThemeName)) {
      darkMenu.setIconId(Icons.CheckedBold);
      defaultMenu.setIconId(null);
    } else {
      darkMenu.setIconId(null);
      defaultMenu.setIconId(Icons.CheckedBold);
    }
  }

  @Order(1000)
  public class UserProfileMenu extends AbstractMenu {

    @Override
    protected String getConfiguredKeyStroke() {
      return IKeyStroke.F10;
    }

    @Override
    protected String getConfiguredIconId() {
      return Icons.PersonSolid;
    }

    @Override
    protected String getConfiguredText() {
      String userId = BEANS.get(IAccessControlService.class).getUserIdOfCurrentSubject();
      return StringUtility.uppercaseFirst(userId);
    }

    @Order(1000)
    public class AboutMenu extends AbstractMenu {

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("About");
      }

      @Override
      protected void execAction() {
        ScoutInfoForm form = new ScoutInfoForm();
        form.startModify();
      }
    }

    @Order(2000)
    public class ThemeMenu extends AbstractMenu {

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("Theme");
      }

      @Order(1000)
      public class DefaultThemeMenu extends AbstractMenu {

        private static final String DEFAULT_THEME = "Default";

        @Override
        protected String getConfiguredText() {
          return DEFAULT_THEME;
        }

        @Override
        protected void execAction() {
          setTheme(DEFAULT_THEME.toLowerCase());
        }
      }

      @Order(2000)
      public class DarkThemeMenu extends AbstractMenu {

        private static final String DARK_THEME = "Dark";

        @Override
        protected String getConfiguredText() {
          return DARK_THEME;
        }

        @Override
        protected void execAction() {
          setTheme(DARK_THEME.toLowerCase());
        }
      }
    }

    @Order(3000)
    public class LogoutMenu extends AbstractMenu {

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("Logout");
      }

      @Override
      protected void execAction() {
        ClientSessionProvider.currentSession().stop();
      }
    }
  }

  @Order(1000)
  public class ContactOutlineViewButton extends AbstractOutlineViewButton {

    public ContactOutlineViewButton() {
      this(ContactOutline.class);
    }

    protected ContactOutlineViewButton(Class<? extends ContactOutline> outlineClass) {
      super(Desktop.this, outlineClass);
    }

    @Override
    protected String getConfiguredKeyStroke() {
      return IKeyStroke.F2;
    }
  }

  @Order(2000)
  public class SearchOutlineViewButton extends AbstractOutlineViewButton {

    public SearchOutlineViewButton() {
      this(SearchOutline.class);
    }

    protected SearchOutlineViewButton(Class<? extends SearchOutline> outlineClass) {
      super(Desktop.this, outlineClass);
    }

    @Override
    protected DisplayStyle getConfiguredDisplayStyle() {
      return DisplayStyle.TAB;
    }

    @Override
    protected String getConfiguredKeyStroke() {
      return IKeyStroke.F3;
    }
  }

  @Order(2000)
  @ClassId("848d4b1a-d88a-4a1b-bd98-707e55ed3d31")
  public class QuickAccessMenu extends AbstractMenu {
    @Override
    protected String getConfiguredText() {
      return TEXTS.get("QuickAccess");
    }

    @Override
    protected void execAction() {

    }
  }

  @Order(3000)
  @ClassId("33eee13a-6e72-4869-825d-26f57684a409")
  public class UserMenu extends AbstractMenu {
    @Override
    protected String getConfiguredText() {
      return TEXTS.get("User");
    }

    @Override
    protected void execAction() {

    }
  }

  @Order(4000)
  @ClassId("5516e731-6637-4d2d-9393-b68d50a900c8")
  public class OptionsMenu extends AbstractMenu {
    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Options");
    }

    @Override
    protected String getConfiguredIconId() {
      return Icons.Gear;
    }
  }

}
