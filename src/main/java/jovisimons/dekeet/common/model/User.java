package jovisimons.dekeet.common.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, scope = User.class)
public class User implements Serializable {
    @Indexed(unique = true)
    private String uid;

    private String name;
    private String email;
    private String authProvider;
    private String role;

    public User() {
    }
    public User(String uid, String email, String name, String authProvider, String role){
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.authProvider = authProvider;
        this.role = role;
    }

    public String getUid() {
        return this.uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getAuthProvider() {
        return authProvider;
    }
    public void setAuthProvider(String authProvider) {
        this.authProvider = authProvider;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
}
