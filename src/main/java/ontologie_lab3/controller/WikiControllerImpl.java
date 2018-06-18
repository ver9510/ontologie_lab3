package ontologie_lab3.controller;

import lombok.RequiredArgsConstructor;
import ontologie_lab3.service.WikiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/wiki")
public class WikiControllerImpl implements WikiController {
    private WikiService wikiService;

    @Override
    public String getPerson(String name, String country) {
        wikiService.getPerson(name);
        return "Jane";
    }
}
