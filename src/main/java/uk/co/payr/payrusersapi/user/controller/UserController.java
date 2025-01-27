package uk.co.payr.payrusersapi.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uk.co.payr.payrusersapi.user.model.User;
import uk.co.payr.payrusersapi.user.model.http.UserRequest;
import uk.co.payr.payrusersapi.user.service.user.UserService;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService   userService;

    @GetMapping("{id}")
    public ResponseEntity<User> findById(@PathVariable("id") String id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("{id}/complete-registration")
    public ResponseEntity<User> completeRegistration(@PathVariable("id") String id, @RequestAttribute("email") String email, @RequestAttribute("code") String code) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<User> register(@RequestBody @Validated UserRequest userRequest) {
        return ResponseEntity.ok(userService.register(userRequest));
    }
}
