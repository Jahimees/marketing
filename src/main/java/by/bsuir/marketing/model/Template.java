package by.bsuir.marketing.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "template")
public class Template implements BaseEntity {

    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtemplate")
    private int idTemplate;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "idaccount")
    private Account account;
}