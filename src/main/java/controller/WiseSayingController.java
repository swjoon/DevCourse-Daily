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
        int id = service.add(content, author);
        System.out.println(id + "번 명언이 등록되었습니다.");
    }

    // 명언 삭제
    public void delete() {
        int no = Integer.parseInt(InputUtils.input("id= "));
        if (service.delete(no)) {
            System.out.println(no + "번 명언이 삭제되었습니다.");
            return;
        }
        System.out.println(no + "번 명언은 존재하지 않습니다.");
    }

    // 명언 수정
    public void edit() {
        int no = Integer.parseInt(InputUtils.input("id= "));
        WiseSayingDto dto = service.findOne(no);
        if (dto == null) {
            System.out.println(no + "번 명언은 존재하지 않습니다.");
            return;
        }
        System.out.println("명언(기존) : " + dto.getContent());
        String content = InputUtils.input("명언 : ");
        System.out.println("작가(기존) : " + dto.getAuthor());
        String author = InputUtils.input("작가 : ");
        service.edit(no, content, author);
    }

    // 목록 출력
    public void print() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        service.loadList().stream().forEach(System.out::println);
    }

    // 중간 저장 (빌드)
    public void build() {
        service.build();
        System.out.println("data.json 파일의 내용이 갱신되었습니다.");
    }

    // 프로그랢 종료
    public void end() {
        service.programEnd();
    }
}
