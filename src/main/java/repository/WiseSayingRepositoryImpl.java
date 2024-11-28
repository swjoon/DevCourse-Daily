package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.dto.Url;
import entity.WiseSaying;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

public class WiseSayingRepositoryImpl implements WiseSayingRepository{
    private int id;
    private final ObjectMapper om = new ObjectMapper();
    private final TreeMap<Integer, WiseSaying> wiseSayingMap = new TreeMap<>(Collections.reverseOrder());
    private static final String RESOURCE_PATH = "src/main/resources/db/wiseSaying";

    // 시작시 데이터 로드
    public WiseSayingRepositoryImpl() {
        checkFile();
        loadAllData();
    }

    // 파일이 없으면 생성
    private void checkFile() {
        File folder = new File(RESOURCE_PATH);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    // 저장된 데이터 불러오기
    private void loadAllData() {
        loadLastId();
        loadWiseSayings();
    }

    // 최신 ID 불러오기
    public void loadLastId() {
        File textFile = new File(RESOURCE_PATH, "lastId.txt");
        if (textFile.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(textFile))) {
                id = Integer.parseInt(br.readLine().trim());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            id = 1;
        }
    }

    // DB에 저장된 값 불러오기
    public void loadWiseSayings() {
        File jsonFile = new File(RESOURCE_PATH, "data.json");
        if (jsonFile.exists() && jsonFile.length() > 0) {
            try {
                wiseSayingMap.putAll(om.readValue(jsonFile, new TypeReference<TreeMap<Integer, WiseSaying>>() {}));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean exists(int no) {
        return wiseSayingMap.containsKey(no);
    }

    public WiseSaying findById(int no) {
        return wiseSayingMap.get(no);
    }

    public List<WiseSaying> findAll() {
        return wiseSayingMap.values().stream().toList();
    }

    // 추가
    public int add(String content, String author) {
        int currentId = id++;
        WiseSaying wiseSaying = new WiseSaying(currentId, content, author);
        wiseSayingMap.put(currentId, wiseSaying);
        saveFile(currentId, wiseSaying);
        saveLastId();
        return currentId;
    }

    // 삭제
    public void delete(int no) {
        wiseSayingMap.remove(no);
        deleteFile(no);
    }

    // 수정
    public void edit(int no, String content, String author) throws IOException {
        WiseSaying updatedWiseSaying = new WiseSaying(no, content, author);
        wiseSayingMap.replace(no, updatedWiseSaying);
        saveFile(no, updatedWiseSaying);
    }

    public List<WiseSaying> searchList(Url url){
        if(url.getQuery().get("keywordType").equals("content")) {
            return findAll().stream().filter(w -> w.getContent().contains(url.getQuery().get("keyword"))).toList();
        } else if(url.getQuery().get("keywordType").equals("author")){
            return findAll().stream().filter( w-> w.getAuthor().contains(url.getQuery().get("keyword"))).toList();
        }
        return null;
    }

    // 파일 빌드
    public void fileBuild() {
        File jsonFile = new File(RESOURCE_PATH, "data.json");
        try {
            om.writeValue(jsonFile, wiseSayingMap);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 파일 저장
    private void saveFile(int id, WiseSaying wiseSaying) {
        File file = new File(RESOURCE_PATH, id + ".json");
        try {
            om.writeValue(file, wiseSaying);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 파일 삭제
    private void deleteFile(int id) {
        File file = new File(RESOURCE_PATH, id + ".json");
        if (file.exists() && !file.delete()) {
            System.err.println("Failed to delete file: " + file.getName());
        }
    }

    // 최신 회차 번호 저장
    public void saveLastId() {
        File textFile = new File(RESOURCE_PATH, "lastId.txt");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(textFile))) {
            bw.write(String.valueOf(id));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

