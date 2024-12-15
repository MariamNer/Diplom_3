package dto;

public class ResponseUser {
    public String success;
    public String accessToken;
    public String refreshToken;
    public User user;

    public ResponseUser(String success, String accessToken, String refreshToken, User user) {
        this.success = success;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.user = user;
    }

    @Override
    public String toString() {
        return "ResponseUser{" +
                "success='" + success + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", user=" + user +
                '}';
    }
}
