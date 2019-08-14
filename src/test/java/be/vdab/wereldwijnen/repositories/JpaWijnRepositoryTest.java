package be.vdab.wereldwijnen.repositories;

import be.vdab.wereldwijnen.domain.Land;
import be.vdab.wereldwijnen.domain.Soort;
import be.vdab.wereldwijnen.domain.Wijn;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql("/insertLand.sql")
@Sql("/insertSoort.sql")
@Sql("/insertWijn.sql")
@Import(JpaWijnRepository.class)
public class JpaWijnRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String WIJNEN = "wijnen";
    private Land land;
    private Soort soort;
    private Wijn wijn;
    @Autowired
    private JpaWijnRepository wijnRepository;
    @Autowired
    private EntityManager manager;

    private long idVanTestWijn() {
        return super.jdbcTemplate.queryForObject("select id from wijnen where jaar = 1985", Long.class);
    }

    @Before
    public void before() {
        land = new Land("test");
        soort = new Soort("test", land);
        wijn = new Wijn(soort, (short) 1985, (byte) 3, BigDecimal.TEN);
    }

    @Test
    public void findById() {
        Optional<Wijn> optionalWijn = wijnRepository.findById(idVanTestWijn());
        assertThat(((Wijn) optionalWijn.get()).getJaar()).isEqualTo(1985);
    }

    @Test
    public void findByOnbestaandId() {
        assertFalse(wijnRepository.findById(-1).isPresent());
    }

    @Test
    public void create() {
        wijnRepository.create(wijn);
        assertThat(super.countRowsInTableWhere(WIJNEN, "id = " + wijn.getId())).isOne();
    }

    @Test
    public void delete() {
        long id = idVanTestWijn();
        wijnRepository.delete(id);
        assertThat(super.countRowsInTableWhere(WIJNEN, "id=" + id)).isZero();
    }
}
