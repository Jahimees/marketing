package by.bsuir.marketing.controller.tab;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MarketerTabController {

    @Value("${tab.marketer}")
    private String MARKETER_TAB;

    @GetMapping("/marketers")
    public String openMarketerTab() {
        return MARKETER_TAB;
    }
}
