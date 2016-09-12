package id.co.ppu.realmapp.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by Eric on 05-Sep-16.
 */
public class TrnCollectAddrPK extends RealmObject implements Serializable {
    @SerializedName("seqNo")
    private Long seqNo;

    @SerializedName("contractNo")
    private String contractNo;

    public Long getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Long seqNo) {
        this.seqNo = seqNo;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    @Override
    public String toString() {
        return "TrnCollectAddrPK{" +
                "seqNo=" + seqNo +
                ", contractNo='" + contractNo + '\'' +
                '}';
    }
}
