package app.library.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Id;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {

    @JsonProperty("items")
    public List<Book> items;

    @JsonProperty("requestedUrl")
    public String requestedUrl;

    public Response() {
    }
}