package be.vdab.wereldwijnen.domain;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

public class WijnTest {
    private Wijn wijn1;
    private Wijn wijn1Dubbel;
    private Wijn wijn2;
    private Soort soort1;
    private Soort soort2;
    private Land land1;
    private Land land2;

    @Before
    public void before() {
        land1 = new Land("TestL1");
        land2 = new Land("TestL2");
        soort1 = new Soort("testS1", land1);
        soort2 = new Soort("testS2", land2);
        wijn1 = new Wijn(soort1, (short)1995, (byte)3, BigDecimal.TEN, 10);
        wijn1Dubbel = new Wijn(soort1, (short)1995, (byte)3, BigDecimal.TEN, 10);
        wijn2 = new Wijn(soort1, (short)1985, (byte)5, BigDecimal.TEN, 10);
    }

    @Test
    public void eenNullSoortInDeConstructorMislukt() {
        assertThatNullPointerException().isThrownBy(()-> new Wijn(null, (short)1985, (byte)5, BigDecimal.TEN, 10));
    }

    @Test
    public void wijnenZijnGelijkAlsHunSoortJaarEnBeoordelingGelijkZijn() {
        assertThat(wijn1).isEqualTo(wijn1Dubbel);
    }

    @Test
    public void wijnenZijnVerschillendAlsHunSoortJaarEnBeoordelingVerschillendZijn() {
        assertThat(wijn1).isNotEqualTo(wijn2);
    }

    @Test
    public void eenWijnVerschiltVanNull() {
        assertThat(wijn1).isNotEqualTo(null);
    }

    @Test
    public void eenWijnVerschiltVanEenAnderTypeObject() {
        assertThat(wijn1).isNotEqualTo("");
    }

    @Test
    public void gelijkeWijnenGevenDezelfdeHashCode() {
        assertThat(wijn1).hasSameHashCodeAs(wijn1Dubbel);
    }

    @Test
    public void meerdereWijnenKunnenTotDezelfdeSoortBehoren() {
        assertThat(soort1.getWijnen()).containsOnly(wijn1, wijn2);
    }

    @Test
    public void wijn1KomtVoorInSoort1() {
        assertThat(wijn1.getSoort()).isEqualTo(soort1);
        assertThat(soort1.getWijnen()).contains(wijn1);
    }

    @Test
    public void wijn1VerhuistNaarSoort2() {
        wijn1.setSoort(soort2);
        assertThat(wijn1.getSoort()).isEqualTo(soort2);
        assertThat(soort1.getWijnen().isEmpty());
        assertThat(soort2.getWijnen()).contains(wijn1);
    }

    @Test
    public void eenNullSoortInDeSetterMislukt() {
        assertThatNullPointerException().isThrownBy(() -> wijn1.setSoort(null));
    }
}
