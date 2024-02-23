package DavidRios.Memorable_Events.services;

import DavidRios.Memorable_Events.entities.User;
import DavidRios.Memorable_Events.exceptions.NotFoundException;
import DavidRios.Memorable_Events.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public Page<User> getUsers(int pageNumber, int size, String orderBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(orderBy));
        return userRepository.findAll(pageable);
    }


    public User findById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public User findByEmail(String email) { return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User with " + email + " was not found."));}

    public User update(UUID id, User modifiedUser) {
        User found = this.findById(id);
        found.setUsername(modifiedUser.getUsername());
        found.setPassword(modifiedUser.getPassword());
        found.setFullName(modifiedUser.getFullName());
        found.setEmail(modifiedUser.getEmail());
        return userRepository.save(found);
    }

    public void delete(UUID id) {
        User found = this.findById(id);
        userRepository.delete(found);
    }

}
