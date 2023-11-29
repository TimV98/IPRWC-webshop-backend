package com.example.IPRWC.Backend.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@With
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Email
    @NotNull(message = "Email can't be null")
    @NotBlank(message = "Email can't be empty")
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private long phoneNumber;

    @NotNull(message = "First name can't be null")
    @NotBlank(message = "First name can't be empty")
    private String firstName;

    private String prefix;

    @NotNull(message = "Last name can't be null")
    @NotBlank(message = "Last name can't be empty")
    private String lastName;

    @NotNull(message = "Street can't be null")
    @NotBlank(message = "Street can't be empty")
    private String street;

    @NotNull(message = "Zipcode can't be null")
    @NotBlank(message = "Zipcode can't be empty")
    private String zipCode;

    @NotNull(message = "Place can't be null")
    @NotBlank(message = "Place can't be empty")
    private int houseNumber;

    @NotNull(message = "Place can't be null")
    @NotBlank(message = "Place can't be empty")
    private String place;

    @OneToMany
    private List<Order> orders;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name())).toList();
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
}
