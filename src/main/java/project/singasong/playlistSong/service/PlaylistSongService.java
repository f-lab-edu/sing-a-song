package project.singasong.playlistSong.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.singasong.playlistSong.domain.PlaylistSong;
import project.singasong.playlistSong.dto.PlaylistSongPagingDto;
import project.singasong.playlistSong.repository.PlaylistSongRepository;

@Service
@RequiredArgsConstructor
public class PlaylistSongService {

    private final PlaylistSongRepository playlistSongRepository;

    public List<PlaylistSongPagingDto> findByPlaylistId(PlaylistSongPagingDto playlistSongPagingDto) {
        return playlistSongRepository.findByPlaylistId(playlistSongPagingDto);
    }

    public int create(PlaylistSong playlistSong) {
        Optional<PlaylistSong> findByPlaylistSong = playlistSongRepository.findByPlaylistIdAndSongId(playlistSong);
        if(!findByPlaylistSong.isEmpty()) {
            return 0;
        }

        return playlistSongRepository.create(playlistSong);
    }

    public int delete(Long playlistId, Long songId) {
        return playlistSongRepository.delete(playlistId, songId);
    }

}
