package project.batch.song.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.batch.song.repository.SongRepository;
import project.common.domain.Song;

@Service
@RequiredArgsConstructor
public class SongService {

    private final SongRepository songRepository;

    public void create(Song song) {
        if (songRepository.findBySongId(song).isEmpty()) {
            songRepository.create(song);
        }
    }

}
