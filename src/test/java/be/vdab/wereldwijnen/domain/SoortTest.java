package be.vdab.wereldwijnen.domain;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

public class SoortTest {
    private Soort soort1;
    private Soort soort1Dubbel;
    private Soort soort2;
    private Land land1;
    private Land land2;

    @Before
    public void before() {
        land1 = new Land("testL1");
        land2 = new Land("testL2");
        soort1 = new Soort("testS1", land1);
        soort1Dubbel = new Soort("testS1", land1);
        soort2 = new Soort("testS2", land1);
    }

    @Test
    public void land1IsHetLandVanSoort1() {
        assertThat(soort1.getLand()).isEqualTo(land1);
    }

    @Test
    public void soortVerhuistNaarLand2() {
        assertThat(land2.add(soort1)).isTrue();
        assertThat(land1.getSoorten().isEmpty());
        assertThat(soort1.getLand()).isEqualTo(land2);
    }

    @Test
    public void eenNullLandInDeSetterMislukt() {
        assertThatNullPointerException().isThrownBy(() -> soort1.setLand(null));
    }

    @Test
    public void eenNullLandInDeConstructorMislukt() {
        assertThatNullPointerException().isThrownBy(()-> new Soort("test", null));
    }

    @Test
    public void soortenZijnGelijkAlsHunNaamEnLandGelijkZijn() {
        assertThat(soort1).isEqualTo(soort1Dubbel);
    }

    @Test
    public void soortenZijnVerschillendAlsHunNaamEnLandVerschillendZijn() {
        assertThat(soort1).isNotEqualTo(soort2);
    }

    @Test
    public void eenSoortVerschiltVanNull() {
        assertThat(soort1).isNotEqualTo(null);
    }

    @Test
    public void eenSoortVerschiltVanEenAnderTypeObject() {
        assertThat(soort1).isNotEqualTo("");
    }

    @Test
    public void gelijkeSoortenGevenDezelfdeHashCode() {
        assertThat(soort1).hasSameHashCodeAs(soort1Dubbel);
    }

    @Test
    public void meerdereSoortenKunnenTotHetzelfdeLandBehoren() {
        assertThat(land1.getSoorten()).containsOnly(soort1, soort2);
    }
}
