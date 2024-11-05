package org.eclipse.scout.apps.contacts.server.common;

import org.eclipse.scout.apps.contacts.shared.common.*;
import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.security.ACCESS;

public class PictureService implements IPictureService {
    @Override
    public PictureFormData prepareCreate(PictureFormData formData) {
        if (!ACCESS.check(new CreatePicturePermission())) {
            throw new VetoException(TEXTS.get("AuthorizationFailed"));
        }
// TODO [ADMIVBDM] add business logic here.
        return formData;
    }

    @Override
    public PictureFormData create(PictureFormData formData) {
        if (!ACCESS.check(new CreatePicturePermission())) {
            throw new VetoException(TEXTS.get("AuthorizationFailed"));
        }
// TODO [ADMIVBDM] add business logic here.
        return formData;
    }

    @Override
    public PictureFormData load(PictureFormData formData) {
        if (!ACCESS.check(new ReadPicturePermission())) {
            throw new VetoException(TEXTS.get("AuthorizationFailed"));
        }
// TODO [ADMIVBDM] add business logic here.
        return formData;
    }

    @Override
    public PictureFormData store(PictureFormData formData) {
        if (!ACCESS.check(new UpdatePicturePermission())) {
            throw new VetoException(TEXTS.get("AuthorizationFailed"));
        }
// TODO [ADMIVBDM] add business logic here.
        return formData;
    }
}
