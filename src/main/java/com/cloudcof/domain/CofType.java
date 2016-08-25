package com.cloudcof.domain;


import javax.persistence.*;

/**
 * Created by simon on 2016/8/22.
 */
@Entity
@Table(name = "cl_cof_type")
public class CofType {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "cof_name")
    private String cofName;
    private Integer milk;
    @Column(name = "cof_powder")
    private Integer cofPowder;
    private Integer price;
    private String intro;

    @Column(name = "img_desc")
    private String imgDesc;

    @Column(name = "owner_id")
    private Integer ownerId;

    @Column(name = "type_category")
    private Short typeCategory;

    @Column(name = "equal_point")
    private Integer equalPoint;

    @Column(name = "pay_by_point")
    private Boolean payByPoint;

    private Double discount;

    @Column(name = "milk_foam")
    private Integer milkFoam;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCofName() {
        return cofName;
    }

    public void setCofName(String cofName) {
        this.cofName = cofName;
    }

    public Integer getMilk() {
        return milk;
    }

    public void setMilk(Integer milk) {
        this.milk = milk;
    }

    public Integer getCofPowder() {
        return cofPowder;
    }

    public void setCofPowder(Integer cofPowder) {
        this.cofPowder = cofPowder;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getImgDesc() {
        return imgDesc;
    }

    public void setImgDesc(String imgDesc) {
        this.imgDesc = imgDesc;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Short getTypeCategory() {
        return typeCategory;
    }

    public void setTypeCategory(Short typeCategory) {
        this.typeCategory = typeCategory;
    }

    public Integer getEqualPoint() {
        return equalPoint;
    }

    public void setEqualPoint(Integer equalPoint) {
        this.equalPoint = equalPoint;
    }

    public Boolean getPayByPoint() {
        return payByPoint;
    }

    public void setPayByPoint(Boolean payByPoint) {
        this.payByPoint = payByPoint;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getMilkFoam() {
        return milkFoam;
    }

    public void setMilkFoam(Integer milkFoam) {
        this.milkFoam = milkFoam;
    }
}
