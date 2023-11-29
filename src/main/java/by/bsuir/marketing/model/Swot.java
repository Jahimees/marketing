package by.bsuir.marketing.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "swot")
@Data
public class Swot implements BaseEntity {

    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idswot")
    private int idSwot;

    @ManyToOne
    @JoinColumn(name = "idaccount")
    private Account account;

    @Column(name = "strength")
    private String strength;

    @Column(name = "weakness")
    private String weakness;

    @Column(name = "opportunity")
    private String opportunity;

    @Column(name = "threat")
    private String threat;

    // constructors, getters, setters, etc.
}