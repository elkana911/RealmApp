package id.co.ppu.realmapp.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Eric on 04-Sep-16.
 */
public class TrnContractBuckets extends RealmObject implements Serializable{

    @SerializedName("pk")
    private TrnContractBucketsPK pk;

    @SerializedName("officeCode")
    private String officeCode;

    @SerializedName("supervisorId")
    private String supervisorId;

    @SerializedName("collectorId")
    private String collectorId;

    @SerializedName("contractStatus")
    private String contractStatus;

    @SerializedName("top")
    private Long top;

    @SerializedName("instNo")
    private Long instNo;

    @SerializedName("dueDate")
    private Date dueDate;

    @SerializedName("ovdDueDate")
    private Date ovdDueDate;

    @SerializedName("ovdInstNo")
    private Long ovdInstNo;

    @SerializedName("dpd")
    private Long dpd;

    @SerializedName("custNo")
    private String custNo;

    @SerializedName("custName")
    private String custName;

    @SerializedName("prncAmt")
    private Long prncAmt;

    @SerializedName("intrAmt")
    private Long intrAmt;

    @SerializedName("prncAc")
    private Long prncAc;

    @SerializedName("rvNo")
    private String rvNo;

    @SerializedName("penaltyAC")
    private Long penaltyAC;

    @SerializedName("prncOTS")
    private Long prncOTS;

    @SerializedName("startedTimestamp")
    private Date startedTimestamp;

    @SerializedName("createdTimestamp")
    private Date createdTimestamp;

    @SerializedName("createdBy")
    private String createdBy;

    @SerializedName("lastupdateBy")
    private String lastupdateBy;

    @SerializedName("lastupdateTimestamp")
    private Date lastupdateTimestamp;

    public TrnContractBucketsPK getPk() {
        return pk;
    }

    public void setPk(TrnContractBucketsPK pk) {
        this.pk = pk;
    }

    public String getOfficeCode() {
        return officeCode;
    }

    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
    }

    public String getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(String supervisorId) {
        this.supervisorId = supervisorId;
    }

    public String getCollectorId() {
        return collectorId;
    }

    public void setCollectorId(String collectorId) {
        this.collectorId = collectorId;
    }

    public String getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(String contractStatus) {
        this.contractStatus = contractStatus;
    }

    public Long getTop() {
        return top;
    }

    public void setTop(Long top) {
        this.top = top;
    }

    public Long getInstNo() {
        return instNo;
    }

    public void setInstNo(Long instNo) {
        this.instNo = instNo;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getOvdDueDate() {
        return ovdDueDate;
    }

    public void setOvdDueDate(Date ovdDueDate) {
        this.ovdDueDate = ovdDueDate;
    }

    public Long getOvdInstNo() {
        return ovdInstNo;
    }

    public void setOvdInstNo(Long ovdInstNo) {
        this.ovdInstNo = ovdInstNo;
    }

    public Long getDpd() {
        return dpd;
    }

    public void setDpd(Long dpd) {
        this.dpd = dpd;
    }

    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public Long getPrncAmt() {
        return prncAmt;
    }

    public void setPrncAmt(Long prncAmt) {
        this.prncAmt = prncAmt;
    }

    public Long getIntrAmt() {
        return intrAmt;
    }

    public void setIntrAmt(Long intrAmt) {
        this.intrAmt = intrAmt;
    }

    public Long getPrncAc() {
        return prncAc;
    }

    public void setPrncAc(Long prncAc) {
        this.prncAc = prncAc;
    }

    public String getRvNo() {
        return rvNo;
    }

    public void setRvNo(String rvNo) {
        this.rvNo = rvNo;
    }

    public Long getPenaltyAC() {
        return penaltyAC;
    }

    public void setPenaltyAC(Long penaltyAC) {
        this.penaltyAC = penaltyAC;
    }

    public Long getPrncOTS() {
        return prncOTS;
    }

    public void setPrncOTS(Long prncOTS) {
        this.prncOTS = prncOTS;
    }

    public Date getStartedTimestamp() {
        return startedTimestamp;
    }

    public void setStartedTimestamp(Date startedTimestamp) {
        this.startedTimestamp = startedTimestamp;
    }

    public Date getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(Date createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastupdateBy() {
        return lastupdateBy;
    }

    public void setLastupdateBy(String lastupdateBy) {
        this.lastupdateBy = lastupdateBy;
    }

    public Date getLastupdateTimestamp() {
        return lastupdateTimestamp;
    }

    public void setLastupdateTimestamp(Date lastupdateTimestamp) {
        this.lastupdateTimestamp = lastupdateTimestamp;
    }

    @Override
    public String toString() {
        return "TrnContractBuckets{" +
                "pk=" + pk +
                ", officeCode='" + officeCode + '\'' +
                ", supervisorId='" + supervisorId + '\'' +
                ", collectorId='" + collectorId + '\'' +
                ", contractStatus='" + contractStatus + '\'' +
                ", top=" + top +
                ", instNo=" + instNo +
                ", dueDate=" + dueDate +
                ", ovdDueDate=" + ovdDueDate +
                ", ovdInstNo=" + ovdInstNo +
                ", dpd=" + dpd +
                ", custNo='" + custNo + '\'' +
                ", custName='" + custName + '\'' +
                ", prncAmt=" + prncAmt +
                ", intrAmt=" + intrAmt +
                ", prncAc=" + prncAc +
                ", rvNo='" + rvNo + '\'' +
                ", penaltyAC=" + penaltyAC +
                ", prncOTS=" + prncOTS +
                ", startedTimestamp=" + startedTimestamp +
                ", createdTimestamp=" + createdTimestamp +
                ", createdBy='" + createdBy + '\'' +
                ", lastupdateBy='" + lastupdateBy + '\'' +
                ", lastupdateTimestamp=" + lastupdateTimestamp +
                '}';
    }

}
