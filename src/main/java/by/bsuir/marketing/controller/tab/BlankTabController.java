package by.bsuir.marketing.controller.tab;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BlankTabController {

    @Value("${tab.blanks}")
    private String BLANKS_TAB;
    @Value("${tab.blanks.create}")
    private String CREATE_BLANK_TAB;
    @Value("${tab.blanks.view}")
    private String VIEW_BLANK_TAB;
    @Value("${tab.blanks.templates}")
    private String TEMPLATES_BLANK_TAB;
    @Value("${tab.blanks.edit}")
    private String EDIT_BLANK_TAB;

    @GetMapping("/blanks")
    public String openBlankTab() {
        return BLANKS_TAB;
    }

    @GetMapping("/blanks/create")
    public String openViewBlankTab() {
        return CREATE_BLANK_TAB;
    }

    @GetMapping("/blanks/view")
    public String openCreateBlankTab() {
        return VIEW_BLANK_TAB;
    }

    @GetMapping("/blanks/templates")
    public String openTemplatesBlankTab() {
        return TEMPLATES_BLANK_TAB;
    }

    @GetMapping("/blanks/edit/{idBlank}")
    public String openEditBlankTab(@PathVariable int idBlank, HttpServletRequest httpServletRequest) {
        httpServletRequest.setAttribute("idBlank", idBlank);
        return EDIT_BLANK_TAB;
    }
}
