package id.co.ppu.realmapp.pojo;

import java.util.List;

/**
 * Created by Eric on 29-Aug-16.
 */
public class LKPData{

    private TrnLDVHeader header;
    private List<TrnLDVDetails> details;
    private List<TrnCollectAddr> address;
    private List<TrnContractBuckets> buckets;
    private List<TrnRVB> rvb;

    public TrnLDVHeader getHeader() {
        return header;
    }

    public void setHeader(TrnLDVHeader header) {
        this.header = header;
    }

    public List<TrnLDVDetails> getDetails() {
        return details;
    }

    public void setDetails(List<TrnLDVDetails> details) {
        this.details = details;
    }

    public List<TrnCollectAddr> getAddress() {
        return address;
    }

    public void setAddress(List<TrnCollectAddr> address) {
        this.address = address;
    }

    public List<TrnContractBuckets> getBuckets() {
        return buckets;
    }

    public void setBuckets(List<TrnContractBuckets> buckets) {
        this.buckets = buckets;
    }

    public List<TrnRVB> getRvb() {
        return rvb;
    }

    public void setRvb(List<TrnRVB> rvb) {
        this.rvb = rvb;
    }
}
