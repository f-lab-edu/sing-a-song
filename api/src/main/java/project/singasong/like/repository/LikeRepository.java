package project.singasong.like.repository;

import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import project.singasong.like.domain.Like;
import project.singasong.like.dto.LikePagingDto;

@Mapper
@Repository
public interface LikeRepository {
    List<LikePagingDto> findByUserId(LikePagingDto likePagingDto);
    Optional<Like> findByUserIdAndSongId(Like like);
    int like(Like like);
    int unLike(Like like);
}
