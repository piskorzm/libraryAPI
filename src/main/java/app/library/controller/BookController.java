package app.library.controller;

import app.library.domain.Book;
import app.library.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/books")
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }



    @GetMapping()
    @ResponseBody
    public Iterable<Book> list(@RequestParam(required = false) String category) {

        Iterable<Book> books;

        System.out.println(category);
        if (category != null) {
            books = () -> StreamSupport.stream(bookService.list().spliterator(), false)
                .filter(book -> book.getCategories().contains(category))
                .iterator();
        }
        else {
            books = bookService.list();
        }

        return books;
    }
}
