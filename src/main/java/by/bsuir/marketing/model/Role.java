package by.bsuir.marketing.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "role")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class Role implements BaseEntity, GrantedAuthority {

    private static final long serialVersionUID = 1l;

    public Role(int idRole) {
        this.idRole = idRole;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idrole")
    private int idRole;

    @Column(name = "name")
    private String name;

    @Override
    public String getAuthority() {
        return getName();
    }
}