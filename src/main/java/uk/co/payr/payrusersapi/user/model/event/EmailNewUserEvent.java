package uk.co.payr.payrusersapi.user.model.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationNewUserEvent {
    private String timestamp;
    private String message;
    private String service;
}
