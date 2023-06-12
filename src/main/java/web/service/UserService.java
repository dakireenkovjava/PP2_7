package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    List<User> index();

    User show(int n);

    void save(User user);
    void update(int id, User updatedUser);
    void delete(int id);
}
