package project.singasong.playlist.service;

import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.singasong.playlist.domain.Playlist;
import project.singasong.playlist.dto.PlaylistDto;
import project.singasong.playlist.dto.PlaylistPagingDto;
import project.singasong.playlist.repository.PlaylistRepository;

@Service
@RequiredArgsConstructor
public class PlaylistService {

    private final PlaylistRepository playlistRepository;

    public Playlist findById(Long id) {
        return playlistRepository.findById(id);
    }

    public List<Playlist> findByUserId(PlaylistPagingDto playlistPagingDto) {
        List<Playlist> playlists = playlistRepository.findByUserId(playlistPagingDto);
        return playlists != null ? playlists : Collections.emptyList();
    }

    public List<PlaylistDto> findByAll(PlaylistPagingDto playlistPagingDto) {
        return playlistRepository.findByAll(playlistPagingDto);
    }

    public long create(Playlist playlist) {
        return playlistRepository.create(playlist);
    }

    public int update(Playlist playlist) {
        return playlistRepository.update(playlist);
    }

    public int delete(Long id) {
        return playlistRepository.delete(id);
    }

}
