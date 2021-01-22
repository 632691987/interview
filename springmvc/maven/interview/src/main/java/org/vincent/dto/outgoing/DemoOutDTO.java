package org.vincent.dto.outgoing;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

import static org.vincent.basic.SystemConstant.DISPLAY_DATETIME_FORMAT;

public class DemoOutDTO {

    private int id;

    private BigDecimal price;

    private boolean isAcitve;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern= DISPLAY_DATETIME_FORMAT)
    private Date insertTime;

    private String productName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isAcitve() {
        return isAcitve;
    }

    public void setAcitve(boolean acitve) {
        isAcitve = acitve;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
