package id.co.ppu.realmapp.pojo;

import java.util.List;

/**
 * Created by Eric on 01-Sep-16.
 */
public class UserData {
    private List<MstUser> user;
    private List<MstSecUser> secUser;

    public List<MstUser> getUser() {
        return user;
    }

    public void setUser(List<MstUser> user) {
        this.user = user;
    }

    public List<MstSecUser> getSecUser() {
        return secUser;
    }

    public void setSecUser(List<MstSecUser> secUser) {
        this.secUser = secUser;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "user=" + user +
                ", secUser=" + secUser +
                '}';
    }
}
