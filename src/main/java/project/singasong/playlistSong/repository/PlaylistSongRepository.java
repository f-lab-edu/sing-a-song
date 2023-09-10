package project.singasong.playlistSong.repository;

import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import project.singasong.playlistSong.domain.PlaylistSong;
import project.singasong.playlistSong.dto.PlaylistSongPagingDto;

@Mapper
@Repository
public interface PlaylistSongRepository {
    List<PlaylistSongPagingDto> findByPlaylistId(PlaylistSongPagingDto playlistSongPagingDto);
    Optional<PlaylistSong> findByPlaylistIdAndSongId(PlaylistSong playlistSong);
    long create(PlaylistSong playlistSong);
    int delete(Long playlistId, Long songId);
}
