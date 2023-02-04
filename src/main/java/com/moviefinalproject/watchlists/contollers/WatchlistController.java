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
import com.moviefinalproject.watchlists.entity.WatchlistInputModel;
import com.moviefinalproject.watchlists.entity.WatchlistModel;
import com.moviefinalproject.watchlists.entity.WatchlistUpdateModel;
import com.moviefinalproject.watchlists.service.WatchlistService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Validated
@Tag(name = "Watchlists")
@RestController
public class WatchlistController {
  
  private WatchlistService watchlistService;
  public WatchlistController (WatchlistService watchlistService) {
    this.watchlistService = watchlistService;
  }
  

  @Operation(summary = "Gets All Watchlists For A User By The User Code")
  @GetMapping(value = "/users/{userCode}/watchlists")
  public List<WatchlistModel> getAllWatchlists(@PathVariable String userCode){
    if((userCode != null) && (! userCode.isEmpty())){
      List<WatchlistModel> watchlists = watchlistService.fetchAllWatchlistsByUser(userCode);  
      if ((watchlists != null) & (! watchlists.isEmpty())) {
        return watchlists;
      }
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No watchlists found for user " + userCode, userCode));
    }
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Invalid input"));
  }
  
  
  @Operation(summary = "Creates A New Watchlist For A User")
  @PostMapping(value = "/users/{userCode}/watchlists")
  @ResponseStatus(code = HttpStatus.CREATED)
  public WatchlistModel createWatchlist(@Valid @RequestBody WatchlistInputModel input) {
    if((input != null) && input.isWatchlistValid()) {
      WatchlistModel watchlist = watchlistService.createUserWatchlist(input);
      if(watchlist != null) {
        return watchlist;
      }
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Watchlist not created. Check user code is valid."));
    }
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Watchlist not created. Invalid input. Fields missing"));
  }
  
  
  @Operation(summary = "Deletes A Watchlist")
  @DeleteMapping(value = "/users/{userCode}/watchlists/{watchlistId}")
  public WatchlistModel deleteWatchlist(@PathVariable Long watchlistId) {
    if (watchlistId != null) {
      WatchlistModel watchlist = watchlistService.deleteWatchlist(watchlistId);
      if (watchlist != null) {
        return watchlist;
      }
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "A unhandled exception occured. Watchlist: " + watchlistId + " does not exist. Watchlist not deleted");
    }
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Invalid Watchlist Id " + watchlistId, watchlistId));
  }

  
  @Operation(summary = "Updates A Watchlist Name")
  @PutMapping(value = "/users/{userCode}/watchlists/{watchlistId}")
  public WatchlistModel updateWatchlistName(@Valid @RequestBody WatchlistUpdateModel input) {
    if(input != null) {
      WatchlistModel watchlist = watchlistService.updateWatchlistName(input);
      if(watchlist != null) {
        return watchlist;
      }
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A unhandled exception occured. Watchlist not updated. Check is watchlist exists.");
    }
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Invalid watchlist name. Fields missing"));
  }
  
}
