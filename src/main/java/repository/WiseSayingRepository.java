package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.WiseSaying;

import java.io.*;
import java.util.List;
import java.util.TreeMap;

public interface WiseSayingRepository {
    boolean exists(int no);

    WiseSaying findById(int no);

    int add(String content, String author);

    void delete(int no);

    void edit(int no, String content, String author) throws IOException;

    List<WiseSaying> findAll();

    void fileBuild();

    void saveLastId();
}
