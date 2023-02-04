package com.moviefinalproject.watchlists;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.moviefinalproject.ComponentScanMarker;


@SpringBootApplication(scanBasePackageClasses = {ComponentScanMarker.class})
public class MovieWatchlistsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieWatchlistsApplication.class, args);
	}

}
