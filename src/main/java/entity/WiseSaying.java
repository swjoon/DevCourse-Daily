package entity;

public class WiseSaying {

    private int no;
    private String content;
    private String author;

    public int getNo() {
        return no;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public WiseSaying() {
    }

    public WiseSaying(int no, String content, String author) {
        this.no = no;
        this.content = content;
        this.author = author;
    }
}
