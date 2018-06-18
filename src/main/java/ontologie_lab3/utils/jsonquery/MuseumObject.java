package ontologie_lab3.utils.jsonquery;

public class MuseumObject {
    private String name;
    private String objectNumber;
    private String category;
    private String country;
    private String author;
    private String year;
    private String summary;
    private String materials;
    private String imageUrl;
    private String imagePath;

    public MuseumObject(String name, String objectNumber, String category, String country, String author, String yearStart, String yearEnd, String summary, String materials) {
        this.name = name;
        this.objectNumber = objectNumber;
        this.category = category;
        this.country = country;
        this.author = author;
        if (yearEnd.equals(yearStart)) {
            this.year = yearStart;
        } else {
            this.year = yearStart + "-" + yearEnd;
        }

        this.summary = summary;
        this.materials = materials;
    }

    public MuseumObject(String name, String objectNumber, String country, String author, String year) {
        this.name = name;
        this.objectNumber = objectNumber;
        this.country = country;
        this.author = author;
        this.year = year;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getCountry() {
        return country;
    }

    public String getAuthor() {
        return author;
    }

    public String getYear() {
        return year;
    }

    public String getSummary() {
        return summary;
    }

    public String getMaterials() {
        return materials;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getObjectNumber() {
        return objectNumber;
    }
}
