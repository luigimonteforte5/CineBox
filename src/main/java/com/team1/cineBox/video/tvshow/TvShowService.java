package com.team1.cineBox.video.tvshow;


import com.team1.cineBox.utils.Pager;
import com.team1.cineBox.video.FieldUpdater;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Slf4j
@SuppressWarnings("unused")
@Service
@RequiredArgsConstructor
public class TvShowService {

    private final TvShowRepository tvShowRepository;
    private final TvShowMapper tvShowMapper;
    private final FieldUpdater <Object> tvShowUpdater;
    private final Pager pager;

    public Page<TvShowDTO> getAllShows(int page, int size) throws BadRequestException { //Corretto NoSuchElementException ??????????????????????????????????

        Page<TvShow> tvShows = tvShowRepository.findAll(pager.createPageable(page, size));

        if (tvShows == null || tvShows.isEmpty()) {
            throw new BadRequestException("There are no shows!");
        } else {
            return tvShows
                    .map(tvShowMapper::toTvShowDTO);
        }
    }

    public TvShowDTO getShowDTOById(Long id) throws NoSuchElementException {
        TvShow tvShow = tvShowRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("No show with id: " + id));
        return tvShowMapper.toTvShowDTO(tvShow);
    }

    public TvShow getShowById(Long id) throws NoSuchElementException {
        return tvShowRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No show with id: " + id));
    }

    public TvShowDTO addTvShow(CreateShowDTO tvShowDTO) throws BadRequestException {
        if (!tvShowRepository.existsByTitleAndDirector(tvShowDTO.getTitle(), tvShowDTO.getDirector())) {
            TvShow added = tvShowRepository.save(tvShowMapper.toTvShow(tvShowDTO));
            return tvShowMapper.toTvShowDTO(added);
        }
        throw new BadRequestException("This Show already exists");
    }

    public TvShowDTO updateShow(CreateShowDTO tvShowDTO, Long id) throws NoSuchElementException {
        if (tvShowRepository.existsById(id)) {
            TvShow found = tvShowMapper.toTvShow(tvShowDTO);
            found.setId(id);
            tvShowRepository.save(found);
            return tvShowMapper.toTvShowDTO(found);
        }
        throw new NoSuchElementException("There is no show with id: " + id);
    }

	public TvShowDTO updateShowField ( Long id, Map<String, Object> fieldValueMap ) throws NoSuchElementException{
		TvShow tvShow = tvShowRepository
				.findById(id)
				.orElseThrow(() -> new NoSuchElementException("No show with id: " + id));

        TvShow updated = fieldValueMap.entrySet()
                .stream()
                .map(e -> {
                    try{
                        return tvShowUpdater.updateField(tvShow, e.getKey(), e.getValue(), TvShow.class);
                    } catch ( NoSuchFieldException | IllegalAccessException ex ) {
	                    throw new RuntimeException(ex);
                    }
                }).reduce((first, second) -> second)
                .orElseThrow(() -> new IllegalStateException("Error in updating movie field"));

		tvShowRepository.save(updated);
		return tvShowMapper.toTvShowDTO(updated);
	}

    public boolean deleteShowById(Long id) {
        tvShowRepository.deleteById(id);
        return true;
    }

    public List<TvShow> getAllTvShowsById(List<Long> idList) {
        return idList.stream().map(this::getShowById).toList();
    }

    public AdminTvShowDTO getSalesById(Long id) throws NoSuchElementException {
        TvShow tvShowFound = tvShowRepository.findById(id).orElseThrow(() -> new NoSuchElementException("There is no tv show with id " + id));
        return tvShowMapper.toAdminShowDTO(tvShowFound);
    }

    public Page<AdminTvShowDTO> getSales(int page, int size) {
        Page<TvShow> shows = tvShowRepository.findAll(pager.createPageable(page, size));
        return shows.map(tvShowMapper::toAdminShowDTO);
    }
}
