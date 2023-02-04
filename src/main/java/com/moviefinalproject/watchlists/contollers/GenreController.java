package com.moviefinalproject.watchlists.contollers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.moviefinalproject.watchlists.entity.GenreModel;
import com.moviefinalproject.watchlists.entity.MovieModel;
import com.moviefinalproject.watchlists.service.GenreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Movie Genres")
@RestController
public class GenreController {

  private GenreService genreService; 
  public GenreController(GenreService genreService) {
    this.genreService = genreService;
  }
 
  
  @Operation(summary = "Gets all available movies in a genre")
  @GetMapping(value = "/genres/{genre}/movies")
  public List<MovieModel> getAllMoviesByGenre(@PathVariable("genre") GenreModel genre){
    if (genre != null) {
      List<MovieModel> movies = genreService.getMoviesByGenre(genre);
      if (! movies.isEmpty()) {
        return movies;
      }
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No movies found for genre " + genre));
    }
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Not a valid entry. Check genre input"));
  }
  
}
