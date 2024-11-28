package controller;

import entity.dto.Page;
import entity.dto.Url;
import entity.dto.WiseSayingDto;
import lombok.RequiredArgsConstructor;
import service.WiseSayingService;
import utils.InputUtils;

import java.util.Map;

@RequiredArgsConstructor
public class WiseSayingController {
    private final WiseSayingService service;

    // 명언 추가
    public void add() {
        int id = service.add(InputUtils.input("명언 : "), InputUtils.input("작가 : "));
        System.out.println(id + "번 명언이 등록되었습니다.");
    }

    // 명언 삭제
    public void delete(Url url) {
        if (url.getQuery() == null) return;
        int id = Integer.parseInt(url.getQuery().get("id"));
        if (service.delete(id)) {
            System.out.println(id + "번 명언이 삭제되었습니다.");
        } else {
            System.out.println(id + "번 명언은 존재하지 않습니다.");
        }
    }

    // 명언 수정
    public void edit(Url url) {
        if (url.getQuery() == null) return;
        int id = Integer.parseInt(url.getQuery().get("id"));
        WiseSayingDto dto = service.findOne(id);
        if (dto != null) {
            System.out.println("명언(기존) : " + dto.getContent());
            String content = InputUtils.input("명언 : ");
            System.out.println("작가(기존) : " + dto.getAuthor());
            String author = InputUtils.input("작가 : ");
            service.edit(id, content, author);
        } else {
            System.out.println(id + "번 명언은 존재하지 않습니다.");
        }
    }

    // 목록 출력
    public void print(Url url) {
        try {
            Page page = service.searchList(url);
            if (page == null) {
                System.out.println("잘못된 입력입니다.");
            }
            if (page.getType().equals("search")) {
                System.out.println("----------------------");
                System.out.println("검색타입 : " + url.getQuery().get("keywordType"));
                System.out.println("검색어 : " + url.getQuery().get("keyword"));
                System.out.println("----------------------");
            }
            System.out.println("번호 / 작가 / 명언");
            System.out.println("----------------------");
            page.getPageList().forEach(System.out::println);
            System.out.println("----------------------");
            System.out.println(page.pageNumber());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 중간 저장 (빌드)
    public void build() {
        service.build();
        System.out.println("data.json 파일의 내용이 갱신되었습니다.");
    }

    // 프로그램 종료
    public void end() {
        service.programEnd();
    }
}
