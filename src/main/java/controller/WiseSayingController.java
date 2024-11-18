package controller;

import entity.dto.WiseSayingDto;
import service.WiseSayingService;
import util.InputUtils;

public class WiseSayingController {
    private static final WiseSayingService service = new WiseSayingService();

    // 프로그램 시작
    public void start() {
        service.programStart();
    }

    // 명언 추가
    public void add() {
        String content = InputUtils.input("명언 : ");
        String author = InputUtils.input("작가 : ");
        service.add(content, author);
    }

    // 명언 삭제
    public void delete() {
        int no = Integer.parseInt(InputUtils.input("id= "));
        service.delete(no);
    }

    // 명언 수정
    public void edit() {
        int no = Integer.parseInt(InputUtils.input("id= "));
        WiseSayingDto dto = service.findOne(no);
        if (dto != null) {
            System.out.println("명언(기존) : " + dto.getContent());
            String content = InputUtils.input("명언 : ");
            System.out.println("작가(기존) : " + dto.getAuthor());
            String author = InputUtils.input("작가 : ");
            service.edit(no, content, author);
        }
    }

    // 목록 출력
    public void print() {
        service.print();
    }

    // 중간 저장 (빌드)
    public void build() {
        service.build();
    }

    // 프로그랢 종료
    public void end() {
        service.programEnd();
    }
}
