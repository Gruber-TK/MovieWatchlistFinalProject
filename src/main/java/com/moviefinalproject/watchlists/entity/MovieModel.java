package com.moviefinalproject.watchlists.entity;


public class MovieModel {
  
  private Long movieId;
  private String movieTitle;
  private Integer releaseDate;
  private Integer runtimeMinutes; 
  private RatingModel rating;
  private String synopsis;
  
  public MovieModel(Long movieId, String movieTitle, Integer releaseDate, Integer runtimeMinutes, RatingModel rating, String synopsis) {
    setMovieId(movieId);
    setMovieTitle(movieTitle);
    setReleaseDate(releaseDate);
    setRuntimeMinutes(runtimeMinutes);
    setRating(rating);
    setSynopsis(synopsis);
  }
  
  
  public Long getMovieId() {
    return movieId;
  }

  public MovieModel setMovieId(Long movieId) {
    this.movieId = movieId;
    return this;
  }

  public String getMovieTitle() {
    return movieTitle;
  }

  public MovieModel setMovieTitle(String movieTitle) {
    this.movieTitle = movieTitle;
    return this;
  }

  public Integer getReleaseDate() {
    return releaseDate;
  }

  public MovieModel setReleaseDate(Integer releaseDate) {
    this.releaseDate = releaseDate;
    return this;
  }

  public Integer getRuntimeMinutes() {
    return runtimeMinutes;
  }

  public MovieModel setRuntimeMinutes(Integer runtimeMinutes) {
    this.runtimeMinutes = runtimeMinutes;
    return this;
  }

  public RatingModel getRating() {
    return rating;
  }

  public MovieModel setRating(RatingModel rating) {
    this.rating = rating;
    return this;
  }

  public String getSynopsis() {
    return synopsis;
  }

  public MovieModel setSynopsis(String synopsis) {
    this.synopsis = synopsis;
    return this;
  }

  
}

