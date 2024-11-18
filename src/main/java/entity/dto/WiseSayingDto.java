package entity.dto;

import entity.WiseSaying;

public class WiseSayingDto {

    private final String content;
    private final String author;

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public WiseSayingDto(WiseSaying entity) {
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
