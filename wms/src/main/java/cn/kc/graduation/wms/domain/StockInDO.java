package cn.kc.graduation.wms.domain;


import java.io.Serializable;
import java.util.Date;

/**
 * 入库记录
 */
public class StockInDO implements Serializable {

    /**
     * 入库记录
     */
    private Long id;

    /**
     * 供应商ID
     */
    private Long supplierID;

    /**
     * 供应商名称
     */
    private String supplierName;

    /**
     * 商品ID
     */
    private Long goodID;

    /**
     * 商品名称
     */
    private String goodName;

    /**
     * 入库仓库ID
     */
    private Long repositoryID;

    /**
     * 入库数量
     */
    private long number;

    /**
     * 入库日期
     */
    private Date time;

    /**
     * 入库经手人
     */
    private String personInCharge;

    public Long getRepositoryID() {
        return repositoryID;
    }

    public void setRepositoryID(Long repositoryID) {
        this.repositoryID = repositoryID;
    }

    public Long getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(Long supplierID) {
        this.supplierID = supplierID;
    }

    public Long getGoodID() {
        return goodID;
    }

    public void setGoodID(Long goodID) {
        this.goodID = goodID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getPersonInCharge() {
        return personInCharge;
    }

    public void setPersonInCharge(String personInCharge) {
        this.personInCharge = personInCharge;
    }

    @Override
    public String toString() {
        return "StockInDO [id=" + id + ", supplierID=" + supplierID + ", supplierName=" + supplierName + ", goodID="
                + goodID + ", goodName=" + goodName + ", repositoryID=" + repositoryID + ", number=" + number
                + ", time=" + time + ", personInCharge=" + personInCharge + "]";
    }

}
