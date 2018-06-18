package ontologie_lab3.enums;

public enum Gender {
    MALE ("wd:Q6581097"),
    FEMALE ("wd:Q6581072");

    private final String name;

    private Gender(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        // (otherName == null) check is not needed because name.equals(null) returns false
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}
