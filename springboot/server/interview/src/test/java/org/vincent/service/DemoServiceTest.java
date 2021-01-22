package org.vincent.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.vincent.data.model.DemoEntity;
import org.vincent.data.repository.DemoRepository;

@RunWith(SpringRunner.class)
public class DemoServiceTest {

    @TestConfiguration
    static class DemoServiceTestContextConfiguration {

        @Bean
        public DemoService demoService() {
            return new DemoService();
        }
    }

    @Autowired
    private DemoService demoService;

    @MockBean
    private DemoRepository demoRepository;

    String productName = "property1";

    @Test
    public void whenValidName_thenEmployeeShouldBeFound() {
        DemoEntity found = demoService.getByProductName(productName);
        Assert.assertTrue(found.getProductName().equalsIgnoreCase(productName));
    }


}
