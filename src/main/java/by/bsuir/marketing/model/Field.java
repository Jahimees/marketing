package by.bsuir.marketing.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "field")
@Data
public class Field implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idfield")
    private int idField;

    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "idfield_type")
    private FieldType fieldType;

    @ManyToOne
    @JoinColumn(name = "idblank")
    private Blank blank;

    @ManyToOne
    @JoinColumn(name = "idtemplate")
    private Template template;
}