package uk.co.payr.payrusersapi.user.model.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionEvent {
    private String timestamp;
    private String message;
    private String service;
    private String exception;
}
