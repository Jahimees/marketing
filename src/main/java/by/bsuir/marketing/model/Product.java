package by.bsuir.marketing.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "product")
@Data
public class Product implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idproduct")
    private int idProduct;

    @Column(name = "name")
    private String name;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "about")
    private String about;

    @ManyToOne
    @JoinColumn(name = "idproduct_type")
    private ProductType productType;
}