package app.library;

import app.library.types.Book;
import app.library.types.Response;
import app.library.service.BookService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
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

			if (args.length == 1) {
				URL url = new URL(args[0]);
				System.out.println("Loading books from: " + args[0]);

				try {
					Response response = mapper.readValue(url, Response.class);
					bookService.save(response.items);
					System.out.println("Books loaded");
				} catch (IOException e) {
					System.out.println("Books failed to load" + e.getMessage());
				}
			}
			else {
				System.out.println("Loading local books");
				InputStream inputStream = TypeReference.class.getResourceAsStream("/json/books.json");

				try {
					Response response = mapper.readValue(inputStream, Response.class);
					bookService.save(response.items);
					System.out.println("Books loaded");
				} catch (IOException e) {
					System.out.println("Books failed to load" + e.getMessage());
				}
			}
		};
	}
}
