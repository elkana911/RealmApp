package id.co.ppu.realmapp.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by Eric on 01-Sep-16.
 */
public class TrxCollectAddr extends RealmObject implements Serializable {
    @SerializedName("contractNo")
    private String contractNo;

    @SerializedName("seq")
    private SeqNo seq;

    @SerializedName("collAddr")
    private String collAddr;

    @SerializedName("collRt")
    private String collRt;

    @SerializedName("collRw")
    private String collRw;

    @SerializedName("collKelCode")
    private String collKelCode;

    @SerializedName("collKel")
    private String collKel;

    @SerializedName("collKecCode")
    private String collKecCode;

    @SerializedName("collKec")
    private String collKec;

    @SerializedName("collCityCode")
    private String collCityCode;

    @SerializedName("collCity")
    private String collCity;

    @SerializedName("collZip")
    private String collZip;

    @SerializedName("collSubZip")
    private String collSubZip;

    @SerializedName("collProvCode")
    private String collProvCode;

    @SerializedName("collProv")
    private String collProv;

    @SerializedName("collFixPhArea")
    private String collFixPhArea;

    @SerializedName("collFixPhone")
    private String collFixPhone;

    @SerializedName("collFaxArea")
    private String collFaxArea;

    @SerializedName("collFaximile")
    private String collFaximile;

    @SerializedName("collMobPhone")
    private String collMobPhone;

    @SerializedName("collEmail")
    private String collEmail;

    @SerializedName("collName")
    private String collName;

    @SerializedName("collMobPhone2")
    private String collMobPhone2;

    @SerializedName("collNickName")
    private String collNickName;

    @Override
    public String toString() {
        return "TrxCollectAddr{" +
                "contractNo='" + contractNo + '\'' +
                ", seq=" + seq +
                ", collAddr='" + collAddr + '\'' +
                ", collRt='" + collRt + '\'' +
                ", collRw='" + collRw + '\'' +
                ", collKelCode='" + collKelCode + '\'' +
                ", collKel='" + collKel + '\'' +
                ", collKecCode='" + collKecCode + '\'' +
                ", collKec='" + collKec + '\'' +
                ", collCityCode='" + collCityCode + '\'' +
                ", collCity='" + collCity + '\'' +
                ", collZip='" + collZip + '\'' +
                ", collSubZip='" + collSubZip + '\'' +
                ", collProvCode='" + collProvCode + '\'' +
                ", collProv='" + collProv + '\'' +
                ", collFixPhArea='" + collFixPhArea + '\'' +
                ", collFixPhone='" + collFixPhone + '\'' +
                ", collFaxArea='" + collFaxArea + '\'' +
                ", collFaximile='" + collFaximile + '\'' +
                ", collMobPhone='" + collMobPhone + '\'' +
                ", collEmail='" + collEmail + '\'' +
                ", collName='" + collName + '\'' +
                ", collMobPhone2='" + collMobPhone2 + '\'' +
                ", collNickName='" + collNickName + '\'' +
                '}';
    }
}
