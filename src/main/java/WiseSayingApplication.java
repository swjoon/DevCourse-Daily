import config.AppConfig;
import servlet.WiseSayingServlet;

public class WiseSayingApplication {

    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        WiseSayingServlet app = appConfig.wiseSayingServlet();
        app.start();
    }
}
