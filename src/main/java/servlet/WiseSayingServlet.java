package servlet;

import controller.WiseSayingController;
import entity.dto.Url;
import lombok.RequiredArgsConstructor;
import utils.InputUtils;

@RequiredArgsConstructor
public class WiseSayingServlet {
    private final WiseSayingController controller;

    public void start() {

        System.out.println("== 명언 앱 ==");

        while (true) {
            Url url = InputUtils.url_parse(InputUtils.input("명령) "));
            switch (url.getResource()) {
                case "등록" -> controller.add();
                case "삭제" -> controller.delete(url);
                case "수정" -> controller.edit(url);
                case "목록" -> controller.print(url);
                case "빌드" -> controller.build();
                case "종료" -> {
                    controller.end();
                    return;
                }
                default -> {
                    System.out.println("잘못된 입력입니다.");
                }
            }
        }
    }
}
