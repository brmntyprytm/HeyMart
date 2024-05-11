package id.ac.ui.cs.advprog.heymart.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import id.ac.ui.cs.advprog.heymart.model.User;
import id.ac.ui.cs.advprog.heymart.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean loginUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return false;
        }

        return user.getPassword().equals(password);
    }

    @Override
    public boolean registerUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return false;
        }

        user.setRole("user");
        user.setBalance(0.0);

        boolean isAdmin = user.getUsername().equals("admin");

        if (isAdmin) {
            user.setRole("admin");
        }

        if (user.getRole().equals("manager")) {
            user.setRole("manager");
        }

        try {
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

