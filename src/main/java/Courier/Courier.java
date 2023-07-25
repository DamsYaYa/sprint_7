package courier;
import java.util.Date;


public class Courier {
    private String login;
    private String password;
    private String firstName;

    public Courier(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    public Courier(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Courier() {
    }

    static Date currentDate = new Date();
    static long timeMilli = currentDate.getTime();

    public static Courier getRandomCourier(){
        return new Courier("Konohe" + timeMilli, "morty", "Saske");
    }

    public static Courier from(Courier courier){
        return new Courier(courier.getLogin(), courier.getPassword());
    }
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
