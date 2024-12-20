package entity.dto;

import entity.WiseSaying;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Page {
    private int page;
    private int pageSize;
    private String type;
    private List<WiseSaying> pageList;



    public String pageNumber() {
        StringBuilder sb = new StringBuilder();
        sb.append("페이지 : ");
        for (int i = 1; i <= pageSize; i++) {
            if (i == page) {
                sb.append("[").append(i).append("]").append(" / ");
            } else {
                sb.append(i).append(" / ");
            }
        }
        sb.deleteCharAt(sb.length()-2);
        return sb.toString();
    }
}
