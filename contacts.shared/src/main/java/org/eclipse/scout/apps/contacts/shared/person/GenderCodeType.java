package org.eclipse.scout.apps.contacts.shared.person;

import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.services.common.code.AbstractCode;
import org.eclipse.scout.rt.shared.services.common.code.AbstractCodeType;

@ClassId("23482843-8cf2-4a38-a6e8-0febfe627d5e")
public class GenderCodeType extends AbstractCodeType<String, String> {
    private static final long serialVersionUID = 1L;
    public static final String ID = "Gender";

    @Override
    public String getId() {
        return ID;
    }

  @Order(1000)
  @ClassId("288f0a13-5bac-4241-840c-a7f67445d2e4")
  public static class MaleCode extends AbstractCode<String> {
    private static final long serialVersionUID = 1L;
    public static final String ID = "M";

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Male");
    }

    @Override
    public String getId() {
      return ID;
    }
  }

  @Order(2000)
  @ClassId("2ee2a4b0-fe08-4b4a-8df6-3b24100a0891")
  public static class FemaleCode extends AbstractCode<String> {
    private static final long serialVersionUID = 1L;
    public static final String ID = "F";

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Female");
    }

    @Override
    public String getId() {
      return ID;
    }
  }
}
