package by.bsuir.marketing.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "blank_answer")
@Data
public class BlankAnswer implements BaseEntity {

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
}