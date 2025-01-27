package uk.co.payr.payrusersapi.user.service.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.flogger.Flogger;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import uk.co.payr.payrusersapi.user.data.UserRepository;
import uk.co.payr.payrusersapi.user.exception.UserApiException;
import uk.co.payr.payrusersapi.user.model.User;
import uk.co.payr.payrusersapi.user.model.event.EmailNewUserEvent;
import uk.co.payr.payrusersapi.user.model.event.NotificationNewEvent;
import uk.co.payr.payrusersapi.user.model.http.UserRequest;
import uk.co.payr.payrusersapi.user.service.encryption.PasswordEncryptionService;
import uk.co.payr.payrusersapi.user.service.event.EventService;
import uk.co.payr.payrusersapi.user.util.DateUtils;
import uk.co.payr.payrusersapi.user.util.RandomUtils;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Flogger
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final EventService eventService;
    private final PasswordEncryptionService passwordEncryptionService;

    @Override
    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public User register(UserRequest userRequest) {
        log.atInfo().log("Registering userRequest with email " + userRequest.getEmail());

        final var user = User.builder()
                        .password(passwordEncryptionService.encryptPassword(userRequest.getPassword()))
                                .name(userRequest.getName())
                                        .email(userRequest.getEmail())
                                                .registeredAt(DateUtils.nowAsString())
                                                        .deleted(false)
                                                                .blocked(false)
                                                                        .deleted(false)
                                                                                .verified(false)
                                                                                        .orderIds(Set.of())
                .accountActiviationCode(RandomUtils.generateRandomAlphanumeric(10))
                                                                                                .build();
        var saved = userRepository.save(user);
        eventService.sendNotification(NotificationNewEvent.builder()
                .service("payr-users-api")
                .timestamp(DateUtils.nowAsString())
                .message("User with ID [" + saved.getId() + "] has been created")
                .build());
        eventService.sendEmailNewUser(EmailNewUserEvent.builder().userId(saved.getId()).build());
        log.atInfo().log("User registered " + saved.getEmail() + " with ID " + saved.getId());
        return saved;
    }

    @Override
    public User completeRegistration(String email, String code) {
        log.atInfo().log("Completing registration for email " + email + " with code " + code);
        final var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserApiException("User with email " + email + " not found", 404));
        if (StringUtils.equals(user.getAccountActiviationCode(), code)) {
            user.setVerified(true);
            user.setAccountActiviationCode(null);
            var saved = userRepository.save(user);
            eventService.sendNotification(NotificationNewEvent.builder()
                    .service("payr-users-api")
                    .timestamp(DateUtils.nowAsString())
                    .message("User with ID [" + saved.getId() + "] has been verified")
                    .build());
            log.atInfo().log("User verified " + saved.getEmail() + " with ID " + saved.getId());
            return saved;
        } else {
            throw new RuntimeException("Invalid code");
        }
    }
}
