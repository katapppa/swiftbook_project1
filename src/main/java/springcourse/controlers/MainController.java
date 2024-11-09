package springcourse.controlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/")
public class MainController {

    @GetMapping("/home")
    public String index(Model model) {

//        model.addAttribute("people", personDAO.index());
        log.info("Мы дома");

        return "/home";
    }
}
