package ontologie_lab3.service;

import ontologie_lab3.utils.jsonquery.MuseumObject;

import java.util.List;

public interface MuseumService {
    List<MuseumObject> findSuitableObjects(String word, String country, String personBirthDateString, String personDeathDateString);
}
