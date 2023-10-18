package project.singasong.oauth.naver.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class KakaoSecretProperty {

    @Value("${kakao.javascript.key}")
    private String javascriptKey;
    @Value("${kakao.templateId}")
    private String templateId;

}
