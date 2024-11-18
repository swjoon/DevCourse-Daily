package entity.dto;

import entity.WiseSaying;

public class WiseSayingDto {

    private final String message;
    private final String author;

    public String getMessage() {
        return message;
    }

    public String getAuthor() {
        return author;
    }

    public WiseSayingDto(WiseSaying entity) {
        this.message = entity.getMessage();
        this.author = entity.getAuthor();
    }
}
