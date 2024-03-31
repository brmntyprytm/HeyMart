package id.ac.ui.cs.advprog.heymart.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    private Long id;
    private String username;
    private String password;
    private String email;

    public User() {
    }

    public User(Long id, String username, String password, String email) {
        // TODO: Implement constructor
    }

    @Override
    public String toString() {
        // TODO: Implement toString
        return null;
    }
}