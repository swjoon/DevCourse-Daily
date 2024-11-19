package service;

import entity.WiseSaying;
import entity.dto.WiseSayingDto;
import repository.WiseSayingRepository;

import java.io.*;
import java.util.List;

public class WiseSayingService {
    private static final WiseSayingRepository repository = new WiseSayingRepository();

    // 번호에 맞는 명언 불러오기
    public WiseSayingDto findOne(int no) {
        if (repository.check(no)) {
            return new WiseSayingDto(repository.findById(no));
        }
        return null;
    }

    // 명언 추가
    public int add(String content, String author) {
        try {
            return repository.add(content, author);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 명언 삭제
    public boolean delete(int no) {
        if (repository.check(no)) {
            repository.delete(no);
            return true;
        }
        return false;
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
    public List<WiseSaying> loadList() {
        return repository.getList();
    }

    // 빌드
    public void build() {
        try {
            repository.fileBuild();
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
