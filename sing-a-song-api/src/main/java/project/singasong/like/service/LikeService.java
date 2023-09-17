package project.singasong.like.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.singasong.like.domain.Like;
import project.singasong.like.dto.LikePagingDto;
import project.singasong.like.repository.LikeRepository;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;

    public List<LikePagingDto> findByUserId(LikePagingDto likePagingDto) {
        return likeRepository.findByUserId(likePagingDto);
    }

    public int like(Like like) {
        return likeRepository.like(like);
    }

    public int unLike(Like like) {
        return likeRepository.unLike(like);
    }

}
