package com.team1.cineBox.video.movie;

import org.springframework.stereotype.Component;

@Component
public class MovieMapper {

    public Movie toMovie(MovieDTO movieDTO) {
        return Movie.builder()
                .videoStatus(movieDTO.getVideoStatus())
                .plot(movieDTO.getPlot())
                .cast(movieDTO.getCast())
                .genre(movieDTO.getGenre())
                .title(movieDTO.getTitle())
                .director(movieDTO.getDirector())
                .purchasePrice(movieDTO.getPurchasePrice())
                .rentalPrice(movieDTO.getRentalPrice())
                .releaseYear(movieDTO.getYear())
                .runningTime(movieDTO.getRunningTime())
                .rating(movieDTO.getRating())
                .ageRating(movieDTO.getAgeRating())
                .build();
    }

    public MovieDTO toMovieDTO(Movie movie) {
        return MovieDTO.builder()
                .videoStatus(movie.getVideoStatus())
                .cast(movie.getCast())
                .genre(movie.getGenre())
                .director(movie.getDirector())
                .plot(movie.getPlot())
                .rating(movie.getRating())
                .runningTime(movie.getRunningTime())
                .year(movie.getReleaseYear())
                .title(movie.getTitle())
                .purchasePrice(movie.getPurchasePrice())
                .rentalPrice(movie.getRentalPrice())
                .ageRating(movie.getAgeRating())
                .build();
    }

    public AdminMovieDTO toAdminMovieDTO(Movie movie){
        return AdminMovieDTO.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .orderCount(movie.getOrderCount())
                .director(movie.getDirector())
                .videoProfit(movie.getVideoProfit())
                .rentalPrice(movie.getRentalPrice())
                .purchasePrice(movie.getPurchasePrice())
                .build();
    }

    public Movie toMovie (CreateMovieDTO movieDTO){
       return Movie.builder()
                .videoStatus(movieDTO.getVideoStatus())
                .plot(movieDTO.getPlot())
                .cast(movieDTO.getCast())
                .genre(movieDTO.getGenre())
                .title(movieDTO.getTitle())
                .director(movieDTO.getDirector())
                .purchasePrice(movieDTO.getPurchasePrice())
                .rentalPrice(movieDTO.getRentalPrice())
                .releaseYear(movieDTO.getReleaseYear())
                .runningTime(movieDTO.getRunningTime())
                .rating(movieDTO.getRating())
                .ageRating(movieDTO.getAgeRating())
                .ageRating(movieDTO.getAgeRating())
               .videoProfit(movieDTO.getVideoProfit())
               .orderCount(movieDTO.getOrderCount())
                .build();
    }

}
