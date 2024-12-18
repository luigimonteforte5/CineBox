package com.team1.cineBox.video.movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    Movie findMovieByTitleAndDirector(String title, String director);

    boolean existsByTitleAndDirector( String title, String director );


}
