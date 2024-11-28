package service;

import entity.dto.Page;
import entity.dto.Url;
import entity.WiseSaying;
import entity.dto.WiseSayingDto;
import lombok.RequiredArgsConstructor;
import repository.WiseSayingRepository;

import java.io.*;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class WiseSayingService {
    private final WiseSayingRepository repository;

    // 번호에 맞는 명언 불러오기
    public WiseSayingDto findOne(int no) {
        return repository.exists(no) ? new WiseSayingDto(repository.findById(no)) : null;
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
        } else {
            return false;
        }
    }

    // 명언 수정
    public void edit(int no, String content, String author) {
        try {
            repository.edit(no, content, author);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 검색 목록 출력
    public Page searchList(Url url) {
        Map<String, String> query = url.getQuery();

        // 전체 목록
        if (query == null) {
            return paging(1, "list", repository.findAll());
        }

        // 일반 검색
        if (query.get("page") == null) {
            return paging(1, "search", repository.searchList(url));
        }

        int page = Integer.parseInt(query.get("page"));

        // 전체 목록 + 페이지
        if (query.get("keywordType") == null && query.get("keyword") == null) {
            return paging(page, "list", repository.findAll());
        }

        // 일반 검색 + 페이지
        return paging(page, "search", repository.searchList(url));
    }

    // 페이지로 변환
    public Page paging(int page, String type, List<WiseSaying> list) {
        int pageSize = (int) Math.ceil((double) list.size() / 5);
        int startIndex = 5 * (page - 1);
        int index = list.size() % 5 == 0 ? 5 : list.size() % 5;

        // 잘못된 입력
        if (page > pageSize) {
            return null;
        }

        // 마지막 페이지
        if (page == pageSize) {
            return new Page(page, pageSize, type, list.subList(startIndex, startIndex + index));
        }

        // 일반 페이지
        return new Page(page, pageSize, type, list.subList(startIndex, startIndex + 5));

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
