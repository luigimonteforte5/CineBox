package com.team1.cineBox.video.movie;

import com.team1.cineBox.rental.Rental;
import com.team1.cineBox.video.AgeRating;
import com.team1.cineBox.video.Genre;
import com.team1.cineBox.video.Video;
import com.team1.cineBox.video.VideoStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.Year;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
public class Movie extends Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "running_time", nullable = false)
    private Integer runningTime;

    @ManyToMany(mappedBy = "movies")
    private List<Rental> rentals;

    public Movie(String title, Genre genre, List<String> cast, String director, Year releaseYear, Double purchasePrice, Double rentalPrice, String plot, Float rating, VideoStatus videoStatus, int orderCount, Double videoProfit, AgeRating ageRating, Integer runningTime) {
        super(title, genre, cast, director, releaseYear, purchasePrice, rentalPrice, plot, rating, videoStatus, orderCount, videoProfit, ageRating);
        this.runningTime = runningTime;
    }
}
