package by.bsuir.marketing.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "field_answer")
@Data
public class FieldAnswer implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idfield_answer")
    private int idFieldAnswer;

    @ManyToOne
    @JoinColumn(name = "idblank_answer")
    private BlankAnswer blankAnswer;

    @ManyToOne
    @JoinColumn(name = "idfield")
    private Field field;

    @Column(name = "answer")
    private String answer;
}