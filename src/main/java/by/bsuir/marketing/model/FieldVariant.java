package by.bsuir.marketing.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "field_variant")
public class FieldVariant implements BaseEntity {

    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idfield_variant")
    private int idFieldVariant;

    @JoinColumn(name = "idfield")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Field field;

    @Column(name = "text")
    private String text;
}
