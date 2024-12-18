package com.team1.cineBox.user;

import com.team1.cineBox.rental.Rental;
import com.team1.cineBox.subscription.Subscription;
import com.team1.cineBox.video.movie.Movie;
import com.team1.cineBox.video.tvshow.TvShow;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("unused")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "phone_number", length = 10)
    private String phoneNumber;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "is_active")
    private boolean isActive = true;

    @OneToMany()
    private List<Movie> watchedMovies;

    @OneToMany
    private List<TvShow> watchedShows;

    @OneToMany
    private List<Movie> movieWishList;

    @OneToMany
    private List<TvShow> showWishList;

    @OneToMany(mappedBy = "renter")
    private List<Rental> rentals;

    @OneToMany(mappedBy = "users")
    private List<Subscription> subscriptions = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isEnabled(){
        return isActive;
    }

}
