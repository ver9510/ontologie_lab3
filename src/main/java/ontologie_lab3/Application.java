package ontologie_lab3;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import ontologie_lab3.config.DBConfig;


@ComponentScan
//@EnableJpaRepositories(basePackages = {"ontologie_lab3.repositories"})
@Import(DBConfig.class)
public class Application {
    public static void main(String[] args) {
//        ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
//        CardController cardController = context.getBean("cardControllerImplementation", CardControllerImplementation.class);
//        cardController.createCard("chien","собака",WordClass.NOUN,Gender.MALE);
//        List<Card> allCards = cardController.findAll();
//        for (Card card : allCards) {
//            System.out.println(card.toString());
//        }
//        WikiController wikiController = context.getBean("wikiControllerImpl", WikiControllerImpl.class);
//        String person = wikiController.getPerson("Victoria", "United Kingdom");
//

    }
}
