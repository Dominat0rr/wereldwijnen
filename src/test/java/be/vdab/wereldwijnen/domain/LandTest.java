package be.vdab.wereldwijnen.domain;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class LandTest {
    private Soort soort;
    private Land land1;
    private Land land2;

    @Before
    public void before() {
        land1 = new Land("test1");
        land2 = new Land("test2");
        soort = new Soort("testSoort", land1);
    }

    @Test
    public void land1IsHetLandVanSoort() {
        assertThat(soort.getLand()).isEqualTo(land1);
        assertThat(land1.getSoorten()).containsOnly(soort);
    }

    @Test
    public void soortVerhuistNaarLand2() {
        assertThat(land2.add(soort)).isTrue();
        assertThat(land2.getSoorten()).containsOnly(soort);
        assertThat(soort.getLand()).isEqualTo(land2);
    }

    @Test
    public void eenNullSoortToevoegenMislukt() {
        assertThatNullPointerException().isThrownBy(() -> land1.add(null));
    }
}
