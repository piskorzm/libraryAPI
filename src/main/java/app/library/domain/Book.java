package app.library.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import javafx.beans.binding.IntegerBinding;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Data
@AllArgsConstructor
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {

    @Id
    @JsonProperty("id")
    private String isbn;

    private String title;
    private String subtitle;
    private String publisher;
    private Long publishedDate;
    @Lob
    @Column( length = 1000 )
    private String description;
    private int pageCount;
    private String thumbnailUrl;
    private String language;
    private Double averageRating;
    private String previewLink;

    @ElementCollection
    private List<String> authors = new ArrayList<>();

    @ElementCollection
    private List<String> categories = new ArrayList<>();

    @JsonProperty("volumeInfo")
    private void unpackFromNestedObject(Map<String, Object> volumeInfo) {
        title = (volumeInfo.get("title") != null ? volumeInfo.get("title").toString() : null);
        subtitle = (volumeInfo.get("subtitle") != null ? volumeInfo.get("subtitle").toString() : null);
        publisher = (volumeInfo.get("publisher") != null ? volumeInfo.get("publisher").toString() : null);
        try {
            publishedDate = this.parseDate(volumeInfo.get("publishedDate"));
        }
        catch (Exception e){
            System.out.println("Could not parse date " + e.getMessage());
        }
        description = (volumeInfo.get("description") != null ? volumeInfo.get("description").toString() : null);
        if (volumeInfo.get("pageCount") != null) {
            pageCount = Integer.parseInt(volumeInfo.get("pageCount").toString());
        }
        Map<String, Object> imageLinks = (Map<String, Object>)volumeInfo.get("imageLinks");
        thumbnailUrl = imageLinks.get("thumbnail").toString();

        language = (volumeInfo.get("language") != null ? volumeInfo.get("language").toString() : null);

        previewLink = (volumeInfo.get("previewLink") != null ? volumeInfo.get("previewLink").toString() : null);


        if (volumeInfo.get("averageRating") != null) {
            averageRating = Double.parseDouble(volumeInfo.get("averageRating").toString());
        }

        if (volumeInfo.get("authors") != null) {
            String authorsString = volumeInfo.get("authors").toString();
            authorsString = authorsString.substring(1, authorsString.length()-1);
            authors = new ArrayList<>(Arrays.asList(authorsString.split(", ")));
        }

        if (volumeInfo.get("categories") != null) {
            String categoriesString = volumeInfo.get("categories").toString();
            categoriesString = categoriesString.substring(1, categoriesString.length()-1);
            categories = new ArrayList<>(Arrays.asList(categoriesString.split(", ")));
        }

    }

    public Book() {

    }

    private Long parseDate(Object publishedDateObject) throws Exception {
        Long publishedDate = null;
        if (publishedDateObject != null) {
            String publishedDateString = publishedDateObject.toString();
            if (publishedDateString.length() == 4) {
                publishedDate = new SimpleDateFormat("yyyy").parse(publishedDateString).getTime();
            }
            else {
                publishedDate = new SimpleDateFormat("dd-MM-yyyy").parse(publishedDateString).getTime();
            }
        }
        return publishedDate;
    }
}