package uk.co.payr.payrusersapi.user.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.flogger.Flogger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import uk.co.payr.payrusersapi.user.model.User;
import uk.co.payr.payrusersapi.user.model.event.NewOrder;
import uk.co.payr.payrusersapi.user.service.UserService;

import java.util.Set;

@Component
@RequiredArgsConstructor
@Flogger
public class OrderListener {

    private final ObjectMapper mapper;
    private final UserService userService;

    @KafkaListener(topics = "${payr.kafka.topic-orders-new}", groupId = "payr-users-api")
    public void listens(final String incomingOrder) {
        try {
            final var newOrder = mapper.readValue(incomingOrder, NewOrder.class);
log.atInfo().log("Order received for user: " + newOrder.getUserId());
        final var user = userService.getUserById(newOrder.getUserId()).orElseGet(() -> {
            return userService.register(User.builder().build());
        });
        log.atInfo().log("Adding order " + newOrder.getOrderId() + " to user: " + newOrder.getUserId());
        user.getOrderIds().add(newOrder.getOrderId());
        userService.register(user);
        log.atInfo().log("User updated in the database");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}