package be.vdab.wereldwijnen.repositories;

import be.vdab.wereldwijnen.domain.Land;
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
@Import(JpaLandRepository.class)
@Sql("/insertLand.sql")
public class JpaLandRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String LANDEN = "landen";
    @Autowired
    private JpaLandRepository landRepository;
    private Land land;

    @Before
    public void before() {
        land = new Land("test");
    }

    private long idVanTestLand() {
        return super.jdbcTemplate.queryForObject("select id from landen where naam = 'test'", Long.class);
    }

    @Test
    public void findById() {
        Optional<Land> optionalLand = landRepository.findById(idVanTestLand());
        assertThat(land.getNaam()).isEqualTo("test");
    }

    @Test
    public void findByOnbestaandeId() {
        assertThat(landRepository.findById(-1)).isNotPresent();
    }
}
