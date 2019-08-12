package be.vdab.wereldwijnen.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@Entity
@Table(name = "wijnen")
@NamedQuery(name = "Wijn.findAantal", query = "select count(*) from Wijn w")
public class Wijn implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "soortid")
    private Soort soort;
    private short jaar;
    private byte beoordeling;
    private BigDecimal prijs;
    private long inBestelling;
    @Version
    private long versie;
    public static final String FIND_AANTAL_WIJNEN = "Wijn.findAantal";
    //public static final String FIND_BY_MULTIPLE_IDS = "Wijn.findByMultipleIds";

    protected Wijn() { }

    public Wijn(Soort soort, short jaar, byte beoordeling, BigDecimal prijs, long inBestelling) {
        this.soort = soort;
        this.jaar = jaar;
        this.beoordeling = beoordeling;
        this.prijs = prijs;
        this.inBestelling = inBestelling;
    }

    public long getId() {
        return id;
    }

    public Soort getSoort() {
        return soort;
    }

    public short getJaar() {
        return jaar;
    }

    public byte getBeoordeling() {
        return beoordeling;
    }

    public BigDecimal getPrijs() {
        return prijs;
    }

    public long getInBestelling() {
        return inBestelling;
    }

    public long getVersie() {
        return versie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Wijn)) return false;
        Wijn wijn = (Wijn) o;
        return jaar == wijn.jaar &&
                Objects.equals(soort, wijn.soort) &&
                Objects.equals(prijs, wijn.prijs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(soort, jaar, prijs);
    }
}
