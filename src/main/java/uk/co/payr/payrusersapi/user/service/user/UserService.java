package uk.co.payr.payrusersapi.user.service;

import uk.co.payr.payrusersapi.user.model.User;
import uk.co.payr.payrusersapi.user.model.http.UserRequest;

import java.util.Optional;

/**
 * Service interface for managing users.
 */
public interface UserService {

    /**
     * Retrieves a user by their ID.
     *
     * @param id the ID of the user to retrieve
     * @return an Optional containing the user if found, or an empty Optional if not found
     */
    Optional<User> getUserById(final String id);

    /**
     * Registers a new user.
     *
     * @param userRequest the user to register
     * @return the registered user
     */
    User register(final UserRequest userRequest);

    User completeRegistration(final String email, final String code);



}