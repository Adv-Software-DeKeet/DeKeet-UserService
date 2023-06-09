package jovisimons.dekeet.common.model;

import java.io.Serializable;

public class UserMsgName implements Serializable {
    private String uid;
    private String name;

    public UserMsgName(String uid, String name){
        this.uid = uid;
        this.name = name;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
    public String getUid() {
        return uid;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
