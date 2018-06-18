package ontologie_lab3.service;

import com.bordercloud.sparql.EndpointException;
import ontologie_lab3.model.Country;
import ontologie_lab3.utils.Constants;
import ontologie_lab3.utils.DateUtils;
import ontologie_lab3.utils.sparql.Converter;
import ontologie_lab3.utils.sparql.Person;
import ontologie_lab3.utils.sparql.SparqlExecutor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class WikiServiceImpl implements WikiService {
    @Override
    public List<Person> getPerson(String name) {
        SparqlExecutor executor = new SparqlExecutor();
        List<Person> listOfFoundPeople = null;
        try {
            listOfFoundPeople = Converter.convertToPerson(executor.searchPerson(name));
        } catch (EndpointException e) {
            e.printStackTrace();
        }
        return listOfFoundPeople;
    }

    @Override
    public List<Person> search(String country, String sex, String dressYear) {
        String startAndEndDatesString = getStartAndEndDates(dressYear);
        String[] splittedDates = startAndEndDatesString.split("-");
        SparqlExecutor executor = new SparqlExecutor();
        LocalDateTime startDate = LocalDate.of(Integer.valueOf(splittedDates[0]), 1, 1).atStartOfDay();
        LocalDateTime endDate = LocalDate.of(Integer.valueOf(splittedDates[1]), 12, 31).atStartOfDay();


        List<Person> listOfFoundPeople = new ArrayList<>();
        List<String> listOfFoundPeopleNames;
        try {
            String nameOfPlace = country.split(" ")[0];
            Country foundCountry = Converter.convertToCountry(executor.searchCountryByName(nameOfPlace));
            if (foundCountry == null) {
                foundCountry = Converter.convertToCountry(executor.searchCountryByCity(nameOfPlace));
            }
            if (foundCountry != null) {
                if (country.contains("England") || Constants.NAMES_OF_ENGLAND.contains(foundCountry.getName())) {
                    listOfFoundPeopleNames = Converter.convertToListOfPeople(executor.searchByYearsCountryAndSexForEngland(sex, startDate.format(DateTimeFormatter.ISO_DATE_TIME), endDate.format(DateTimeFormatter.ISO_DATE_TIME)));
                } else {
                    listOfFoundPeopleNames = Converter.convertToListOfPeople(executor.searchByYearsCountryAndSex(foundCountry.getId(), sex, startDate.format(DateTimeFormatter.ISO_DATE_TIME), endDate.format(DateTimeFormatter.ISO_DATE_TIME)));
                }
                for (String name: listOfFoundPeopleNames) {
                    listOfFoundPeople.addAll(getPerson(name));
                }
            }
        } catch (EndpointException e) {
            e.printStackTrace();
        }
        return listOfFoundPeople;
    }

    private String getStartAndEndDates(String dressYear) {
        String[] splittedByHyphen = dressYear.split("-");
        if (splittedByHyphen.length > 1) {
            if (splittedByHyphen[0].equals(splittedByHyphen[1])) {
                return DateUtils.getDecadeBySingleYear(splittedByHyphen[0]);
            }
            return dressYear;
        } else {
            return DateUtils.getDecadeBySingleYear(dressYear);
        }


    }


}
