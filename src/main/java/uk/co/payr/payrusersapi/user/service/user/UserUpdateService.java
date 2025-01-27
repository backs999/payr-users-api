package uk.co.payr.payrusersapi.user.service.user;

import uk.co.payr.payrusersapi.user.model.event.UserUpdateEvent;

public interface UserUpdateService {

    void externalUserUpdate(final UserUpdateEvent userUpdateEvent);
}
