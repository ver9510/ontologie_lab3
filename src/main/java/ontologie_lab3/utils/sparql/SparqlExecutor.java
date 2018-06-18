package ontologie_lab3.utils.sparql;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import com.bordercloud.sparql.*;

public class SparqlExecutor {
    private EndpointExecutor executor = new EndpointExecutor();

    private static final int LIMIT = 10;

    public HashMap<String, HashMap> searchPersonQuickWithDates(String word) throws EndpointException {
        return executor.executeQuery(SparqlConstants.SPARQL_FIND_PERSON_QUICK_WITH_DATES, word, LIMIT);
    }

    public HashMap<String, HashMap> searchPersonQuickWithDatesAndImage(String word) throws EndpointException {
        return executor.executeQuery(SparqlConstants.SPARQL_FIND_PERSON_QUICK_WITH_DATES_AND_IMAGE, word, LIMIT);
    }

    public HashMap<String, HashMap> searchCountryByName(String country) throws EndpointException {
        return executor.executeQuery(SparqlConstants.SPARQL_FIND_COUNTRY_BY_NAME, country, LIMIT);
    }

    public HashMap<String, HashMap> searchCountryByCity(String city) throws EndpointException {
        return executor.executeQuery(SparqlConstants.SPARQL_FIND_COUNTRY_BY_CITY, city, LIMIT);
    }

    public HashMap<String, HashMap> searchByYearsCountryAndSex(String country, String sex, String dateOfbirth, String dateOfDeath) throws EndpointException {
        return executor.executeQuery(SparqlConstants.SPARQL_FIND_PEOPLE_BY_YEARS_COUNTRY_AND_SEX, "wd:"+country, sex, dateOfbirth, dateOfDeath, LIMIT);
    }

    public HashMap<String, HashMap> searchByYearsCountryAndSexForEngland(String sex, String dateOfbirth, String dateOfDeath) throws EndpointException {
        return executor.executeQuery(SparqlConstants.SPARQL_FIND_PEOPLE_BY_YEARS_AND_SEX_FOR_ENGLAND, sex, dateOfbirth, dateOfDeath, LIMIT);
    }



    public static void main(String[] args) {
        try {
            System.out.println("Тестирование запросов SPARQL");
            SparqlExecutor executor = new SparqlExecutor();
            ArrayList<Person> sm = Converter.convertToPerson(executor.searchPersonQuickWithDatesAndImage("Jane Austen"));
            for (Person person : sm) {
                System.out.println(person.toString());
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            //logger.catching(e);
        }
    }

}