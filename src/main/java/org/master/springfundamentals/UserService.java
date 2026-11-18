package org.master.springfundamentals;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private List<User> userList = new ArrayList<>();
    private Long userId = 1L;

    public List<User> fetchAllUsers() {
        return userList;
    }

    public User fetchSingleUser(Long id) {
        return userList.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void createUser(User user) {
        user.setId(userId++);
        userList.add(user);
    }

    public void updateUser(Long id, User user) {
        User existingUser = fetchSingleUser(id);
        if (existingUser != null) {
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }
}
