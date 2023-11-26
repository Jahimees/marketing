package by.bsuir.marketing.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "blank_status")
@Data
public class BlankStatus implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idblank_status")
    private int idBlankStatus;

    @Column(name = "name")
    private String name;
}