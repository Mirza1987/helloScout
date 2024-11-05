package org.eclipse.scout.apps.contacts.shared.organization;

import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.shared.services.lookup.ILookupService;
import org.eclipse.scout.rt.shared.services.lookup.LookupCall;

@ClassId("7606424c-8138-426b-8718-b1f74ef32e1d")
public class OrganizationLookupCall extends LookupCall<String> {
    private static final long serialVersionUID = 1L;

    @Override
    protected Class<? extends ILookupService<String>> getConfiguredService() {
        return IOrganizationLookupService.class;
    }
}
