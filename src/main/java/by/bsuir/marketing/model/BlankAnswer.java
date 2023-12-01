package by.bsuir.marketing.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "blank_answer")
@Data
@NoArgsConstructor
public class BlankAnswer implements BaseEntity {

    private static final long serialVersionUID = 1l;

    public BlankAnswer(int idBlankAnswer) {
        this.idBlankAnswer = idBlankAnswer;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idblank_answer")
    private int idBlankAnswer;

    @ManyToOne
    @JoinColumn(name = "idblank")
    private Blank blank;

    @Column(name = "username")
    private String username;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "answer_date")
    private Date answerDate;

    @OneToMany(mappedBy = "blankAnswer", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonManagedReference
    @JsonIgnoreProperties("blankAnswer")
    private List<FieldAnswer> fieldAnswers;
}