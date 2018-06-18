package ontologie_lab3.utils;

import java.util.ArrayList;
import java.util.List;

public class DateUtils {
    public static List<String> getListOfDecades(int birthYear, int deathYear) {
        int startYear = birthYear / 10 * 10;
        int endYear = deathYear / 10 * 10 + 10;
        List<String> resultDecades = new ArrayList<>();
        int middleYear = startYear + 10;
        for (int i = startYear; i < endYear; i += 10) {
            String decade = i + "-" + middleYear;
            middleYear += 10;
            resultDecades.add(decade);
        }
        return resultDecades;
    }

    public static String getDecadeBySingleYear(String dressYear) {
        String start;
        String end;
        Integer year = Integer.valueOf(dressYear);
        start = String.valueOf(year / 10 * 10);
        end = String.valueOf(year / 10 * 10 + 10);
        return start + "-" + end;
    }
}
