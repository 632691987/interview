package org.vincent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vincent.data.model.DemoEntity;
import org.vincent.data.repository.DemoRepository;

@Service
public class DemoService {

    @Autowired
    private DemoRepository demoRepository;

    public DemoEntity getByProductName(String productName) {
        return demoRepository.findTopByProductName(productName).orElse(new DemoEntity());
    }

}
