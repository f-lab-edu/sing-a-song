package project.singasong.like.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Like {

    private Long userId;
    private Long songId;

    public static Like of(Like like) {
        return Like.builder()
            .songId(like.getSongId())
            .userId(like.getUserId())
            .build();
    }

}
