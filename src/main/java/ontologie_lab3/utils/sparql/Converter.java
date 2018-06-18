package ontologie_lab3.utils.sparql;

import com.bordercloud.sparql.EndpointException;

import java.util.ArrayList;
import java.util.HashMap;

public class Converter {
    public static ArrayList<Person> convertToPerson(HashMap<String, HashMap> data) throws EndpointException {
        ArrayList<Person> result = new ArrayList<>();
        ArrayList listOfResults = ((ArrayList<HashMap<String, String>>)data
                .get("result").get("rows"));
        for(HashMap<String, String> item : (ArrayList<HashMap<String, String>>)listOfResults) {
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
        return result;
    }
}
