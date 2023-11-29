package by.bsuir.marketing.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Fetch;

@Data
@Entity
@Table(name = "field_variant_answer")
public class FieldVariantAnswer implements BaseEntity {

    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idfield_variant_answer")
    private int idFieldVariantAnswer;

    @ManyToOne
    @JoinColumn(name = "idfield_variant")
    private FieldVariant fieldVariant;

    @ManyToOne
    @JoinColumn(name = "idfield_answer")
    private FieldAnswer fieldAnswer;

    @Column(name = "text")
    private String text;
}
