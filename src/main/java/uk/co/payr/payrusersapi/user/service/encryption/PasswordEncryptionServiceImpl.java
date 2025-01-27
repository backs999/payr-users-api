package uk.co.payr.payrusersapi.user.service.encryption;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncryptionServiceImpl implements  PasswordEncryptionService {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public String encryptPassword(String password) {
       return passwordEncoder.encode(password);
    }
}
