package id.co.ppu.realmapp.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by Eric on 04-Sep-16.
 */
public class TrnRVB extends RealmObject implements Serializable {
    @SerializedName("rvbNo")
    private String rvbNo;

    @SerializedName("officeCode")
    private String officeCode;

    @SerializedName("rvbDate")
    private Date rvbDate;

    @SerializedName("rvbOnHand")
    private String rvbOnHand;

    @SerializedName("rvNo")
    private String rvNo;

    @SerializedName("remarks")
    private String remarks;

    @SerializedName("rvbStatus")
    private String rvbStatus;

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


    public String getRvbNo() {
        return rvbNo;
    }

    public void setRvbNo(String rvbNo) {
        this.rvbNo = rvbNo;
    }

    public String getOfficeCode() {
        return officeCode;
    }

    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
    }

    public Date getRvbDate() {
        return rvbDate;
    }

    public void setRvbDate(Date rvbDate) {
        this.rvbDate = rvbDate;
    }

    public String getRvbOnHand() {
        return rvbOnHand;
    }

    public void setRvbOnHand(String rvbOnHand) {
        this.rvbOnHand = rvbOnHand;
    }

    public String getRvNo() {
        return rvNo;
    }

    public void setRvNo(String rvNo) {
        this.rvNo = rvNo;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRvbStatus() {
        return rvbStatus;
    }

    public void setRvbStatus(String rvbStatus) {
        this.rvbStatus = rvbStatus;
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
        return "TrnRVB{" +
                "rvbNo='" + rvbNo + '\'' +
                ", officeCode='" + officeCode + '\'' +
                ", rvbDate=" + rvbDate +
                ", rvbOnHand='" + rvbOnHand + '\'' +
                ", rvNo='" + rvNo + '\'' +
                ", remarks='" + remarks + '\'' +
                ", rvbStatus='" + rvbStatus + '\'' +
                ", startedTimestamp=" + startedTimestamp +
                ", createdTimestamp=" + createdTimestamp +
                ", createdBy='" + createdBy + '\'' +
                ", lastupdateBy='" + lastupdateBy + '\'' +
                ", lastupdateTimestamp=" + lastupdateTimestamp +
                '}';
    }
}
