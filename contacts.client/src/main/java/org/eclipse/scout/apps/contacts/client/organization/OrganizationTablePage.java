package org.eclipse.scout.apps.contacts.client.organization;

import org.eclipse.scout.apps.contacts.client.Icons;
import org.eclipse.scout.apps.contacts.client.organization.OrganizationTablePage.Table;
import org.eclipse.scout.apps.contacts.shared.organization.IOrganizationService;
import org.eclipse.scout.apps.contacts.shared.organization.OrganizationTablePageData;
import org.eclipse.scout.rt.client.dto.Data;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.action.menu.IMenu;
import org.eclipse.scout.rt.client.ui.action.menu.IMenuType;
import org.eclipse.scout.rt.client.ui.action.menu.TableMenuType;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.client.ui.form.FormEvent;
import org.eclipse.scout.rt.client.ui.form.FormListener;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.CollectionUtility;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

import java.util.Set;

@Data(OrganizationTablePageData.class)
@ClassId("5e20f4a7-ad7e-4dab-abd8-c4c9d2954396")
public class OrganizationTablePage extends AbstractPageWithTable<Table> {

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Organizations");
  }
  @Override
  protected boolean getConfiguredLeaf() {
    return true;
  }

  @Override
  protected void execLoadData(SearchFilter filter) {
    importPageData(BEANS.get(IOrganizationService.class).getOrganizationTableData(filter));
  }

  /* LASTTASK
  @Override
  protected IPage<?> execCreateChildPage(ITableRow row) {
    OrganizationNodePage childPage = new OrganizationNodePage();
    childPage.setOrganizationId(getTable().getOrganizationIdColumn().getValue(row));
    return childPage;
  }
*/

  @Override
  protected String getConfiguredIconId() {
    return Icons.OrganizationLarge;
  }

  @ClassId("6390be49-00e9-423d-bb01-d265e0dc18bc")
  public class Table extends AbstractTable {
    public CityColumn getCityColumn() {
      return getColumnSet().getColumnByClass(CityColumn.class);
    }

    public CountryColumn getCountryColumn() {
      return getColumnSet().getColumnByClass(CountryColumn.class);
    }

    public HomepageColumn getHomepageColumn() {
      return getColumnSet().getColumnByClass(HomepageColumn.class);
    }

    public NameColumn getNameColumn() {
      return getColumnSet().getColumnByClass(NameColumn.class);
    }

    public OrganizationIdColumn getOrganizationIdColumn() {
      return getColumnSet().getColumnByClass(OrganizationIdColumn.class);
    }

    @Order(1000)
    @ClassId("adc4ef2b-da40-4c53-a48a-21311306eb98")
    public class OrganizationIdColumn extends AbstractStringColumn {
      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("OrganizatioId");
      }

      @Override
      protected boolean getConfiguredDisplayable() {
        return false;
      }

      @Override
      protected int getConfiguredWidth() {
        return 100;
      }
    }

    @Order(2000)
    @ClassId("e188be53-deb0-4bda-ad45-3f89d05e05e5")
    public class NameColumn extends AbstractStringColumn {
      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Name");
      }

      @Override
      protected int getConfiguredWidth() {
        return 100;
      }
    }

    @Order(3000)
    @ClassId("4326a9ef-71b8-40a9-a4af-a0f726ac4e07")
    public class CityColumn extends AbstractStringColumn {
      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("City");
      }

      @Override
      protected int getConfiguredWidth() {
        return 100;
      }
    }

    @Order(4000)
    @ClassId("5113808d-6590-4d74-bdb2-29bf9b676c4c")
    public class CountryColumn extends AbstractStringColumn {
      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Country");
      }

      @Override
      protected int getConfiguredWidth() {
        return 100;
      }
    }

    @Order(5000)
    @ClassId("0dadfb48-1dbb-480e-9e2f-417ad97a463f")
    public class HomepageColumn extends AbstractStringColumn {
      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Organizations");
      }

      @Override
      protected boolean getConfiguredVisible() {
        return false;
      }

      @Override
      protected int getConfiguredWidth() {
        return 100;
      }
    }


    @Order(1000)
    @ClassId("aea0f1d2-8fc8-4fb2-8e7b-ba81fe4e8f17")
    public class NewMenu extends AbstractMenu {
      @Override
      protected String getConfiguredText() {
        return TEXTS.get("New");
      }

      @Override //sets the "Neu" button when organization table view is opened
      protected Set<? extends IMenuType> getConfiguredMenuTypes() {
        return CollectionUtility.<IMenuType>hashSet(TableMenuType.EmptySpace, TableMenuType.SingleSelection);
      }

      @Override
      protected void execAction() {
        final OrganizationForm form = new OrganizationForm();
        form.addFormListener(e -> {
          if (FormEvent.TYPE_CLOSED == e.getType() && form.isFormStored()) {
            reloadPage();
          }
        });

        form.startNew();
      }
    }


    @Override
    protected Class<? extends IMenu> getConfiguredDefaultMenu() {
      return OrganizationTablePage.Table.EditMenu.class;
    }

    @Order(2000)
    @ClassId("212a089c-84f5-45ee-b40f-3abb4b36cdfa")
    public class EditMenu extends AbstractMenu {
      @Override
      protected String getConfiguredText() {
        return TEXTS.get("Edit");
      }

      @Override
      protected void execAction() {
        OrganizationForm form = new OrganizationForm();
        form.setOrganizationId(getOrganizationIdColumn().getSelectedValue());
        form.addFormListener(new OrganizationFormListener());
        // start the form using its modify handler
        form.startModify();
      }
    }

    private class OrganizationFormListener implements FormListener {
      @Override
      public void formChanged(FormEvent e) {
        // reload page to reflect new/changed data after saving any changes
        if (FormEvent.TYPE_CLOSED == e.getType() && e.getForm().isFormStored()) {
          reloadPage();
        }
      }
    }

  }
}
