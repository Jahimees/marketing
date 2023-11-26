package by.bsuir.marketing.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "field_type")
public class FieldType implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idfield_type")
    private int idFieldType;

    @Column(name = "name")
    private String name;
}