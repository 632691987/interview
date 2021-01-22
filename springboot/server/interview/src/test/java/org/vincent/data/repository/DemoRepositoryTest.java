package org.vincent.data.repository;

import org.hamcrest.text.IsEqualIgnoringCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.vincent.data.model.DemoEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DemoRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    /**
     * The TestEntityManager provided by Spring Boot is an alternative to the standard JPA EntityManager
     * that provides methods commonly used when writing tests.
     */
    @Autowired
    private DemoRepository demoRepository;

    @Test
    public void whenFindByName_thenReturnEmployee() {
        // given
        DemoEntity alex = new DemoEntity();
        alex.setProductName("property1");
        alex.setPrice(BigDecimal.valueOf(12.3d));
        alex.setInsertTime(new Date());
        alex.setAcitve(true);

        // when
        Optional<DemoEntity> found = demoRepository.findTopByProductName("property1");

        // then
        Assert.assertThat(found.get().getProductName(), new IsEqualIgnoringCase(alex.getProductName()));
    }
}
