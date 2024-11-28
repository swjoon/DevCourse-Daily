package service;

import entity.WiseSaying;
import entity.dto.WiseSayingDto;
import lombok.RequiredArgsConstructor;
import repository.WiseSayingRepository;

import java.io.*;
import java.util.List;

@RequiredArgsConstructor
public class WiseSayingService {
    private final WiseSayingRepository repository;

    // 번호에 맞는 명언 불러오기
    public WiseSayingDto findOne(int no) {
        if (repository.exists(no)) {
            return new WiseSayingDto(repository.findById(no));
        }
        return null;
    }

    // 명언 추가
    public int add(String content, String author) {
            return repository.add(content, author);
    }

    // 명언 삭제
    public boolean delete(int no) {
        if (repository.exists(no)) {
            repository.delete(no);
            return true;
        }
        return false;
    }

    // 명언 수정
    public void edit(int no, String content, String author) {
        try {
            repository.edit(no, content, author);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 목록 출력
    public List<WiseSaying> loadList() {
        return repository.findAll();
    }

    // 빌드
    public void build() {
            repository.fileBuild();
    }

    // 종료
    public void programEnd() {
            repository.saveLastId();
            repository.fileBuild();
    }
}
