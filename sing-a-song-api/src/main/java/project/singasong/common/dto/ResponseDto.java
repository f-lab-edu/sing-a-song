package project.singasong.common.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.singasong.common.enums.ResultMessage;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseDto<T> {

    private ResultMessage message;
    private T result;

    public static <T> ResponseDto success(T result) {
        return ResponseDto.builder()
            .message(ResultMessage.SUCCESS)
            .result(result)
            .build();
    }

    public static <T> ResponseDto fail(T result) {
        return ResponseDto.builder()
            .message(ResultMessage.FAIL)
            .result(result)
            .build();
    }

}
