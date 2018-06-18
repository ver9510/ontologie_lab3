package ontologie_lab3.service;

import com.bordercloud.sparql.EndpointException;
import lombok.RequiredArgsConstructor;
import ontologie_lab3.model.Country;
import ontologie_lab3.utils.Constants;
import ontologie_lab3.utils.DateUtils;
import ontologie_lab3.utils.jsonquery.MuseumObject;
import ontologie_lab3.utils.jsonquery.MuseumQueries;
import ontologie_lab3.utils.sparql.Converter;
import ontologie_lab3.utils.sparql.SparqlExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MuseumServiceImplementation implements MuseumService {

    private MuseumQueries queries = new MuseumQueries();

    @Override
    public List<MuseumObject> findSuitableObjects(String word, String country, String personBirthDateString, String personDeathDateString) {
        LocalDate birthDate = LocalDate.parse(personBirthDateString);
        LocalDate deathDate = LocalDate.parse(personDeathDateString);
        List<String> listOfDecades = DateUtils.getListOfDecades(birthDate.getYear(), deathDate.getYear());
        List<MuseumObject> foundMuseumObjectsForEachDecade = new ArrayList<>();
        if (Constants.NAMES_OF_ENGLAND.contains(country)) {
            country = "England";
        }
        for (String decade: listOfDecades) {
            String endDate = decade.split("-")[1];
            String startDate = decade.split("-")[0];
            List<MuseumObject> museumObjects = queries.search(word, startDate, endDate, country, true);
            if (museumObjects.size() > 0) {
                int randomIndex = new Random().nextInt(museumObjects.size());
                MuseumObject randomObject = museumObjects.get(randomIndex);
                if (foundMuseumObjectsForEachDecade.stream().map(MuseumObject::getObjectNumber).noneMatch(on -> on.equals(randomObject.getObjectNumber()))) {
                    foundMuseumObjectsForEachDecade.add(randomObject);
                }
            }
        }
        return foundMuseumObjectsForEachDecade;
    }

    public List<MuseumObject> findMore(String word, String country, String dressYear) {
        String nameOfPlace = country.split(" ")[0];
        SparqlExecutor executor = new SparqlExecutor();
        Country foundCountry = null;
        if (country.contains("England")) {
            country = "England";
        } else {
            try {
                foundCountry = Converter.convertToCountry(executor.searchCountryByName(nameOfPlace));
                if (foundCountry == null) {
                    foundCountry = Converter.convertToCountry(executor.searchCountryByCity(nameOfPlace));
                }
            } catch (EndpointException e) {
                e.printStackTrace();
            }

            if (Constants.NAMES_OF_ENGLAND.contains(foundCountry.getName())) {
                country = "England";
            } else {
                country = foundCountry.getName();
            }
        }
        String[] splittedYears = dressYear.split("-");
        String startDate;
        String endDate;
        String decadeBySingleYear;
        if (splittedYears.length == 1) {
            decadeBySingleYear = DateUtils.getDecadeBySingleYear(dressYear);

        } else {
            decadeBySingleYear = DateUtils.getDecadeBySingleYear(splittedYears[0]);
        }
        String[] years = decadeBySingleYear.split("-");
        startDate = years[0];
        endDate = years[1];
        List<MuseumObject> museumObjects = queries.search(word, startDate, endDate, country, true);
        return museumObjects;
    }


}
