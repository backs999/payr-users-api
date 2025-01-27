package uk.co.payr.payrusersapi.user.service;

import uk.co.payr.payrusersapi.user.model.event.EmailNewUserEvent;
import uk.co.payr.payrusersapi.user.model.event.ExceptionEvent;
import uk.co.payr.payrusersapi.user.model.event.NotificationNewEvent;
import uk.co.payr.payrusersapi.user.model.event.OrderErrorEvent;

public interface EventService {

    void sendNotificationNewUser(final NotificationNewEvent notificationNewUserEvent);

    void sendEmailNewUser(final EmailNewUserEvent emailNewUserEvent);

    void sendException(ExceptionEvent exceptionEvent);
}
