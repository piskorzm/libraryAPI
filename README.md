# libraryAPI
Books collection API in Java Spring. Technologies used: Spring Boot, Jackson, Maven.

Building 
 To build the application use following command: 'mvn clean package'
 
Running 
 To load local books from JSON to the API run: 'java -jar .\target\library-0.0.1.jar' 
 
 To load books from google api use: 'java -jar .\target\library-0.0.1.jar <google api url for example: https://www.googleapis.com/books/v1/volumes?q=java&maxResults=4>' 
 
Access
 API should start on domain 'localhost:8091' with four endpoints available:
 - '/books': returns all books from the database,
 - '/books/{isbn}': returns a book with a specified isbn number
 - '/books?category=<category>': returns books matching a given category
 - '/rating': returns the average rating for every author
