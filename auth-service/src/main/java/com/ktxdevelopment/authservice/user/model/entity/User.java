package com.ktxdevelopment.authservice.user.model.entity;

import com.ktxdevelopment.authservice.token.model.Token;
import com.ktxdevelopment.authservice.user.model.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user")
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue
    @Getter
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "user_id", nullable = false, unique = true)
    private String userId;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String encryptedPassword;

    @Column(name = "ban", nullable = false)
    private Boolean isBanned;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Token> tokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getPassword() {
        return encryptedPassword;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public String getRealUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return !isBanned; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return !isBanned; }

    public User(String userId, String username, String email, String encryptedPassword, Role role) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.encryptedPassword = encryptedPassword;
        this.role = role;
    }

    public User(String userId, String username, String email, String encryptedPassword, Role role, List<Token> tokens) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.encryptedPassword = encryptedPassword;
        this.role = role;
        this.tokens = tokens;
    }


}
