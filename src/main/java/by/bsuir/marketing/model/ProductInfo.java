package by.bsuir.marketing.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "product_info")
@Data
public class ProductInfo implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idproduct_info")
    private int idProductInfo;

    @ManyToOne
    @JoinColumn(name = "idproduct")
    private Product product;

    @Column(name = "month")
    private Timestamp month;

    @Column(name = "sell_count")
    private double sellCount;

    @Column(name = "price")
    private double price;

    @Column(name = "production_count")
    private double productionCount;

    @Column(name = "surplus")
    private double surplus;
}