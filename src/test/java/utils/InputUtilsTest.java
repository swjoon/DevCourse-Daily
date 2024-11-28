package utils;

import entity.dto.Url;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class InputUtilsTest {

    @Test
    @DisplayName("삭제 url")
    public void url_삭제(){
        String test = "삭제?id=1";
        Url url = InputUtils.url_parse(test);

        assertThat(url.getResource()).isEqualTo("삭제");
        assertThat(url.getQuery().get("id")).isEqualTo("1");
    }

    @Test
    @DisplayName("목록 검색 url")
    public void url_목록1(){
        String test = "목록?keywordType=content&keyword=과거";
        Url url = InputUtils.url_parse(test);

        assertThat(url.getResource()).isEqualTo("목록");
        assertThat(url.getQuery().get("keywordType")).isEqualTo("content");
        assertThat(url.getQuery().get("keyword")).isEqualTo("과거");
    }

    @Test
    @DisplayName("목록 검색 url")
    public void url_목록2(){
        String test = "목록?keywordType=author&keyword=작자";
        Url url = InputUtils.url_parse(test);

        assertThat(url.getResource()).isEqualTo("목록");
        assertThat(url.getQuery().get("keywordType")).isEqualTo("author");
        assertThat(url.getQuery().get("keyword")).isEqualTo("작자");
    }
}
