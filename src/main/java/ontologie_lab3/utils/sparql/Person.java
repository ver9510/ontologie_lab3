package ontologie_lab3.utils.sparql;


import ontologie_lab3.utils.Constants;
import ontologie_lab3.utils.ImageLoader;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Person {
    private String person;
    private String personLabel;
    private String description;
    private String link;
    private LocalDate dateOfBirth;
    private LocalDate dateOfDeath;
    private String imagePath;
    private String imageUrl;
    private String sex;
    private String country;


    public Person(String person, String personLabel, String description, String link, String dateOfBirth, String dateOfDeath, String imgUrl, String sex, String country) {
        this.person = person;
        this.personLabel = personLabel;
        this.description = description;
        this.link = link;
        this.dateOfBirth = LocalDate.parse(dateOfBirth, DateTimeFormatter.ISO_DATE_TIME);
        this.dateOfDeath = LocalDate.parse(dateOfDeath, DateTimeFormatter.ISO_DATE_TIME);
        if (!(imgUrl == null)) {
            this.imageUrl = imgUrl;
        } else {
            this.imageUrl = Constants.NO_IMAGE_PATH;
        }
        this.sex = sex;
        this.country = country;
    }

    public void loadImage(String imgUrl) {
        imagePath = ImageLoader.loadImage(imgUrl, StringUtils.replace(personLabel, " ", "_") + ".jpg");

    }

    public String getPerson() {
        return person;
    }

    public String getPersonLabel() {
        return personLabel;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public LocalDate getDateOfDeath() {
        return dateOfDeath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getSex() {
        return sex;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "Person{" +
                "person='" + person + '\'' +
                ", personLabel='" + personLabel + '\'' +
                ", description='" + description + '\'' +
                ", link='" + link + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", dateOfDeath=" + dateOfDeath +
                ", imagePath='" + imagePath + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", sex='" + sex + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
