package by.bsuir.marketing.controller.tab;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewBlankTabController {

    @Value("${tab.blanks.view}")
    private String VIEW_BLANK_TAB;

    @GetMapping("/blanks/view")
    public String openCreateBlankTab() {
        return VIEW_BLANK_TAB;
    }
}
