package be.vdab.wereldwijnen.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

//@Entity
//@Table(name = "bestelbonlijnen")
@Embeddable
public class BestelbonLijn implements Serializable {
    private static final long serialVersionUID = 1L;
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wijnid")
    private Wijn wijn;
    @NotNull(message = "Aantal mag niet leeg zijn")
    @Positive(message = "Aantal moet groter als 0 zijn")
    private long aantal;
    @Column(name = "prijs")
    private BigDecimal aankoopPrijs;

    protected BestelbonLijn() { }

    public BestelbonLijn(Wijn wijn, long aantal) {
        this.wijn = wijn;
        this.aantal = aantal;
        aankoopPrijs = wijn.getPrijs();
    }

//    public long getId() {
//        return id;
//    }

    public Wijn getWijn() {
        return wijn;
    }

    public long getAantal() {
        return aantal;
    }

    public BigDecimal getAankoopPrijs() {
        return aankoopPrijs;
    }

    public BigDecimal getSubTotaal() {
        return aankoopPrijs.multiply(BigDecimal.valueOf(aantal));
    }
}
