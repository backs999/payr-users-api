package uk.co.payr.payrusersapi.user.model.event;

import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class OrderErrorEvent {
    private String userId;
    private String orderId;
    private String error;
}
