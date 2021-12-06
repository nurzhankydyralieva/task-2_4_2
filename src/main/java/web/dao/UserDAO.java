package web.dao;


import org.springframework.security.core.userdetails.UserDetails;
import web.model.User;

import java.util.List;

public interface UserDAO {
    List<User> getAllUsers();

    void addUser(User user);

    User getUser(long id);

    void deleteUser(long id);

    void updateUser(User user);

    UserDetails loadUserByUsername(String userName);
}