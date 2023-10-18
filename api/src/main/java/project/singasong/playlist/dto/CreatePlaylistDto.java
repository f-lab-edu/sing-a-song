package project.singasong.playlist.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreatePlaylistDto {

    private Long id;

    @NotBlank(message = "빈 값을 입력하실 수 없습니다.")
    private Long userId;

    @NotBlank(message = "빈 값을 입력하실 수 없습니다.")
    @Size(min = 1, max = 50, message = "1~50글자까지 입력이 가능합니다.")
    private String title;

}
