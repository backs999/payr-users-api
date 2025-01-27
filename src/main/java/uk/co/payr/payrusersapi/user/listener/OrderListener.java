package uk.co.payr.payrusersapi.user.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.flogger.Flogger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import uk.co.payr.payrusersapi.user.service.user.UserService;

@Component
@RequiredArgsConstructor
@Flogger
public class OrderListener {

    private final ObjectMapper mapper;
    private final UserService userService;

    @KafkaListener(topics = "${payr.kafka.topic-orders-new}", groupId = "payr-users-api")
    public void listens(final String incomingOrder) {
        // nothing for now
    }
}