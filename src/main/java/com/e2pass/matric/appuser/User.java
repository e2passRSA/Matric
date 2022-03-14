package com.e2pass.matric.appuser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name="User")
@Table(name="user", uniqueConstraints = {
        @UniqueConstraint(name="email_unique", columnNames = "email")
})
public class User implements UserDetails {

    @Id
    @SequenceGenerator(name="user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @Column(name="id")
    private Long id;

    @Column(name="fist_name", columnDefinition = "TEXT", nullable = false)
    private String firstname;

    @Column(name="last_name", columnDefinition = "TEXT", nullable= false)
    private String lastname;

    @Column(name="email", columnDefinition = "TEXT")
    private String email;

    @Column(name="password", columnDefinition = "TEXT")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name="user_role", columnDefinition = "TEXT", nullable = false)
    private UserRole userRole;

    @Column(name = "is_enable", columnDefinition = "TEXT")
    private Boolean enabled = false;

    @Column(name="is_locked", columnDefinition = "TEXT")
    private Boolean locked = false;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        var authority = new SimpleGrantedAuthority(userRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
