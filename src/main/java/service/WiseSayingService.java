package service;

import entity.WiseSaying;
import entity.dto.WiseSayingDto;
import repository.WiseSayingRepository;

import java.io.*;

public class WiseSayingService {
    private static final WiseSayingRepository repository = new WiseSayingRepository();

    // 번호에 맞는 명언 불러오기
    public WiseSayingDto findOne(int no) {
        if (repository.check(no)) {
            return new WiseSayingDto(repository.findById(no));
        } else {
            System.out.println(no + "번 명언은 존재하지 않습니다.");
            return null;
        }
    }

    // 명언 추가
    public void add(String content, String author) {
        try {
            int id = repository.add(content, author);
            System.out.println(id + "번 명언이 등록되었습니다.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 명언 삭제
    public void delete(int no) {
        if (repository.check(no)) {
            repository.delete(no);
            System.out.println(no + "번 명언이 삭제되었습니다.");
        } else {
            System.out.println(no + "번 명언은 존재하지 않습니다.");
        }
    }

    // 명언 수정
    public void edit(int no, String content, String author) {
        try {
            repository.edit(no, content, author);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 목록 출력
    public void print() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        for (WiseSaying ws : repository.getList()) {
            System.out.println(ws.getNo() + " / " + ws.getAuthor() + " / " + ws.getContent());
        }
    }

    // 빌드
    public void build() {
        try {
            repository.fileBuild();
            System.out.println("data.json 파일의 내용이 갱신되었습니다.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 시작
    public void programStart() {
        try {
            repository.loadLastId();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 종료
    public void programEnd() {
        try {
            repository.saveLastId();
            repository.fileBuild();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
