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
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "landen")
@NamedQuery(name = "Land.findAll", query = "select l from Land l order by l.naam")
public class Land implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String naam;
    @OneToMany(mappedBy = "land")
    @OrderBy("naam")
    private Set<Soort> soorten;
    @Version
    private long versie;
    public final static String FIND_ALL = "Land.findAll";

    protected Land() { }

    public Land(@NotBlank String naam) {
        this.naam = naam;
        this.soorten = new LinkedHashSet<>();
    }

    public Land(long id, @NotBlank String naam) {
        this.id = id;
        this.naam = naam;
        this.soorten = new LinkedHashSet<>();
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public Set<Soort> getSoorten() {
        return Collections.unmodifiableSet(soorten);
    }

    public boolean add(Soort soort) {
        boolean toegevoegd = soorten.add(soort);
        Land oudLand = soort.getLand();

        if (oudLand != null && oudLand != this) oudLand.soorten.remove(soort);
        if (this != oudLand) soort.setLand(this);

        return toegevoegd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Land)) return false;
        Land land = (Land) o;
        return Objects.equals(naam, land.naam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(naam);
    }
}
