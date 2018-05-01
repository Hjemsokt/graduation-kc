package cn.kc.graduation.rss.model;


import java.io.Serializable;
import java.util.Date;

/**
 * 出库记录
 */
public class StockOutDO implements Serializable {

    /**
     * 出库记录ID
     */
    private Long id;

    /**
     * 客户ID
     */
    private Long customerID;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 商品ID
     */
    private Long goodID;

    /**
     * 商品名称
     */
    private String goodName;

    /**
     * 出库仓库ID
     */
    private Long repositoryID;

    /**
     * 商品出库数量
     */
    private long number;

    /**
     * 出库日期
     */
    private Date time;

    /**
     * 出库经手人
     */
    private String personInCharge;// 经手人

    public Long getRepositoryID() {
        return repositoryID;
    }

    public void setRepositoryID(Long repositoryID) {
        this.repositoryID = repositoryID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Long getGoodID() {
        return goodID;
    }

    public void setGoodID(Long goodID) {
        this.goodID = goodID;
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
        return "StockOutDO [id=" + id + ", customerID=" + customerID + ", customerName=" + customerName + ", goodID="
                + goodID + ", goodName=" + goodName + ", repositoryID=" + repositoryID + ", number=" + number
                + ", time=" + time + ", personInCharge=" + personInCharge + "]";
    }

}
