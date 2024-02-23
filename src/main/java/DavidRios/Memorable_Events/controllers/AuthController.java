package DavidRios.Memorable_Events.controllers;

import DavidRios.Memorable_Events.DTOs.LoginToken;
import DavidRios.Memorable_Events.DTOs.LoginUser;
import DavidRios.Memorable_Events.DTOs.NewUser;
import DavidRios.Memorable_Events.entities.User;
import DavidRios.Memorable_Events.security.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping("/login")
    public LoginToken login(@RequestBody LoginUser loginUser) {
        return new LoginToken(authService.authentication(loginUser));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@RequestBody NewUser newUser) {
        return this.authService.saveUser(newUser);
    }
}
