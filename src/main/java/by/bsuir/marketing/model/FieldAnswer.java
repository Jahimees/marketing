package by.bsuir.marketing.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "field_answer")
@Data
@NoArgsConstructor
public class FieldAnswer implements BaseEntity {

    private static final long serialVersionUID = 1l;

    public FieldAnswer(int idFieldAnswer) {
        this.idFieldAnswer = idFieldAnswer;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idfield_answer")
    private int idFieldAnswer;

    @JoinColumn(name = "idblank_answer")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JsonIgnoreProperties("fieldAnswers")
    private BlankAnswer blankAnswer;

    @ManyToOne
    @JoinColumn(name = "idfield")
    private Field field;

    @Column(name = "answer")
    private String answer;

    @OneToMany(mappedBy = "fieldAnswer", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonManagedReference
    @JsonIgnoreProperties("fieldAnswer")
    private List<FieldVariantAnswer> fieldVariantAnswers;
}