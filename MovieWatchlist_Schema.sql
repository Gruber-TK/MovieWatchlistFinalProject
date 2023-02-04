DROP TABLE IF EXISTS watchlist_movie;
DROP TABLE IF EXISTS movie_genre;
DROP TABLE IF EXISTS movie;
DROP TABLE IF EXISTS watchlist;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
  user_id INT AUTO_INCREMENT NOT NULL,
  user_code VARCHAR(50) NOT NULL UNIQUE,
  user_name VARCHAR(128) NOT NULL,
  PRIMARY KEY (user_id)
);

CREATE TABLE watchlist (
  watchlist_id INT AUTO_INCREMENT NOT NULL,
  watchlist_name VARCHAR(128) NOT NULL,
  user_code VARCHAR(50) NOT NULL,
  PRIMARY KEY (watchlist_id),
  FOREIGN KEY (user_code) REFERENCES users (user_code) ON DELETE CASCADE
);

CREATE TABLE movie (
  movie_id INT AUTO_INCREMENT NOT NULL,
  movie_title VARCHAR(128) NOT NULL,
  release_date INT NOT NULL,
  runtime_minutes INT NOT NULL,
  rating enum('G', 'PG', 'PG13', 'R', 'X', 'NOT_RATED') NOT NULL,
  synopsis TEXT,  
  PRIMARY KEY (movie_id)
);

CREATE TABLE movie_genre (
  movie_id INT NOT NULL,
  genre_name enum ('ACTION', 'ADVENTURE', 'ANIMATION', 'COMEDY', 'CRIME', 'DRAMA', 'FANTASY', 'HORROR', 'NOIR', 'MUSICAL', 'MYSTERY', 'ROMANCE', 'SCIENCE_FICTION', 'SPORTS', 'THRILLER', 'WAR', 'WESTERN') NOT NULL,
  FOREIGN KEY (movie_id) REFERENCES movie (movie_id) ON DELETE CASCADE,
  UNIQUE KEY (movie_id, genre_name)
);

CREATE TABLE watchlist_movie (
  watchlist_id INT NOT NULL,
  movie_id INT NOT NULL,
  have_watched BOOLEAN NOT NULL,
  FOREIGN KEY (watchlist_id) REFERENCES watchlist (watchlist_id) ON DELETE CASCADE,
  FOREIGN KEY (movie_id) REFERENCES movie (movie_id) ON DELETE CASCADE,
  UNIQUE KEY (watchlist_id, movie_id)
);