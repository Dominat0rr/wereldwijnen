package be.vdab.wereldwijnen.repositories;

import be.vdab.wereldwijnen.domain.Land;
import be.vdab.wereldwijnen.domain.Soort;
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

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(JpaSoortRepository.class)
@Sql("/insertLand.sql")
@Sql("/insertSoort.sql")
public class JpaSoortRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String SOORTEN = "soorten";
    @Autowired
    private JpaSoortRepository soortRepository;
    private Soort soort;

    @Before
    public void before() {
        soort = new Soort("test", new Land("test"));
    }

    private long idVanTestSoort() {
        return super.jdbcTemplate.queryForObject("select id from soorten where naam = 'test'", Long.class);
    }

    @Test
    public void findById() {
        Optional<Soort> optionalSoort = soortRepository.findById(idVanTestSoort());
        assertThat(((Soort) optionalSoort.get()).getNaam()).isEqualTo("test");
    }

    @Test
    public void findByOnbestaandeId() {
        assertThat(soortRepository.findById(-1)).isNotPresent();
    }

    @Test
    public void create() {
        soortRepository.create(soort);
        assertThat(super.countRowsInTableWhere(SOORTEN, "id = " + soort.getId())).isOne();
    }

    @Test
    public void delete() {
        long id = idVanTestSoort();
        soortRepository.delete(id);
        assertThat(super.countRowsInTableWhere(SOORTEN, "id=" + id)).isZero();
    }
}
