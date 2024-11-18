package handler;

import controller.WiseSayingController;
import util.InputUtils;

public class WiseSayingHandler {
    private static final WiseSayingController controller = new WiseSayingController();

    public void start() {

        controller.start();

        System.out.println("== 명언 앱 ==");

        while (true) {
            switch (InputUtils.input("명령) ")) {
                case "등록" -> controller.add();
                case "삭제" -> controller.delete();
                case "수정" -> controller.edit();
                case "목록" -> controller.print();
                case "빌드" -> controller.build();
                default -> {
                    controller.end();
                    return;
                }
            }
        }
    }
}
