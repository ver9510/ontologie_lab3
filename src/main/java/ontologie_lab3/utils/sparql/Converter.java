package ontologie_lab3.utils.sparql;

import com.bordercloud.sparql.EndpointException;
import ontologie_lab3.model.Country;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Converter {
    public static List<Person> convertToPerson(HashMap<String, HashMap> data) throws EndpointException {
        List<Person> result = new ArrayList<>();
        List listOfResults = ((ArrayList<HashMap<String, String>>) data
                .get("result").get("rows"));
        if (listOfResults.size() > 10) {
            listOfResults = listOfResults.subList(0, 10);
        }
        for (HashMap<String, String> item: (List<HashMap<String, String>>) listOfResults) {
            if (result.stream().noneMatch(p -> p.getPerson().equals(item.get("person")))) {
                result.add(new Person(
                        item.get("person"),
                        item.get("personLabel"),
                        item.get("description"),
                        item.get("link"),
                        item.get("date_of_birth"),
                        item.get("date_of_death"),
                        item.get("image"),
                        item.get("sex_or_genderLabel"),
                        item.get("country_of_citizenshipLabel"))
                );
            }
        }
        return result;
    }
    public static List<String> convertToListOfPeople(HashMap<String, HashMap> data) throws EndpointException {
        List<String> result = new ArrayList<>();
        List listOfResults = ((ArrayList<HashMap<String, String>>) data
                .get("result").get("rows"));
        for (HashMap<String, String> item: (List<HashMap<String, String>>) listOfResults) {
            result.add(item.get("personLabel"));
        }
        return result;
    }
    public static Country convertToCountry(HashMap<String, HashMap> data) {
        ArrayList<Person> result = new ArrayList<>();
        ArrayList listOfResults = ((ArrayList<HashMap<String, String>>) data
                .get("result").get("rows"));
        if (listOfResults.size() == 0) {
            return null;
        }
        HashMap<String, String> countryObject = ((ArrayList<HashMap<String, String>>) listOfResults).get(0);
        String[] parts = countryObject.get("country").split("/");
        String id = parts[parts.length - 1];
        Country country = new Country(id, countryObject.get("countryLabel"));
        return country;
    }
}
