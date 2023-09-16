package project.batch.song.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.batch.common.domain.Song;
import project.batch.song.repository.SongRepository;

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
