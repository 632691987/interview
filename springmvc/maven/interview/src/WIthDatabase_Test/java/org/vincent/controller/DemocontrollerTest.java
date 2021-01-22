package org.vincent.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.vincent.BasicConfig;
import org.vincent.dto.incomming.DemoInDTO;
import org.vincent.dto.outgoing.DemoOutDTO;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BasicConfig.class})
@TestPropertySource("classpath:application.test.properties")
@ActiveProfiles("functiontest")
public class DemocontrollerTest {

    @Autowired
    private Democontroller democontroller;

    @Test
    public void testCreateDemo1() {
        String uniqueTestName = "testCreateDemo1";
        DemoOutDTO demoOutDTO = democontroller.insertDemo(uniqueTestName,Calendar.getInstance().getTime());
        Assert.assertNotNull(demoOutDTO.getProductName());

        List<DemoOutDTO> list = null;
        list = democontroller.getDemoList();
        Assert.assertTrue(list.size() == 1);

        deleteDemo(uniqueTestName);
        list = democontroller.getDemoList();
        Assert.assertTrue(list.size() == 0);
    }

    @Test
    public void testCreateDemo2() {
        String uniqueTestName = "testCreateDemo2";
        DemoInDTO demoInDTO = new DemoInDTO();
        demoInDTO.setInsertTime(Calendar.getInstance().getTime());
        demoInDTO.setProductName(uniqueTestName);
        demoInDTO.setPrice(BigDecimal.valueOf(12.3d));

        DemoOutDTO demoOutDTO = democontroller.insertDemo(demoInDTO);
        Assert.assertNotNull(demoOutDTO.getProductName());

        deleteDemo(uniqueTestName);
    }

    @Test
    public void testDeleteDemo() {
        String uniqueTestName = "org.vincent.controller.DemocontrollerTest.testDeleteDemo";

        democontroller.insertDemo(uniqueTestName,Calendar.getInstance().getTime());

        DemoInDTO demoInDTO = new DemoInDTO();
        demoInDTO.setProductName(uniqueTestName);
        demoInDTO.setPrice(BigDecimal.valueOf(12.3d));

        boolean result = democontroller.deleteDemo(demoInDTO);
        Assert.assertTrue(result);
    }

    private void deleteDemo(String uniqueTestName) {
        DemoInDTO demoInDTO = new DemoInDTO();
        demoInDTO.setProductName(uniqueTestName);
        demoInDTO.setPrice(BigDecimal.valueOf(12.3d));

        boolean result = democontroller.deleteDemo(demoInDTO);
        Assert.assertTrue(result);
    }
}
