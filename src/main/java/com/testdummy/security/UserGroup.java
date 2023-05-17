package com.testdummy.security;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_groups")
@Data
public class UserGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "group_permission_mapping",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private List<Permission> permissions;

    @ManyToMany(mappedBy = "userGroups")
    private List<User> users;
    // Constructors, getters, and setters

    public void addUser(User user) {
        if (users == null) {
            users = new ArrayList<>();
        }
        users.add(user);
        user.getUserGroups().add(this);
    }

    public void removeUser(User user) {
        if (users != null) {
            users.remove(user);
            user.getUserGroups().remove(this);
        }
    }

    // Add necessary mappings, relationships, and constructors

    // Getters and setters

}
