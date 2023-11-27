package by.bsuir.marketing.controller.tab;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SwotTabController {

    @Value("${tab.swot}")
    private String SWOT_TAB;

    @GetMapping("/swot")
    public String openSwotTab() {
        return SWOT_TAB;
    }
}
