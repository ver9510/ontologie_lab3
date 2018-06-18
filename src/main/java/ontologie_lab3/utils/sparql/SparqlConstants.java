package ontologie_lab3.utils.sparql;

public class SparqlConstants {
    public static final String ENDPOINT = "https://query.wikidata.org/sparql";

    public static final String SPARQL_FIND_COUNTRY_BY_CITY =
            "SELECT distinct ?place ?placeLabel ?country ?countryLabel WHERE {\n" +
                    "  ?place ?label \"%s\"@en.\n" +
                    "  SERVICE wikibase:label { bd:serviceParam wikibase:language \"[AUTO_LANGUAGE],en\". }\n" +
                    "  OPTIONAL { ?place wdt:P17 ?country. }\n" +
                    "}\n" +
                    "ORDER BY STRLEN(STR(?place))\n" +
                    "LIMIT 1";
    public static final String SPARQL_FIND_COUNTRY_BY_NAME =
            "SELECT distinct ?country ?countryLabel WHERE {\n" +
                    "  ?country ?label \"%s\".\n" +
                    "  ?country wdt:P31 wd:Q6256.\n" +
                    "  SERVICE wikibase:label { bd:serviceParam wikibase:language \"[AUTO_LANGUAGE],en\". }\n" +
                    "}\n" +
                    "LIMIT 1";

    public static final String SPARQL_FIND_PERSON_QUICK_WITH_DATES =
            "SELECT DISTINCT ?personLabel ?person ?description ?date_of_birth ?date_of_death WHERE {\n" +
                    "  ?person ?label \"%s\"@en.\n" +
                    "  ?person wdt:P31 wd:Q5.\n" +
                    "  OPTIONAL {\n" +
                    "    ?person schema:description ?description.\n" +
                    "    FILTER((LANG(?description)) = \"en\")\n" +
                    "  }\n" +
                    "  SERVICE wikibase:label { bd:serviceParam wikibase:language \"en\". }\n" +
                    "  OPTIONAL { ?person wdt:P569 ?date_of_birth. }\n" +
                    "  OPTIONAL { ?person wdt:P570 ?date_of_death. }\n" +
                    "}\n" +
                    "ORDER BY STRLEN(STR(?person))\n" +
                    "LIMIT %d";

    public static final String SPARQL_FIND_PERSON_QUICK_WITH_DATES_AND_IMAGE =
            "SELECT DISTINCT ?personLabel ?person ?description ?date_of_birth ?date_of_death ?image ?country_of_citizenshipLabel WHERE {\n" +
                    "  ?person ?label \"%s\"@en.\n" +
                    "  ?person wdt:P31 wd:Q5.\n" +
                    "  OPTIONAL {\n" +
                    "    ?person schema:description ?description.\n" +
                    "    FILTER((LANG(?description)) = \"en\")\n" +
                    "  }\n" +
                    "  SERVICE wikibase:label { bd:serviceParam wikibase:language \"en\". }\n" +
                    "  OPTIONAL { ?person wdt:P569 ?date_of_birth. }\n" +
                    "  OPTIONAL { ?person wdt:P570 ?date_of_death. }\n" +
                    "  OPTIONAL { ?person wdt:P18 ?image. }\n" +
                    "  OPTIONAL { ?person wdt:P27 ?country_of_citizenship. }\n" +

                    "}\n" +
                    "ORDER BY STRLEN(STR(?person))\n" +
                    "LIMIT %d";
    ;

    public static final String SPARQL_FIND_PEOPLE_BY_YEARS_AND_SEX_FOR_ENGLAND =
            "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                    "\n" +
                    "SELECT DISTINCT ?person ?date_of_birth ?date_of_death ?date_of_birthLabel ?personLabel  WHERE {\n" +
                    "  ?person wdt:P31 wd:Q5.\n" +
                    "  ?person wdt:P21 %s.\n" +
                    "  { ?person wdt:P27 wd:Q145. }\n" +
                    "  UNION\n" +
                    "  {?person wdt:P27 wd:Q161885. }\n" +
                    "  UNION\n" +
                    "  { ?person wdt:P27 wd:Q174193. }\n" +
                    " \n" +
                    "  SERVICE wikibase:label { bd:serviceParam wikibase:language \"en\". }\n" +
                    "  OPTIONAL { ?person wdt:P569 ?date_of_birth. }\n" +
                    "  OPTIONAL { ?person wdt:P570 ?date_of_death. }\n" +
                    "  FILTER(?date_of_birth <= \"%s\"^^xsd:dateTime)\n" +
                    "  FILTER(?date_of_death >= \"%s\"^^xsd:dateTime)\n" +
                    "}\n" +
                    "ORDER BY STRLEN(STR(?person))\n" +
                    "LIMIT 10";

    public static final String SPARQL_FIND_PEOPLE_BY_YEARS_COUNTRY_AND_SEX =
            "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                    "\n" +
                    "SELECT DISTINCT ?person ?date_of_birth ?date_of_death ?date_of_birthLabel ?personLabel  WHERE {\n" +
                    "  ?person wdt:P31 wd:Q5.\n" +
                    "  ?person wdt:P27 %s.\n" +
                    "  ?person wdt:P21 %s.\n" +
                    "  SERVICE wikibase:label { bd:serviceParam wikibase:language \"en\". }\n" +
                    "  OPTIONAL { ?person wdt:P569 ?date_of_birth. }\n" +
                    "  OPTIONAL { ?person wdt:P570 ?date_of_death. }\n" +
                    "  FILTER(?date_of_birth <= \"%s\"^^xsd:dateTime)\n" +
                    "  FILTER(?date_of_death >= \"%s\"^^xsd:dateTime)\n" +
                    "}\n" +
                    "ORDER BY STRLEN(STR(?person))\n" +
                    "LIMIT %d";

    public static final String SPARQL_FIND_PERSON =
            "SELECT DISTINCT ?person ?description ?date_of_birth ?date_of_death ?image ?date_of_birthLabel ?personLabel ?sex_or_gender ?sex_or_genderLabel ?country_of_citizenship ?country_of_citizenshipLabel WHERE {\n" +
                    "  ?person ?label \"%s\"@en.\n" +
                    " ?person wdt:P31 wd:Q5.\n" +
                    "  OPTIONAL {\n" +
                    "    ?person schema:description ?description.\n" +
                    "    FILTER((LANG(?description)) = \"en\")\n" +
                    "  }\n" +
                    "  SERVICE wikibase:label { bd:serviceParam wikibase:language \"en\". }\n" +
                    "  OPTIONAL { ?person wdt:P569 ?date_of_birth. }\n" +
                    "  OPTIONAL { ?person wdt:P570 ?date_of_death. }\n" +
                    "  OPTIONAL { ?person wdt:P18 ?image. }\n" +
                    "  OPTIONAL { ?person wdt:P21 ?sex_or_gender. }\n" +
                    "  OPTIONAL { ?person wdt:P27 ?country_of_citizenship. }\n" +
                    "}\n" +
                    "ORDER BY STRLEN(STR(?person))\n" +
                    "LIMIT 1";
    public static final String MALE = "wd:Q6581097";
    public static final String FEMALE = "wd:Q6581072";


}
