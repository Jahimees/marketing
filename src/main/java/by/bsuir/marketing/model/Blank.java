package by.bsuir.marketing.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "blank")
@Data
@NoArgsConstructor
public class Blank implements BaseEntity {

    private static final long serialVersionUID = 1l;

    public Blank(int idBlank) {
        this.idBlank = idBlank;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idblank")
    private int idBlank;

    @ManyToOne
    @JoinColumn(name = "idblank_status")
    private BlankStatus blankStatus;

    @Column(name = "name")
    private String name;

    @Column(name = "creation_date")
    private Date creationDate;

    @ManyToOne
    @JoinColumn(name = "idproduct")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "idaccount")
    private Account account;

    @OneToMany(mappedBy = "blank", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Field> fields;
}