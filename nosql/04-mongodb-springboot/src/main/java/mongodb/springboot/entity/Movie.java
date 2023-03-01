package mongodb.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("movies")
public class Movie {
    private String id;
    private List<String> plot;
    private String genres;
    private String runtime;
    private List<String> cast;
    private Integer num_mflix_comments;
    private String title;
    private String fullplot;
    private List<String> countries;
    private String released;
    private List<String> directors;
    private String rated;
    private Map<String, Object> awards;
    private String lastupdated;
    private String year;
    private Map<String, Object> imdb;
    private String type;
    private Map<String, Object> tomatoes;
    private String poster;
    private String languages;
    private String writers;
    private String metacritic;
}
