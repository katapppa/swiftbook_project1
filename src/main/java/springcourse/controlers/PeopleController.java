package springcourse.controlers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springcourse.dao.Person.PersonDAO;
import springcourse.models.Person;

import java.util.Optional;

@Controller
@RequestMapping("/people")
@AllArgsConstructor
public class PeopleController {

    private final PersonDAO personDAO;

    @GetMapping("/show")
    public String showPeople(
            Model model) {
        model.addAttribute("people", personDAO.selectPeople());
        return "people/showAll";
    }

    @GetMapping("/new")
    public String newPerson(
            @ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String createPerson(
            @ModelAttribute("person") @Valid Person person,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "people/new";

        personDAO.createPerson(person);

        return "redirect:/people/showAll";
    }

    @GetMapping("/{id}")
    public String showPerson(
            @PathVariable("id") int id,
            Model model) {

        Optional<Person> optionalPerson = personDAO.selectPerson(id);
        if (optionalPerson.isPresent()) {
            model.addAttribute("person", optionalPerson.get());
        } else {
            return "/error";
        }


        return "people/showById";
    }

    @GetMapping("/{id}/edit")
    public String edit(
            Model model,
            @PathVariable("id") int id) {

        Optional<Person> optionalPerson = personDAO.selectPerson(id);
        if (optionalPerson.isPresent()) {
            model.addAttribute("person", optionalPerson.get());
        } else {
            return "redirect:/error";
        }

        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(
            @ModelAttribute("person") @Valid Person person,
            BindingResult bindingResult,
            @PathVariable("id") int id) {

        if (bindingResult.hasErrors())
            return "people/edit";

        personDAO.updatePerson(id, person);

        return "redirect:/people/show";
    }
}
