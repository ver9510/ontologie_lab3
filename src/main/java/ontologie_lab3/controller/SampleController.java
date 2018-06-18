package ontologie_lab3.controller;

import ontologie_lab3.enums.Gender;
import ontologie_lab3.service.MuseumService;
import ontologie_lab3.service.WikiService;
import ontologie_lab3.utils.jsonquery.MuseumObject;
import ontologie_lab3.utils.sparql.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class SampleController {
    private final WikiService wikiService;
    private final MuseumService museumService;

    @Autowired
    public SampleController(WikiService wikiService, MuseumService museumService) {
        this.wikiService = wikiService;
        this.museumService = museumService;
    }

    @RequestMapping("/")
    public String welcome(Map<String, Object> model, HttpServletRequest request) {
        Person person;
        if (request.getParameter("findPerson") == null) {
            person = wikiService.getPerson("Jane Austen").get(0);
            model.put("person", person);
        } else {
            List<Person> foundPersons = wikiService.getPerson(request.getParameter("personToFind"));
            if (!foundPersons.isEmpty()) {
                person = foundPersons.get(0);
                model.put("person", person);
            } else {
                model.put("error", true);
            }
        }

        return "index";
    }

    @RequestMapping("/findObjects")
    public String findObjectsInMuseum(Map<String, Object> model, HttpServletRequest request) {
        String personName = request.getParameter("personName");
        String personBirthDateString = request.getParameter("personBirthDate");
        String personDeathDateString = request.getParameter("personDeathDate");
        String personCountry = request.getParameter("personCountry");
        List<MuseumObject> dresses = museumService.findSuitableObjects("dress", personCountry, personBirthDateString, personDeathDateString);
        model.put("foundDresses", dresses);
        model.put("personName", personName);
        return "museumObjects";
    }

    @RequestMapping("/findMore")
    public String findMore(Map<String, Object> model, HttpServletRequest request) {
        String country = request.getParameter("dressCountry");
        String dressYear = request.getParameter("dressYear");
        String objectNumber = request.getParameter("dressObjectNumber");
        List<MuseumObject> dresses = museumService.findMore("dress", country, dressYear);
        model.put("foundDresses", dresses);
        return "museumObjects";
    }

    @RequestMapping("/findPeopleOfThatPeriod")
    public String findPeople(Map<String, Object> model, HttpServletRequest request) {
        String country = request.getParameter("dressCountry");
        String dressYear = request.getParameter("dressYear");
        String objectNumber = request.getParameter("dressObjectNumber");

        List<Person> people = wikiService.search(country, Gender.FEMALE.toString(), dressYear);
        model.put("foundPeople", people);
        model.put("year", dressYear);
        return "people";
    }


}
