package uk.co.payr.payrusersapi.user.model.http;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @NotEmpty
    private String name;
    @Column(unique = true)
    @Email
    private String email;
    @NotEmpty
    @Size(min = 8, max = 16)
    private String password;
}
