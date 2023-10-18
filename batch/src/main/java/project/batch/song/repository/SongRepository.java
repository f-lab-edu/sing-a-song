package project.batch.song.repository;

import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import project.common.domain.Song;

@Mapper
@Repository
public interface SongRepository {
    Optional<Song> findBySongId(Song song);
    int create(Song song);
}
