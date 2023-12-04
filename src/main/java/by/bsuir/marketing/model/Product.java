package by.bsuir.marketing.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "product")
@Data
public class Product implements BaseEntity {

    private static final long serialVersionUID = 1l;

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

    @ManyToOne
    @JoinColumn(name = "idaccount")
    private Account account;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonManagedReference
    @JsonIgnoreProperties("product")
    private List<ProductInfo> productInfos;
}