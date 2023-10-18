package project.singasong.common.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import project.singasong.common.enums.ResultMessage;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseDto<T> {

    private ResultMessage message;
    private T result;

    public static <T> ResponseEntity success(T result) {
        return ResponseEntity.ok().body(
            ResponseDto.builder()
                .message(ResultMessage.SUCCESS)
                .result(result)
                .build()
        );
    }

}
