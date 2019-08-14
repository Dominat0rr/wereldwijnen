package be.vdab.wereldwijnen.domain;

import org.springframework.format.annotation.NumberFormat;

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
@NamedQuery(name = "Wijn.findAll", query = "select w from Wijn w order by w.jaar")
@NamedQuery(name = "Wijn.findBySoort", query =
        "select w from Wijn w where w.soort.id = :soortid order by w.jaar")
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
    @NumberFormat(pattern = "0.00")
    private BigDecimal prijs;
    //private long inBestelling;
    @Version
    private long versie;
    public static final String FIND_ALL = "Wijn.findAll";
    public static final String FIND_BY_SOORT  = "Wijn.findBySoort";

    protected Wijn() { }

    public Wijn(Soort soort, short jaar, byte beoordeling, BigDecimal prijs/*, long inBestelling*/) {
        setSoort(soort);
        this.jaar = jaar;
        this.beoordeling = beoordeling;
        this.prijs = prijs;
        //this.inBestelling = inBestelling;
    }

    public Wijn(long id, Soort soort, short jaar, byte beoordeling, BigDecimal prijs/*, long inBestelling*/) {
        this.id = id;
        setSoort(soort);
        this.jaar = jaar;
        this.beoordeling = beoordeling;
        this.prijs = prijs;
        //this.inBestelling = inBestelling;
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

//    public long getInBestelling() {
//        return inBestelling;
//    }

    public long getVersie() {
        return versie;
    }

//    public void addBestelling(long aantal){
//        inBestelling += aantal;
//    }

    public void setSoort(Soort soort) throws NullPointerException {
        if (!soort.getWijnen().contains(this)) soort.add(this);
        this.soort = soort;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Wijn)) return false;
        Wijn wijn = (Wijn) o;
        return jaar == wijn.jaar &&
                beoordeling == wijn.beoordeling &&
                Objects.equals(soort, wijn.soort);
    }

    @Override
    public int hashCode() {
        return Objects.hash(soort, jaar, beoordeling);
    }
}
