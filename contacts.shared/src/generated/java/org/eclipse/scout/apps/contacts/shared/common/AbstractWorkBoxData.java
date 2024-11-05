package org.eclipse.scout.apps.contacts.shared.common;

import jakarta.annotation.Generated;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.shared.data.form.fields.AbstractFormFieldData;
import org.eclipse.scout.rt.shared.data.form.fields.AbstractValueFieldData;

/**
 * <b>NOTE:</b><br>
 * This class is auto generated by the Scout SDK. No manual modifications recommended.
 */
@Generated(value = "org.eclipse.scout.apps.contacts.client.common.AbstractWorkBox", comments = "This class is auto generated by the Scout SDK. No manual modifications recommended.")
public class AbstractWorkBoxData extends AbstractFormFieldData {
    private static final long serialVersionUID = 1L;

    public EmailWork getEmailWork() {
        return getFieldByClass(EmailWork.class);
    }

    public Organization getOrganization() {
        return getFieldByClass(Organization.class);
    }

    public PhoneWork getPhoneWork() {
        return getFieldByClass(PhoneWork.class);
    }

    public Position getPosition() {
        return getFieldByClass(Position.class);
    }

    @ClassId("3f79101c-c072-4096-8e55-d81be6a1e841-formdata")
    public static class EmailWork extends AbstractValueFieldData<String> {
        private static final long serialVersionUID = 1L;
    }

    @ClassId("b9a42a6f-7ac7-4055-bc3a-9eaae2ba22b7-formdata")
    public static class Organization extends AbstractValueFieldData<String> {
        private static final long serialVersionUID = 1L;
    }

    @ClassId("0fdea723-63e0-4d20-8d6e-821b8404934f-formdata")
    public static class PhoneWork extends AbstractValueFieldData<String> {
        private static final long serialVersionUID = 1L;
    }

    @ClassId("fb9757d2-40a7-454b-9df3-ad50ccce37cf-formdata")
    public static class Position extends AbstractValueFieldData<String> {
        private static final long serialVersionUID = 1L;
    }
}