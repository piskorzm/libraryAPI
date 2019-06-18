package app.library.types;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getPublisher() {
        return publisher;
    }

    public Long getPublishedDate() {
        return publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public int getPageCount() {
        return pageCount;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public String getLanguage() {
        return language;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public String getPreviewLink() {
        return previewLink;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public List<String> getCategories() {
        return categories;
    }

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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Long getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Long publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public String getPreviewLink() {
        return previewLink;
    }

    public void setPreviewLink(String previewLink) {
        this.previewLink = previewLink;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}