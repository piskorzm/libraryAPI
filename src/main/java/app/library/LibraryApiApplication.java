package app.library;

import app.library.domain.Book;
import app.library.domain.Response;
import app.library.service.BookService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class LibraryApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryApiApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(BookService bookService) {
		return args -> {
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<Book>> typeReference = new TypeReference<List<Book>>(){};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/books.json");
			try {
				Response response = mapper.readValue(inputStream, Response.class);
				bookService.save(response.items);
				System.out.println("Books loaded");
			} catch (IOException e) {
				System.out.println("Books failed to load" + e.getMessage());
			}
		};
	}
}
