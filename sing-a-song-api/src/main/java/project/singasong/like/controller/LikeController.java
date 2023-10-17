package project.singasong.like.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import project.singasong.common.dto.ResponseDto;
import project.singasong.common.enums.ResultMessage;
import project.singasong.like.domain.Like;
import project.singasong.like.dto.LikePagingDto;
import project.singasong.like.service.LikeService;

@Controller
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @GetMapping("/like-list/{userId}")
    public String likeList(@PathVariable Long userId, @RequestParam(defaultValue = "0") long offset, Model model) {
        findByUserId(userId, offset, model);
        return "like-list";
    }

    @GetMapping("/like/{userId}")
    public ResponseEntity findByAll(@PathVariable Long userId, @RequestParam(defaultValue = "0") long offset, Model model) {
        return ResponseEntity.ok().body(findByUserId(userId, offset, model));
    }

    @PostMapping("/like/{userId}/{songId}")
    public @ResponseBody ResponseEntity like(@PathVariable Long userId, @PathVariable Long songId) {
        Like like = Like.builder()
            .userId(userId)
            .songId(songId)
            .build();

        return ResponseDto.success(likeService.like(like));
    }

    @DeleteMapping("/like/{userId}/{songId}")
    public @ResponseBody ResponseEntity unLike(@PathVariable Long userId, @PathVariable Long songId) {
        Like like = Like.builder()
            .userId(userId)
            .songId(songId)
            .build();

        return ResponseEntity.ok().body(likeService.unLike(like));
    }

    private List<LikePagingDto> findByUserId(Long userId, long offset, Model model) {
        LikePagingDto likePagingDto = LikePagingDto.builder()
            .userId(userId)
            .offset(offset)
            .build();

        List<LikePagingDto> likeList = likeService.findByUserId(likePagingDto);

        if(!likeList.isEmpty()) {
            model.addAttribute("likeList", likeList);
            model.addAttribute("offset", likeList.get(likeList.size()-1).getSongId());
        }

        return likeList;
    }

}
