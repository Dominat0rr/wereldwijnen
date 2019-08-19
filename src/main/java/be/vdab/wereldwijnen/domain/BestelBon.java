package be.vdab.wereldwijnen.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
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
    private LocalDateTime besteld; // datum, maar dat was te moeilijk?
    @NotBlank
    private String naam;
    @Embedded
    private Adres adres;
    //@NotNull
    private BestelWijze bestelwijze;
    @ElementCollection
    @CollectionTable(name = "bestelbonlijnen", joinColumns = @JoinColumn(name = "bonid") )
    private Set<BestelbonLijn> bestelbonlijnen;
    @Version
    private long versie;

    protected BestelBon() { }

    public BestelBon(LocalDateTime besteld, String naam, Adres adres, BestelWijze bestelwijze) {
        this.besteld = besteld;
        this.naam = naam;
        this.adres = adres;
        this.bestelwijze = bestelwijze;
        this.bestelbonlijnen = new LinkedHashSet<>();
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getBesteld() {
        return besteld;
    }

    public String getNaam() {
        return naam;
    }

    public Adres getAdres() {
        return adres;
    }

    public BestelWijze getBestelwijze() {
        return bestelwijze;
    }

    public void add(BestelbonLijn bestelbonLijn) {
        bestelbonlijnen.add(bestelbonLijn);
    }
}
