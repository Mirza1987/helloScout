package org.eclipse.scout.apps.contacts.shared.common;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;

@TunnelToServer
public interface IPictureService extends IService {
    PictureFormData prepareCreate(PictureFormData formData);

    PictureFormData create(PictureFormData formData);

    PictureFormData load(PictureFormData formData);

    PictureFormData store(PictureFormData formData);
}
