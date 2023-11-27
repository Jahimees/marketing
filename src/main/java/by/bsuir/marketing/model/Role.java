package by.bsuir.marketing.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "role")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Role implements BaseEntity, GrantedAuthority {

    private static final long serialVersionUID = 1l;

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