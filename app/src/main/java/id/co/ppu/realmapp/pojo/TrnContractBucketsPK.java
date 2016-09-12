package id.co.ppu.realmapp.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by Eric on 05-Sep-16.
 */
public class TrnContractBucketsPK extends RealmObject implements Serializable {
    @SerializedName("periode")
    private String periode;

    @SerializedName("contractNo")
    private String contractNo;

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    @Override
    public String toString() {
        return "TrnContractBucketsPK{" +
                "periode='" + periode + '\'' +
                ", contractNo='" + contractNo + '\'' +
                '}';
    }
}
