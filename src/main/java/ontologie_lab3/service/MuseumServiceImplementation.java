package ontologie_lab3.service;

import lombok.RequiredArgsConstructor;
import ontologie_lab3.utils.jsonquery.MuseumObject;
import ontologie_lab3.utils.jsonquery.MuseumQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MuseumServiceImplementation implements MuseumService {
    private final static List<String> NAMES_OF_ENGLAND = Arrays.asList("United Kingdom", "Kingdom of Great Britain", "United Kingdom of Great Britain and Ireland");

    private MuseumQueries queries = new MuseumQueries();

    @Override
    public List<MuseumObject> findSuitableObjects(String word, String country, String personBirthDateString, String personDeathDateString) {
        LocalDate birthDate = LocalDate.parse(personBirthDateString);
        LocalDate deathDate = LocalDate.parse(personDeathDateString);
        List<String> listOfDecades = getListOfDecades(birthDate.getYear(), deathDate.getYear());
        List<MuseumObject> foundMuseumObjectsForEachDecade = new ArrayList<>();
        if (NAMES_OF_ENGLAND.contains(country)) {
            country = "England";
        }
        for (String decade : listOfDecades) {
            String endDate = decade.split("-")[1];
            String startDate = decade.split("-")[0];
            List<MuseumObject> museumObjects = queries.search(word, startDate, endDate, country, true);

            int randomIndex = new Random().nextInt(museumObjects.size());
            MuseumObject randomObject = museumObjects.get(randomIndex);
            if (!foundMuseumObjectsForEachDecade.contains(randomObject)) {
                foundMuseumObjectsForEachDecade.add(randomObject);
            }
//            else {
//                foundMuseumObjectsForEachDecade.add(museumObjects.get(randomIndex>1?randomIndex-1:0));
//            }
        }
        return foundMuseumObjectsForEachDecade;
    }

    private List<String> getListOfDecades(int birthYear, int deathYear) {
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
}
