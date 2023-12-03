package by.bsuir.marketing.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "account_status")
@Data
@NoArgsConstructor
public class AccountStatus implements BaseEntity {

    private static final long serialVersionUID = 1l;

    public AccountStatus(int idAccountStatus) {
        this.idAccountStatus = idAccountStatus;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idaccount_status")
    private int idAccountStatus;

    @Column(name = "name")
    private String name;

}