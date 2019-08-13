package be.vdab.wereldwijnen.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@Entity
@Table(name = "soorten")
@NamedQuery(name = "Soort.findAll", query = "select s from Soort s order by s.naam")
@NamedQuery(name = "Soort.findByLand", query =
        "select s from Soort s where s.land.id = :landid order by s.naam")
public class Soort implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String naam;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "landid")
    private Land land;
    @OneToMany(mappedBy = "soort")
    @OrderBy("jaar")
    private Set<Wijn> wijnen;
    @Version
    private long versie;
    public static final String FIND_ALL = "Soort.findAll";
    public static final String FIND_BY_LAND  = "Soort.findByLand";

    protected Soort() { }

    public Soort(@NotBlank String naam, Land land) {
        this.naam = naam;
        this.wijnen = new LinkedHashSet<>();
        setLand(land);
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public Land getLand() {
        return land;
    }

    public Set<Wijn> getWijnen() {
        return Collections.unmodifiableSet(wijnen);
    }

    public void setLand(Land land) throws NullPointerException {
        if (!land.getSoorten().contains(this)) land.add(this);
        this.land = land;
    }

    public boolean add(Wijn wijn) {
        boolean toegevoegd = wijnen.add(wijn);
        Soort oudeSoort = wijn.getSoort();
        if (oudeSoort != null && oudeSoort != this) oudeSoort.wijnen.remove(wijn);
        if (this != oudeSoort) wijn.setSoort(this);
        return toegevoegd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Soort)) return false;
        Soort soort = (Soort) o;
        return Objects.equals(naam.toLowerCase(), soort.naam.toLowerCase()) &&
                Objects.equals(land, soort.land);
    }

    @Override
    public int hashCode() {
        return Objects.hash(naam.toLowerCase(), land);
    }
}
