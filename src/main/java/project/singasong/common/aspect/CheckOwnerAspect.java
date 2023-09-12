package project.singasong.common.aspect;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import project.singasong.playlist.service.PlaylistService;

@Aspect
@Component
@RequiredArgsConstructor
public class CheckOwnerAspect {

    private final PlaylistService playlistService;

    @Pointcut("@annotation(project.singasong.common.customAnnotation.CheckOwner)")
    public void checkOwnerPointcut() {}

    @Before("checkOwnerPointcut()")
    public void checkOwner(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Long playlistId = (Long) args[0];

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        if(request != null) {
            HttpSession session = request.getSession();
            Long loginId = (Long)session.getAttribute("userId");

            if(!loginId.equals(playlistService.findById(playlistId).getUserId())) {
                throw new IllegalStateException("다른 사용자의 플레이리스트는 수정할 수 없습니다.");
            }
        }
    }
}
