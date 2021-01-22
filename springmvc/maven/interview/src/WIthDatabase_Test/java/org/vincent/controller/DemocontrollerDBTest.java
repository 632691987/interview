package org.vincent.controller;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.vincent.BasicConfig;
import org.vincent.dto.outgoing.DemoOutDTO;

import java.util.List;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BasicConfig.class})
@TestPropertySource("classpath:application.hsqldb.properties")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class })
@ActiveProfiles("hsqldb")
public class DemocontrollerDBTest {


    @Autowired
    private Democontroller democontroller;

    @Test
    @DatabaseSetup("/sampleData.xml")
    public void testGetDemoList() {
        List<DemoOutDTO> demoList = democontroller.getDemoList();
        Assert.assertTrue(demoList.size()==9);
    }

}
