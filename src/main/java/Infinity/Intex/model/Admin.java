package Infinity.Intex.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Admin implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "admin_id_seq")
    private Integer id;
    private String fullName;
    @Column(unique = true)
    private String username;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Authorities> authorities;

    @OneToMany(mappedBy = "sellerId")
    private List<Product> productList;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(authorities != null) {
            return authorities.stream().map(
                            a -> new SimpleGrantedAuthority(a.getAuthority()))
                    .collect(Collectors.toSet());
        }
        return new ArrayList<SimpleGrantedAuthority>();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return password;
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
