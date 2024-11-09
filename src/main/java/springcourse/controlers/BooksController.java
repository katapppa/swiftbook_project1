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
import springcourse.dao.Book.BookDAO;
import springcourse.models.Book;
import springcourse.models.Person;
import springcourse.models.enums.BookStatusEnum;

import java.util.Optional;

@Controller
@RequestMapping("/books")
@AllArgsConstructor
public class BooksController {

    private BookDAO bookDAO;

    @GetMapping("/show")
    public String showBooks(
            Model model) {

        model.addAttribute("books", bookDAO.selectBooks());

        return "books/showAll";
    }

    @GetMapping("/new")
    public String newBook(
            @ModelAttribute("book") Book book) {

        return "books/new";
    }

    @PostMapping()
    public String createBook(
            @ModelAttribute("book") @Valid Book book,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "books/new";

        book.setStatus(BookStatusEnum.IDLE);
        book.setStatus_reason("Book is available");
        bookDAO.createBook(book);

        return "redirect:/books/show";
    }

    @GetMapping("/{id}")
    public String showBook(
            @PathVariable("id") int id,
            Model model) {

        Optional<Book> optionalBook = bookDAO.selectBook(id);
        if (optionalBook.isPresent()) {
            model.addAttribute("book", optionalBook.get());
        } else {
            return "/error";
        }


        return "books/showById";
    }

    @GetMapping("/{id}/edit")
    public String edit(
            Model model,
            @PathVariable("id") int id) {

        Optional<Book> optionalBook = bookDAO.selectBook(id);
        if (optionalBook.isPresent()) {
            model.addAttribute("book", optionalBook.get());
        } else {
            return "/error";
        }

        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(
            @ModelAttribute("book") @Valid Book book,
            BindingResult bindingResult,
            @PathVariable("id") int id) {

        if (bindingResult.hasErrors())
            return "book/edit";

        book.setStatus(BookStatusEnum.UNAVAILABLE);
        bookDAO.updateBook(id, book);

        return "redirect:/books/show";
    }
}
