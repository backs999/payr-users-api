package uk.co.payr.payrusersapi.user.service;

import uk.co.payr.payrusersapi.user.model.event.UserUpdateEvent;

public interface UserUpdateService {

    void externalUserUpdate(final UserUpdateEvent userUpdateEvent);
}
