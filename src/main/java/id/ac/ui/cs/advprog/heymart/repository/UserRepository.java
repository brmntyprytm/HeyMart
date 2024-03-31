package id.ac.ui.cs.advprog.heymart.repository;
import org.springframework.stereotype.Repository;
import id.ac.ui.cs.advprog.heymart.model.User;
import java.util.List;
import java.util.ArrayList;

@Repository
public class UserRepository {

    private final List<User> users = new ArrayList<>();

    public User save(User user) {
        // TODO: Implement this method
        return null;
    }

    public User findById(Long id) {
        // TODO: Implement this method
        return null;
    }
}


