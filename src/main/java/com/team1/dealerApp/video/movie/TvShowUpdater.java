package com.team1.dealerApp.video.movie;

import com.team1.dealerApp.video.tvshow.TvShowRepository;
import com.team1.dealerApp.video.tvshow.TvShow;
import com.team1.dealerApp.video.tvshow.TvShowMapper;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Field;

@SuppressWarnings("unused")
@RequiredArgsConstructor
public class TvShowUpdater <T>{

	private final TvShowRepository tvShowRepository;
	private final TvShowMapper tvShowMapper;

	public TvShow updateShowField( TvShow tvShow, String fieldName, T value) throws NoSuchFieldException, IllegalAccessException {
		Field field = TvShow.class.getField(fieldName);
		field.setAccessible(true);
		field.set(tvShow, value );
		return tvShow;
	}
}