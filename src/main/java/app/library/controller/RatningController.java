package app.library.controller;

import app.library.types.Author;
import app.library.types.Book;
import app.library.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatningController {

    private BookService bookService;

    public RatningController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    public Collection<Author> list() {
        Iterable<Book> books = bookService.list();

        HashMap<String, Author> authors = new HashMap<String, Author>();

        for (Book book : books) {
            List<String> authorNames = book.getAuthors();

            if (authorNames.size() > 0 && book.getAverageRating() != null) {
                for (String authorName : authorNames) {
                    Author author = authors.get(authorName);
                    if (author == null) {
                        author = new Author(authorName, book.getAverageRating());
                    }
                    else {
                        author.addBookRating(book.getAverageRating());
                    }
                    authors.put(authorName, author);
                }
            }
        }

        return authors.values();
    }
}