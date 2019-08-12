package be.vdab.wereldwijnen.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@Entity
@Table(name = "bestelbonnen")
public class BestelBon implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @DateTimeFormat
    private LocalDateTime datum;
    @NotBlank
    private String naam;
    @Embedded
    private Adres adres;
    @NotBlank
    private BestelWijze bestelwijze;
    @ElementCollection
    @CollectionTable(name = "bestelbonlijnen", joinColumns = @JoinColumn(name = "bonid") )
    private Set<BestelbonLijn> bestelbonLijnen;
    @Version
    private long versie;

    protected BestelBon() { }


}
