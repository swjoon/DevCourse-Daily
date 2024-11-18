package entity;

public class WiseSaying {

    private int no;
    private String message;
    private String author;

    public int getNo() {
        return no;
    }

    public String getMessage() {
        return message;
    }

    public String getAuthor() {
        return author;
    }

    public WiseSaying() {
    }

    public WiseSaying(int no, String message, String author) {
        this.no = no;
        this.message = message;
        this.author = author;
    }

    @Override
    public String toString() {
        return no + " / " + author + " / " + message;
    }
}
