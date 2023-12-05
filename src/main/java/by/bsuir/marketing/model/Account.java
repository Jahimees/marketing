package by.bsuir.marketing.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "account")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class Account implements BaseEntity, UserDetails {

    private static final long serialVersionUID = 1l;

    public Account(int idAccount) {
        this.idAccount = idAccount;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idaccount")
    private int idAccount;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "idrole")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "idaccount_status")
    private AccountStatus accountStatus;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(getRole());
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountStatus != null &&
                accountStatus.getIdAccountStatus() == 1;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}