package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.WiseSaying;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WiseSayingRepository {
    private int id;
    private final ObjectMapper om = new ObjectMapper();
    private Map<Integer, WiseSaying> wiseSayingMap = new HashMap<>();
    private static final String resourcePath = "src/main/resources/db/wiseSaying";

    public boolean check(int no) {
        return wiseSayingMap.containsKey(no);
    }

    public WiseSaying findById(int no) {
        return wiseSayingMap.get(no);
    }

    public List<WiseSaying> getList() {
        return wiseSayingMap.values().stream().toList();
    }

    public int add(String content, String author) throws IOException {
        int no = id++;
        WiseSaying entity = new WiseSaying(no, content, author);
        wiseSayingMap.put(no, entity);
        File file = new File(resourcePath, no + ".json");
        om.writeValue(file, entity);
        return no;
    }

    public void delete(int no) {
        wiseSayingMap.remove(no);
        File file = new File(resourcePath, no + ".json");
        if (file.exists()) {
            boolean result = file.delete();
        }
    }

    public void edit(int no, String content, String author) throws IOException {
        WiseSaying entity = new WiseSaying(no, content, author);
        wiseSayingMap.replace(no, entity);
        File file = new File(resourcePath, no + ".json");
        om.writeValue(file, entity);
    }

    public void fileBuild() throws IOException {
        File jsonFile = new File(resourcePath, "data.json");
        om.writeValue(jsonFile, wiseSayingMap);
    }

    // 세이브 파일 불러오기
    public void loadLastId() throws IOException {
        File textFile = new File(resourcePath, "lastId.txt");
        if (textFile.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(textFile))) {
                id = Integer.parseInt(br.readLine().trim());
            }
        } else {
            id = 1;
        }

        File jsonFile = new File(resourcePath, "data.json");

        if (jsonFile.exists() && !(jsonFile.length() == 0)) {
            wiseSayingMap = om.readValue(jsonFile, new TypeReference<Map<Integer, WiseSaying>>() {
            });
        }
    }

    // 최신 회차 번호 저장
    public void saveLastId() throws IOException {
        File textFile = new File(resourcePath, "lastId.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(textFile));
        bw.write(String.valueOf(id));
        bw.close();
    }
}
