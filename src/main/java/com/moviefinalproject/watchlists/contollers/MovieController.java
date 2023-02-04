package com.moviefinalproject.watchlists.contollers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.moviefinalproject.watchlists.entity.MovieModel;
import com.moviefinalproject.watchlists.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Movies")
@RestController
public class MovieController {
  
  private MovieService movieService; 
  public MovieController(MovieService movieService) {
    this.movieService = movieService;
  }

  
  @Operation(summary = "Gets all available movies")
  @GetMapping(value = "/movies")
  public List<MovieModel> getAllMovies() {
    List<MovieModel> movies = movieService.getMovies();
    return movies;
  }
  
  
  @Operation(summary = "Gets a movie by a movie id")
  @GetMapping (value = "/movies/{movieId}")
  public MovieModel getMovieById(@PathVariable("movieId") Long movieId) {
      MovieModel movie = movieService.getMovieById(movieId);
      if (movie != null){
        return movie;
      }
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Movie not found", movieId));
  }
  
}
