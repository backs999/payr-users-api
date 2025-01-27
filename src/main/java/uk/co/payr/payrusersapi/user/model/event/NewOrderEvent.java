package uk.co.payr.payrusersapi.user.model.event;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewOrderEvent {
    private String userId;
    private String orderId;
}
