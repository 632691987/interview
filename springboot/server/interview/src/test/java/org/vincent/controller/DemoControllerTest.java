package org.vincent.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.vincent.data.model.DemoEntity;
import org.vincent.service.DemoService;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(DemoController.class)
public class DemoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DemoService service;

    @Test
    public void givenEmployees_whenGetEmployees_thenReturnJsonArray() throws Exception {
        String productName = "property1";
        DemoEntity alex = new DemoEntity();
        alex.setProductName(productName);
        given(service.getByProductName(productName)).willReturn(alex);
        mvc.perform(get("/api/demoentity/" + productName).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
}
