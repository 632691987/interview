package org.vincent.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.vincent.business.service.Demoservice;
import org.vincent.data.model.DemoEntity;
import org.vincent.dto.outgoing.DemoOutDTO;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(PowerMockRunner.class)
public class DemocontrollerUnitTest {

    @Mock
    private Demoservice demoservice;

    @InjectMocks
    private Democontroller democontroller;

    @Before
    public void setUp() {
        democontroller = new Democontroller(demoservice);
    }

    @Test
    public void testFunction1() {
        String property = givenAnProperty();
        Date date = givenAnDate();
        PowerMockito.when(demoservice.insertDemo(any(String.class), any(Date.class))).thenReturn(returnADemoOutDTO(property, date));

        DemoOutDTO demoOutDTO = democontroller.insertDemo(property, date);

        verify(demoservice, times(1)).insertDemo(eq(property), eq(date));
        Assert.assertTrue(demoOutDTO != null);
        Assert.assertTrue(demoOutDTO.getProductName().equalsIgnoreCase(property));
        Assert.assertTrue(demoOutDTO.getInsertTime().equals(date));
    }

    public DemoOutDTO returnADemoOutDTO(String property1, Date property2) {
        DemoOutDTO demoOutDTO = new DemoOutDTO();
        demoOutDTO.setAcitve(false);
        demoOutDTO.setInsertTime(property2);
        demoOutDTO.setPrice(BigDecimal.valueOf(12.3d));
        demoOutDTO.setProductName(property1);

        return demoOutDTO;
    }

    public DemoEntity returnADemoEntity(String property1, Date property2) {
        DemoEntity entity = new DemoEntity();
        entity.setAcitve(false);
        entity.setInsertTime(property2);
        entity.setPrice(BigDecimal.valueOf(12.3d));
        entity.setProductName(property1);

        return entity;
    }

    public String givenAnProperty() {
        return "property1";
    }

    public Date givenAnDate() {
        return Calendar.getInstance().getTime();
    }
}
