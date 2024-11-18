import controller.WiseSayingController;
import handler.WiseSayingHandler;

public class WiseSayingApplication {

    private static final WiseSayingHandler app = new WiseSayingHandler();

    public static void main(String[] args) {

        app.start();

    }
}
