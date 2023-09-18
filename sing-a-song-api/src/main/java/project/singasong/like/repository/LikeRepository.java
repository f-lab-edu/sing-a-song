package project.singasong.like.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import project.singasong.like.domain.Like;
import project.singasong.like.dto.LikePagingDto;

@Mapper
@Repository
public interface LikeRepository {
    List<LikePagingDto> findByUserId(LikePagingDto likePagingDto);
    int like(Like like);
    int unLike(Like like);
}
