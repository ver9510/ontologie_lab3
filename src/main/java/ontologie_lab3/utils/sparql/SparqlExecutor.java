package ontologie_lab3.utils.sparql;


import com.bordercloud.sparql.EndpointException;

import java.util.HashMap;
import java.util.List;

public class SparqlExecutor {
    private EndpointExecutor executor = new EndpointExecutor();

    private static final int LIMIT = 10;

    public HashMap<String, HashMap> searchPersonQuickWithDates(String word) throws EndpointException {
        return executor.executeQuery(SparqlConstants.SPARQL_FIND_PERSON_QUICK_WITH_DATES, word, LIMIT);
    }

    public HashMap<String, HashMap> searchPerson(String word) throws EndpointException {
        return executor.executeQuery(SparqlConstants.SPARQL_FIND_PERSON, word, LIMIT);
    }

    public HashMap<String, HashMap> searchCountryByName(String country) throws EndpointException {
        return executor.executeQuery(SparqlConstants.SPARQL_FIND_COUNTRY_BY_NAME, country);
    }

    public HashMap<String, HashMap> searchCountryByCity(String city) throws EndpointException {
        return executor.executeQuery(SparqlConstants.SPARQL_FIND_COUNTRY_BY_CITY, city);
    }

    public HashMap<String, HashMap> searchByYearsCountryAndSex(String country, String sex, String dateOfbirth, String dateOfDeath) throws EndpointException {
        return executor.executeQuery(SparqlConstants.SPARQL_FIND_PEOPLE_BY_YEARS_COUNTRY_AND_SEX, "wd:" + country, sex, dateOfbirth, dateOfDeath, LIMIT);
    }

    public HashMap<String, HashMap> searchByYearsCountryAndSexForEngland(String sex, String dateOfbirth, String dateOfDeath) throws EndpointException {
        return executor.executeQuery(SparqlConstants.SPARQL_FIND_PEOPLE_BY_YEARS_AND_SEX_FOR_ENGLAND, sex, dateOfbirth, dateOfDeath, LIMIT);
    }




    public static void main(String[] args) {
        try {
            System.out.println("Тестирование запросов SPARQL");
            SparqlExecutor executor = new SparqlExecutor();
            List<Person> sm = Converter.convertToPerson(executor.searchPerson("Jane Austen"));
            for (Person person: sm) {
                System.out.println(person.toString());
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            //logger.catching(e);
        }
    }

}