package uk.co.payr.payrusersapi.user.service.event;

import uk.co.payr.payrusersapi.user.model.event.EmailNewUserEvent;
import uk.co.payr.payrusersapi.user.model.event.ExceptionEvent;
import uk.co.payr.payrusersapi.user.model.event.NotificationNewEvent;

public interface EventService {

    void sendNotification(final NotificationNewEvent notificationNewUserEvent);

    void sendEmailNewUser(final EmailNewUserEvent emailNewUserEvent);

    void sendException(ExceptionEvent exceptionEvent);
}
