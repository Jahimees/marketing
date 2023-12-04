package by.bsuir.marketing.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "field")
@Data
@NoArgsConstructor
public class Field implements BaseEntity {

    private static final long serialVersionUID = 1l;

    public Field(int idField) {
        this.idField = idField;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idfield")
    private int idField;

    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "idfield_type")
    private FieldType fieldType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idblank")
    @JsonBackReference
    private Blank blank;

    @ManyToOne
    @JoinColumn(name = "idtemplate")
    private Template template;

    @OneToMany(mappedBy = "field", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<FieldVariant> fieldVariants;
}