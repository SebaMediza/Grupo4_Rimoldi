package com.rimoldi.practico1.model;
import java.util.List;

import lombok.Data;

@Data
public class Juego {
    private String imgUrl;
    private String title;
    private Developer developer;
    private Publisher publisher;
    private String released;
    private String description;
    private List<Tag> tags;
    private AllReviews allReviews;
    private String price;
    private List<DLC> dlcs;

    @Override
    public String toString() {
        return "Juego{" +
                "imgUrl='" + imgUrl + '\'' +
                ", title='" + title + '\'' +
                ", developer=" + developer +
                ", publisher=" + publisher +
                ", released='" + released + '\'' +
                ", description='" + description + '\'' +
                ", tags=" + tags +
                ", allReviews=" + allReviews +
                ", price='" + price + '\'' +
                ", dlcs=" + dlcs +
                '}';
    }
}
