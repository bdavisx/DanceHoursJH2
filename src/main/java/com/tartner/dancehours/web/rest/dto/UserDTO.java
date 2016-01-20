package com.tartner.dancehours.web.rest.dto;

import com.google.common.base.MoreObjects;
import com.tartner.dancehours.domain.Authority;
import com.tartner.dancehours.domain.User;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.*;
import java.util.Set;
import java.util.stream.Collectors;
/**
 * A DTO representing a user, with his authorities.
 */
public class UserDTO {

    public static final int PASSWORD_MIN_LENGTH = 5;
    public static final int PASSWORD_MAX_LENGTH = 100;

    @Email
    @NotNull
    @Size(min = 1, max = 50)
    private String login;

    @NotNull
    @Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH)
    private String password;

    @Size(max = 250)
    private String fullName;

    @Email
    @Size(min = 5, max = 100)
    private String email;

    private boolean activated = false;

    @Size(min = 2, max = 5)
    private String langKey;

    private Set<String> authorities;

    public UserDTO() {
    }

    public UserDTO(User user) {
        this(user.getLogin(), null, user.getFullName(), user.getEmail(), user.getActivated(), user.getLangKey(), user
            .getAuthorities().stream().map(Authority::getName).collect(Collectors.toSet()));
    }

    public UserDTO(String login, String password, String fullName, String email, boolean activated, String langKey,
        Set<String> authorities) {

        this.login = login;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.activated = activated;
        this.langKey = langKey;
        this.authorities = authorities;
    }

    public String getPassword() { return password; }
    public String getLogin() { return login; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public boolean isActivated() { return activated; }
    public String getLangKey() { return langKey; }
    public Set<String> getAuthorities() { return authorities; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("login", login).add("fullName", fullName).add("email", email).add
            ("activated", activated).toString();
    }
}
