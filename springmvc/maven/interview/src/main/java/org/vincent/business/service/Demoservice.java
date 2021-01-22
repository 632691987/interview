package org.vincent.business.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vincent.data.model.DemoEntity;
import org.vincent.data.repository.DemoRepository;
import org.vincent.dto.incomming.DemoInDTO;
import org.vincent.dto.outgoing.DemoOutDTO;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.vincent.basic.SystemConstant.DISPLAY_DATETIME_FORMAT;

@Service
public class Demoservice {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private DemoRepository repository;

    @Autowired
    public Demoservice(DemoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public DemoOutDTO insertDemo(String property1, Date property2) {

        logger.info("property1:{}", property1);
        logger.info("property2:{}", new SimpleDateFormat(DISPLAY_DATETIME_FORMAT).format(property2));

        DemoEntity entity = new DemoEntity();
        entity.setAcitve(false);
        entity.setInsertTime(property2);
        entity.setPrice(BigDecimal.valueOf(12.3d));
        entity.setProductName(property1);

        return demoEntityToDemoOutDTO(repository.save(entity));
    }

    @Transactional
    public DemoOutDTO insertDemo(DemoInDTO demoInDTO) {
        DemoEntity entity = demoInDTOToDemoEntity(demoInDTO);
        entity = repository.save(entity);
        return demoEntityToDemoOutDTO(entity);
    }

    @Transactional
    public boolean deleteDemo(DemoInDTO demoInDTO) {
        return repository.deleteByProductNameAndPrice(demoInDTO.getProductName(), demoInDTO.getPrice()) > 0;
    }

    @Transactional
    public List<DemoOutDTO> getDemoList() {
        return repository.findAll().stream().map(this::demoEntityToDemoOutDTO).collect(Collectors.toList());
    }

    private DemoOutDTO demoEntityToDemoOutDTO(DemoEntity entity) {
        DemoOutDTO dto = new DemoOutDTO();
        dto.setAcitve(entity.isAcitve());
        dto.setId(entity.getId());
        dto.setInsertTime(entity.getInsertTime());
        dto.setProductName(entity.getProductName());
        dto.setPrice(entity.getPrice());
        return dto;
    }

    private DemoEntity demoInDTOToDemoEntity(DemoInDTO demoInDTO) {
        DemoEntity entity = new DemoEntity();
        entity.setAcitve(demoInDTO.isAcitve());
        entity.setInsertTime(demoInDTO.getInsertTime());
        entity.setPrice(demoInDTO.getPrice());
        entity.setProductName(demoInDTO.getProductName());
        return entity;
    }


}
