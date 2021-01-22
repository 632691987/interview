package org.vincent.controller;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.vincent.business.service.Demoservice;
import org.vincent.dto.incomming.DemoInDTO;
import org.vincent.dto.outgoing.DemoOutDTO;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;

import static org.vincent.basic.SystemConstant.DISPLAY_DATETIME_FORMAT;

@RestController
@Validated
@RequestMapping("/demo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Democontroller {

    private Demoservice demoservice;

    @Autowired
    public Democontroller(Demoservice demoservice) {
        this.demoservice = demoservice;
    }

    /**
     * 127.0.0.1:22900/interview/demo/xxx?property2=2018-04-02T14:26
     */
    @PostMapping(value="{property1}")
    public DemoOutDTO insertDemo(@NotEmpty(message = "parameter 'property1' can not empty") @PathVariable String property1,
            @NotNull(message = "parameter 'property2' can not empty") @RequestParam @DateTimeFormat(pattern=DISPLAY_DATETIME_FORMAT) Date property2) {

        return demoservice.insertDemo(property1, property2);
    }

    @PostMapping
    public DemoOutDTO insertDemo(@NotNull @RequestBody DemoInDTO demoInDTO) {
        return demoservice.insertDemo(demoInDTO);
    }

    /**
     * 127.0.0.1:22900/interview/demo
     */
    @DeleteMapping
    public boolean deleteDemo(@NotNull @RequestBody DemoInDTO demoInDTO) {
        return demoservice.deleteDemo(demoInDTO);
    }

    @GetMapping
    public List<DemoOutDTO> getDemoList() {
        return demoservice.getDemoList();
    }

}
