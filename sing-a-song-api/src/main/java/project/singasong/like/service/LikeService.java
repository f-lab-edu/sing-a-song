package project.singasong.like.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.singasong.like.domain.Like;
import project.singasong.like.dto.LikePagingDto;
import project.singasong.like.repository.LikeRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final RedisTemplate<String, Like> likeRedisTemplate;

    public List<LikePagingDto> findByUserId(LikePagingDto likePagingDto) {
        return likeRepository.findByUserId(likePagingDto);
    }

    public long like(Like like) {
        SetOperations<String, Like> setOperations = likeRedisTemplate.opsForSet();

        boolean hasLike = getLike(like);
        if (!hasLike) {
            return setOperations.add("like", like);
        } else {
            setOperations.add("unlike", like);
            return setOperations.remove("like", like);
        }
    }

    private boolean getLike(Like like) {
        SetOperations<String, Like> setOperations = likeRedisTemplate.opsForSet();
        return setOperations.isMember("like", like);
    }

    @Transactional
    @Scheduled(fixedDelay = 60 * 1000, initialDelay = 60 * 1000) // 60초 대기 후, 60초마다 실행
    public void publishLike() {
        SetOperations<String, Like> setOperations = likeRedisTemplate.opsForSet();
        Set<Like> likes = setOperations.members("like");

        for (Like like : likes) {
            Optional<Like> getLike = likeRepository.findByUserIdAndSongId(like);
            if (getLike.isEmpty()) {
                likeRepository.like(like);
            }
        }

        Set<Like> unlikes = setOperations.members("unlike");
        unlikes.forEach((unlike) -> {
            likeRepository.unLike(unlike);
            setOperations.remove("unlike", unlike);
            setOperations.remove("like", unlike);
        });

        log.info("publish like data: Redis Server -> DB");
    }

}
