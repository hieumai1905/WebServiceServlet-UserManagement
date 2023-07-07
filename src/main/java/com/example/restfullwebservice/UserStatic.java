package com.example.restfullwebservice;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserStatic {
    private static List<User> users;

    static {
        users = new ArrayList<User>();
        users.add(new User(1L, "John", 20));
        users.add(new User(2L, "Mary", 21));
        users.add(new User(3L, "Peter", 22));
    }

    public static List<User> getUsers() {
        return users;
    }

    public static User getUser(Long id) {
        for (User u : users) {
            if (u.getId().equals(id)) {
                return u;
            }
        }
        return null;
    }

    public static void addUser(User user) {
        UserStatic.users.add(user);
    }

    public static void updateUser(User user) {
        for (User u : users) {
            if (u.getId().equals(user.getId())) {
                u.setName(user.getName());
                u.setAge(user.getAge());
            }
        }
    }

    public static void deleteUser(Long id) {
        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);
            if (u.getId().equals(id)) {
                users.remove(u);
            }
        }
    }

    public static String usersToGSon() throws IOException {
//        return new Gson().toJson(users);
        return new ObjectMapper().writeValueAsString(users);
    }
}
