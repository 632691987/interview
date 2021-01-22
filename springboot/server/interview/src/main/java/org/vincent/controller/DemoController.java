package org.vincent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vincent.data.model.DemoEntity;
import org.vincent.service.DemoService;

@RestController
@RequestMapping("/api")
public class DemoController {

    @Autowired
    private DemoService demoService;

    /**
     * localhost:13256/api/demoentity/property1
     */
    @GetMapping("/demoentity/{productName}")
    public DemoEntity getDemoEntityProductName(@PathVariable(name="productName", required=true) String productName) {
        return demoService.getByProductName(productName);
    }

}
