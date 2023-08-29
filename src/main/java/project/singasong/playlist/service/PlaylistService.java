package project.singasong.playlist.service;

import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.singasong.playlist.domain.Playlist;
import project.singasong.playlist.dto.PlaylistPagingDto;
import project.singasong.playlist.repository.PlaylistRepository;

@Service
@RequiredArgsConstructor
public class PlaylistService {

    private final PlaylistRepository repository;

    public List<Playlist> getFindByUserId(PlaylistPagingDto playlist) {
        List<Playlist> playlists = repository.getFindByUserId(playlist);
        return playlists != null ? playlists : Collections.emptyList();
    }

    public int create(Playlist playlist) {
        return repository.create(playlist);
    }

    public int update(Playlist playlist) {
        return repository.update(playlist);
    }

    public int delete(Long id) {
        return repository.delete(id);
    }

}
