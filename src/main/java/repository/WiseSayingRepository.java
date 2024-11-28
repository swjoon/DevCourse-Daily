package repository;

import entity.dto.Url;
import entity.WiseSaying;

import java.io.*;
import java.util.List;

public interface WiseSayingRepository {
    boolean exists(int no);

    WiseSaying findById(int no);

    int add(String content, String author);

    void delete(int no);

    void edit(int no, String content, String author) throws IOException;

    List<WiseSaying> findAll();

    List<WiseSaying> searchList(Url url);

    void fileBuild();

    void saveLastId();

}
