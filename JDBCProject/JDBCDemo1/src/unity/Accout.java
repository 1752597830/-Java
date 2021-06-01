package unity;

/**
 * @Auther: qinfeng
 * @Date: 2021/5/22 - 05 - 22 - 11:38
 * @Description: unity
 */
public class Accout {
    //private Integer Id;
    private String Username;
    private Double Pwd;

    public Accout() {
    }

    public Accout(String username, Double pwd) {
        Username = username;
        Pwd = pwd;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public Double getPwd() {
        return Pwd;
    }

    public void setPwd(Double pwd) {
        Pwd = pwd;
    }

    @Override
    public String toString() {
        return "Accout{" +
                "Username='" + Username + '\'' +
                ", Pwd=" + Pwd +
                '}';
    }
}
