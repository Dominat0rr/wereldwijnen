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
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "bonid")
    private long bestelbonId;

    private long wijnId;
    @NotNull(message = "Aantal mag niet leeg zijn")
    @Positive(message = "Aantal moet groter als 0 zijn")
    private long aantal;
    @Column(name = "prijs")
    private BigDecimal aankoopPrijs;

    protected BestelbonLijn() { }

    public BestelbonLijn(long bestelbonId, long wijnId, long aantal, BigDecimal aankoopPrijs) {
        this.bestelbonId = bestelbonId;
        this.wijnId = wijnId;
        this.aantal = aantal;
        this.aankoopPrijs = aankoopPrijs;
    }

    public long getBestelbonId() {
        return bestelbonId;
    }

    public long getWijn() {
        return wijnId;
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
