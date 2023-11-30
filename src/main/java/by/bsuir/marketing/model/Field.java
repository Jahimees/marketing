package by.bsuir.marketing.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "field")
@Data
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idField")
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
    @JsonIgnoreProperties("fields")
//    @Transient
    private Blank blank;

//    @Column(name = "idblank")
//    private Integer idBlank;

    @ManyToOne
    @JoinColumn(name = "idtemplate")
    private Template template;

    @OneToMany(mappedBy = "field", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonManagedReference
//    @Transient
    @JsonIgnoreProperties("field")
    private List<FieldVariant> fieldVariants;
}