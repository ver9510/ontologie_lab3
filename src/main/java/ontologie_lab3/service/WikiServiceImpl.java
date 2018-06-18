package ontologie_lab3.service;

import com.bordercloud.sparql.EndpointException;
import lombok.RequiredArgsConstructor;
import ontologie_lab3.utils.sparql.Converter;
import ontologie_lab3.utils.sparql.Person;
import ontologie_lab3.utils.sparql.SparqlExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WikiServiceImpl implements WikiService {
    @Override
    public List<Person> getPerson(String name) {
        SparqlExecutor executor = new SparqlExecutor();
        ArrayList<Person> listOfFoundPeople = null;
        try {
            listOfFoundPeople = Converter.convertToPerson(executor.searchPersonQuickWithDatesAndImage(name));
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
        ArrayList<Person> listOfFoundPeople = null;
        try {
            if (country.equalsIgnoreCase("England")) {
                listOfFoundPeople = Converter.convertToPerson(executor.searchByYearsCountryAndSexForEngland(sex, splittedDates[0], splittedDates[1]));
            } else {
                listOfFoundPeople = Converter.convertToPerson(executor.searchByYearsCountryAndSex(country, sex, splittedDates[0], splittedDates[1]));
            }
        } catch (EndpointException e) {
            e.printStackTrace();
        }
        return listOfFoundPeople;
    }

    private String getStartAndEndDates(String dressYear) {
        String[] dates = dressYear.split(" ");
        if (dates.length > 1) {
            int randomIndex = new Random(dates.length).nextInt();
            String[] splittedByHyphen = dates[randomIndex].split("-");
            if (splittedByHyphen.length > 1) {
                return dates[randomIndex];
            } else {
                return getDecadeBySingleYear(dressYear);
            }
        } else {
            String[] splittedByHyphen = dressYear.split("-");
            if (splittedByHyphen.length > 1) {
                return dressYear;
            } else {
                return getDecadeBySingleYear(dressYear);
            }
        }

    }

    private String getDecadeBySingleYear(String dressYear) {
        String start;
        String end;
        Integer year = Integer.valueOf(dressYear);
        start = String.valueOf(year / 10 * 10);
        end = String.valueOf(year / 10 * 10 + 10);
        return start + "-" + end;
    }
}
