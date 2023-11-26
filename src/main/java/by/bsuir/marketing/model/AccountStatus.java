package by.bsuir.marketing.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "account_status")
@Data
public class AccountStatus implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idaccount_status")
    private int idAccountStatus;

    @Column(name = "name")
    private String name;

}