package DavidRios.Memorable_Events.security;

import DavidRios.Memorable_Events.DTOs.LoginUser;
import DavidRios.Memorable_Events.DTOs.NewUser;
import DavidRios.Memorable_Events.entities.User;
import DavidRios.Memorable_Events.exceptions.BadRequestException;
import DavidRios.Memorable_Events.exceptions.UnauthorizedException;
import DavidRios.Memorable_Events.repositories.UserRepository;
import DavidRios.Memorable_Events.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder bcrypt;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JWTTools jwtTools;

    public String authentication(LoginUser loginUser) {

        User user = userService.findByEmail(loginUser.email());
        if (bcrypt.matches(loginUser.password(), user.getPassword())) {
            return jwtTools.createToken(user);
        } else {
            throw new UnauthorizedException("Wrong credentials!");
        }
    }

    public User saveUser(NewUser newUser) {
        userRepository.findByEmail(newUser.email()).ifPresent(user -> {
            throw new BadRequestException("The following email address exists already: " + user.getEmail());
        });
        User userToSave = new User(newUser.username(), bcrypt.encode(newUser.password()), newUser.fullName(), newUser.email());

        return userRepository.save(userToSave);
    }
}
