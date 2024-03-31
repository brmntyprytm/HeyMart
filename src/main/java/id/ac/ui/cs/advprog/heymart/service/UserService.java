package id.ac.ui.cs.advprog.heymart.service;

import id.ac.ui.cs.advprog.heymart.model.User;

public interface UserService {
    boolean registerUser(User user);
    boolean loginUser(String username, String password);

}

