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
@Entity
@Table(name = "users")
public class UserRequest {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @NotEmpty
    private String name;
    @Column(unique = true)
    @Email
    private String email;
    @NotEmpty
    @Size(min = 8, max = 16)
    private String password;
    private String registeredAt;
    private String lastLoginAt;
    private String registrationEmailSentAt;
    private boolean active;
    private boolean deleted;
    private boolean blocked;
    private boolean verified;
    @ElementCollection
    private Set<String> orderIds;
}
