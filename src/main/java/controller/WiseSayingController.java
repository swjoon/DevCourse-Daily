package controller;

import entity.dto.WiseSayingDto;
import service.WiseSayingService;
import util.InputUtils;

public class WiseSayingController {
    private static final WiseSayingService service = new WiseSayingService();

    public static void start() {
        service.programStart();
    }

    public void add() {
        String message = InputUtils.input("명언 : ");
        String author = InputUtils.input("작가 : ");
        service.add(message, author);
    }

    public void delete() {
        int no = Integer.parseInt(InputUtils.input("id= "));
        service.delete(no);
    }

    public void edit() {
        int no = Integer.parseInt(InputUtils.input("id= "));
        WiseSayingDto dto = service.findOne(no);
        if(dto != null) {
            System.out.println("명언(기존) : " + dto.getMessage());
            String message = InputUtils.input("명언 : ");
            System.out.println("작가(기존) : " + dto.getAuthor());
            String author = InputUtils.input("작가 : ");
            service.edit(no,message,author);
        }
    }

    public void print() {
        service.print();
    }

    public void build() {
        service.build();
    }

    public void end() {
        service.programEnd();
    }
}
