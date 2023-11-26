package by.bsuir.marketing.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "blank")
@Data
public class Blank implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idblank")
    private int idBlank;

    @ManyToOne
    @JoinColumn(name = "idblank_status")
    private BlankStatus blankStatus;

    @Column(name = "name")
    private String name;

    @Column(name = "creation_date")
    private Date creationDate;

    @ManyToOne
    @JoinColumn(name = "idproduct")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "idaccount")
    private Account account;
}