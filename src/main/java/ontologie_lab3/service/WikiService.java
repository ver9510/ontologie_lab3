package ontologie_lab3.service;

import ontologie_lab3.utils.sparql.Person;

import java.util.List;

public interface WikiService {
    List<Person> getPerson(String name);

    List<Person> search(String country, String sex, String year);
}
