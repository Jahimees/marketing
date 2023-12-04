package by.bsuir.marketing.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "product_info")
@Data
public class ProductInfo implements BaseEntity {

    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idproduct_info")
    private int idProductInfo;

    @ManyToOne
    @JoinColumn(name = "idproduct")
    @JsonBackReference
    @JsonIgnoreProperties("productInfos")
    private Product product;

    @Column(name = "month")
    private Date month;

    @Column(name = "sell_count")
    private double sellCount;

    @Column(name = "price")
    private double price;

    @Column(name = "production_count")
    private double productionCount;

    @Column(name = "surplus")
    private double surplus;

    @Column(name = "filling_date")
    private Date fillingDate;
}