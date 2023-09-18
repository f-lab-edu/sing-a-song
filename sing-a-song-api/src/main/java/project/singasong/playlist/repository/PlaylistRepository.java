package project.singasong.playlist.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import project.singasong.playlist.domain.Playlist;
import project.singasong.playlist.dto.PlaylistDto;
import project.singasong.playlist.dto.PlaylistPagingDto;

@Mapper
@Repository
public interface PlaylistRepository {

    Playlist findById(Long id);
    List<Playlist> findByUserId(PlaylistPagingDto playlistPagingDto);
    List<PlaylistDto> findByAll(PlaylistPagingDto playlistPagingDto);
    long create(Playlist playlist);
    int update(Playlist playlist);
    int delete(Long id);

}
