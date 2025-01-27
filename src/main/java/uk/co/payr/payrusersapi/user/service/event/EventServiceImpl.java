package uk.co.payr.payrusersapi.user.service.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import uk.co.payr.payrusersapi.user.config.KafkaConfigProps;
import uk.co.payr.payrusersapi.user.model.event.EmailNewUserEvent;
import uk.co.payr.payrusersapi.user.model.event.ExceptionEvent;
import uk.co.payr.payrusersapi.user.model.event.NotificationNewEvent;
import uk.co.payr.payrusersapi.user.model.event.OrderErrorEvent;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements  EventService {

    private final ObjectMapper mapper;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaConfigProps kafkaConfigProps;

    @Override
    public void sendNotification(NotificationNewEvent notificationNewUserEvent) {
        try {
            kafkaTemplate.send(kafkaConfigProps.getTopicNotificationNew(), mapper.writeValueAsString(notificationNewUserEvent));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendEmailNewUser(EmailNewUserEvent emailNewUserEvent) {
        try {
            kafkaTemplate.send(kafkaConfigProps.getTopicEmailRegister(), mapper.writeValueAsString(emailNewUserEvent));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendException(ExceptionEvent exceptionEvent) {
        try {
            kafkaTemplate.send(kafkaConfigProps.getTopicException(), mapper.writeValueAsString(exceptionEvent));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}