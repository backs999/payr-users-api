package uk.co.payr.payrusersapi.user.service;


public interface PasswordEncryptionService {
    String encryptPassword(final String password);
}
