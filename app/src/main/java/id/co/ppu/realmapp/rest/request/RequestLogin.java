package id.co.ppu.realmapp.rest.request;

/**
 * Created by Eric on 19-Aug-16.
 */
public class RequestLogin {
    private String id;
    private String pwd;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
