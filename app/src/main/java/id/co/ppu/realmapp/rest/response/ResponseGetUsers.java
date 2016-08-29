package id.co.ppu.realmapp.rest.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import id.co.ppu.realmapp.pojo.User;

public class ResponseGetUsers extends ResponseBasic implements Serializable {

    @SerializedName("data")
    private List<User> data;

    public List<User> getData() {
        return data;
    }

    public void setData(List<User> data) {
        this.data = data;
    }

}
