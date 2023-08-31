package project.singasong.playlist.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import project.singasong.playlist.domain.Playlist;
import project.singasong.playlist.dto.PlaylistPagingDto;

@Mapper
@Repository
public interface PlaylistRepository {

    List<Playlist> getFindByUserId(PlaylistPagingDto playlist);
    int create(Playlist playlist);
    int update(Playlist playlist);
    int delete(Long id);

}
