package project.singasong.song.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.singasong.song.domain.Song;
import project.singasong.song.dto.SongPagingDto;
import project.singasong.song.repository.SongRepository;

@Service
@RequiredArgsConstructor
public class SongService {

    private final SongRepository songRepository;

    public List<Song> findBySong(SongPagingDto song) {
        return songRepository.findBySong(song);
    }

}
