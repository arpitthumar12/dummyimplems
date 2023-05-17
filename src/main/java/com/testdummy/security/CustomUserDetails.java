package com.testdummy.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {

//    private User user;
//    @Autowired
//    private UserGroupRepository userGroupRepository;

    private User user;
    private UserGroupRepository userGroupRepository;



    public CustomUserDetails(User user, UserGroupRepository userGroupRepository) {
        this.user = user;
        this.userGroupRepository = userGroupRepository;
    }

//    public CustomUserDetails(User user) {
//        this.user = user;
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<UserGroup> userGroups = userGroupRepository.findByUserId(user.getId());
        List<Permission> permissions = userGroups.stream()
                .flatMap(userGroup -> userGroup.getPermissions().stream())
                .distinct()
                .collect(Collectors.toList());

        return permissions.stream()
                .peek(permission-> System.out.println(permission.getName()))
                .map(permission -> new SimpleGrantedAuthority(permission.getName()))

                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    // Implement other methods such as getUsername(), getPassword(), isEnabled(), etc.

}
