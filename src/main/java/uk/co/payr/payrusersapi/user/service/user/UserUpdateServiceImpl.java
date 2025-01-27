package uk.co.payr.payrusersapi.user.service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import uk.co.payr.payrusersapi.user.model.event.UserUpdateEvent;
import uk.co.payr.payrusersapi.user.util.DateUtils;

@Service
@AllArgsConstructor
public class UserUpdateServiceImpl implements  UserUpdateService {

    private final UserService userService;

    @Override
    public void externalUserUpdate(@NonNull  UserUpdateEvent userUpdateEvent) {

        final var user = userService.getUserById(userUpdateEvent.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));

        if (StringUtils.equals(userUpdateEvent.getAction(), "registration-email-sent")) {
            user.setRegistrationEmailSentAt(DateUtils.nowAsString());
        }

        userService.update(user);
    }
}
