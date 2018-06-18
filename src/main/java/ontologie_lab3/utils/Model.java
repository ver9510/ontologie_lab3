package ontologie_lab3.utils;


import org.springframework.util.StringUtils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Model {
    private String person;
    private String personLabel;
    private String description;
    private String link;
    private LocalDate dateOfBirth;
    private LocalDate dateOfDeath;
    private String imagePath;
    private String imageUrl;


    public Model(String person, String personLabel, String description, String link, String dateOfBirth, String dateOfDeath, String imgUrl) {
        this.person = person;
        this.personLabel = personLabel;
        this.description = description;
        this.link = link;
        this.dateOfBirth = LocalDate.parse(dateOfBirth, DateTimeFormatter.ISO_DATE_TIME);
        this.dateOfDeath = LocalDate.parse(dateOfDeath, DateTimeFormatter.ISO_DATE_TIME);
        this.imageUrl = imgUrl;
        addImage(imgUrl);
    }

    private void addImage(String imgUrl) {
        imagePath = ImageLoader.loadImage(imgUrl,StringUtils.replace(personLabel, " ","_")+".jpg");

    }


    @Override
    public String toString() {
        return "Model{" +
                "person='" + person + '\'' +
                ", personLabel='" + personLabel + '\'' +
                ", description='" + description + '\'' +
                ", link='" + link + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", dateOfDeath=" + dateOfDeath +
                ", imagePath='" + imagePath + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
