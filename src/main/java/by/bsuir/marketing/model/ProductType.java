package by.bsuir.marketing.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "product_type")
@Data
public class ProductType implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idproduct_type")
    private int idProductType;

    @Column(name = "name")
    private String name;
}