package com.moviefinalproject.watchlists.contollers;



import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.moviefinalproject.watchlists.entity.WatchlistMovieInputModel;
import com.moviefinalproject.watchlists.entity.WatchlistMovieModel;
import com.moviefinalproject.watchlists.service.WatchlistMovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Validated
@Tag(name = "Watchlist Movies")
@RestController
public class WatchlistMovieController {

  private WatchlistMovieService watchlistMovieService;
  public WatchlistMovieController(WatchlistMovieService watchlistMovieService) {
    this.watchlistMovieService = watchlistMovieService;
  }
  
  
  
  @Operation(summary = "Adds a Movie to a watchlist")
  @PostMapping(value = "/users/{userCode}/watchlists/{watchlistId}/movies")
  @ResponseStatus(code = HttpStatus.CREATED)
  public WatchlistMovieModel addMovieToWatchlist(@Valid @RequestBody WatchlistMovieInputModel input) {
    if(input != null) {
      WatchlistMovieModel movie = watchlistMovieService.addMovieToWatchlist(input);
      if(movie != null) {
        return movie;
      }
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Movie not added to watchlist. Check watchlist id and movie id exist."));  
    }
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Invalid input. Fields missing"));
  }
  
  
  
  @Operation(summary = "Gets all movies on a watchlist by watchlst id")
  @GetMapping(value = "/users/{userCode}/watchlists/{watchlistId}/movies")
  public List<WatchlistMovieModel> getAllMoviesFromWatchlist(@PathVariable Long watchlistId) {
    if (watchlistId != null) {
      List<WatchlistMovieModel> movies = watchlistMovieService.getAllMoviesFromWatchlist(watchlistId);
      if ((movies != null) && (! movies.isEmpty())) {
        return movies;
      }
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No Movies found for " + watchlistId, watchlistId));
    }
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format(" Watchlist" + watchlistId + "does not exist", watchlistId));
    }

  
  
  @Operation(summary = "Updates the watched status for a movie on the watchlist")
  @PutMapping(value = "/users/{userCode}/watchlists/{watchlistId}/movies/{movieId}")
  public WatchlistMovieModel updateMovieWatchlistStatus(@Valid @RequestBody WatchlistMovieInputModel input) {
      if(input != null) {
      WatchlistMovieModel movie = watchlistMovieService.updateMovieWatchlistStatus(input);
      if(movie != null) {
        return movie;
      }
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Movie status not updated. Verify if movie is on watchlist."));  
    }
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Input not valid. Please check input and try again"));
  }
  
  
  @Operation(summary = "Deletes a movie from a watchlist")
  @DeleteMapping(value = "/users/{userCode}/watchlists/{watchlistId}/movies/{movieId}")
  public WatchlistMovieModel deleteMovieFromWatchlist(@PathVariable Long watchlistId, Long movieId) {
    WatchlistMovieModel movie = watchlistMovieService.deleteMovieFromWatchlist(watchlistId, movieId);
    if(movie != null) {
      return movie;
    }
    throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Movie not deleted from watchlist. Verify if movie is on watchlist.")); 
  }
 
}
