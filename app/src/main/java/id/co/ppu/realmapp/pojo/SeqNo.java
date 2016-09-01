package id.co.ppu.realmapp.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by Eric on 01-Sep-16.
 */
public class SeqNo extends RealmObject implements Serializable {
    @SerializedName("seqNo")
    private Long seqNo;

    public Long getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Long seqNo) {
        this.seqNo = seqNo;
    }

    @Override
    public String toString() {
        return "SeqNo{" +
                "seqNo=" + seqNo +
                '}';
    }
}
