package id.ac.ui.cs.advprog.heymart.repository;
import org.springframework.stereotype.Repository;
import id.ac.ui.cs.advprog.heymart.model.User;
import java.util.List;
import java.util.ArrayList;

@Repository
public class UserRepository {

    private final List<User> users = new ArrayList<>();
    private Long idCounter = 1L;

    public User save(User user) {
        user.setId(idCounter++);
        users.add(user);
        return user;
    }

    public User findById(Long id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }
}


