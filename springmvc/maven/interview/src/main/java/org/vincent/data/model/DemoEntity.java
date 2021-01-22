package org.vincent.data.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="demo")
public class DemoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="price")
    private BigDecimal price = BigDecimal.ZERO;

    @Column(name="time", nullable = false)
    private Date insertTime;

    @Column(name="name", nullable=false)
    private String productName;

    @Column(name="active", nullable=false)
    private boolean isAcitve;

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

    public boolean isAcitve() {
        return isAcitve;
    }

    public void setAcitve(boolean acitve) {
        isAcitve = acitve;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DemoEntity that = (DemoEntity) o;
        return id == that.id &&
                isAcitve == that.isAcitve &&
                Objects.equal(price, that.price) &&
                Objects.equal(insertTime, that.insertTime) &&
                Objects.equal(productName, that.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, price, insertTime, productName, isAcitve);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("price", price)
                .add("insertTime", insertTime)
                .add("productName", productName)
                .add("isAcitve", isAcitve)
                .toString();
    }
}
