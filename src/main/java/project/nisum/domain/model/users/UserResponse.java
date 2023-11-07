package project.nisum.domain.model.users;

import java.io.Serializable;

public class UserResponse implements Serializable {
    private String id;
    private String created;
    private String modified;
    private String lastLogin;
    private String token;
    private Boolean isActive;

    public UserResponse() {
    }

    private UserResponse(String id, String created, String modified, String lastLogin, String token, Boolean isActive) {
        this.id = id;
        this.created = created;
        this.modified = modified;
        this.lastLogin = lastLogin;
        this.token = token;
        this.isActive = isActive;
    }

    public static UserResponse create(String id, String created, String modified, String lastLogin, String token, Boolean isActive) {
        UserResponse userResponse = new UserResponse(id, created, modified, lastLogin, token, isActive);
        return userResponse;
    }
}
