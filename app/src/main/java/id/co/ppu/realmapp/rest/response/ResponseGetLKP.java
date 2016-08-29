package id.co.ppu.realmapp.rest.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import id.co.ppu.realmapp.pojo.LKPData;
import id.co.ppu.realmapp.pojo.User;

public class ResponseGetLKP extends ResponseBasic implements Serializable {

    @SerializedName("data")
    private LKPData data;

    public LKPData getData() {
        return data;
    }

    public void setData(LKPData data) {
        this.data = data;
    }
}
