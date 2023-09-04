package project.singasong.song.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import project.singasong.song.domain.Song;
import project.singasong.song.dto.SongPagingDto;

@Mapper
@Repository
public interface SongRepository {
    List<Song> findBySong(SongPagingDto song);
}
