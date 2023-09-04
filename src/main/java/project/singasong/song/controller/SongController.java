package project.singasong.song.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import project.singasong.song.domain.Song;
import project.singasong.song.dto.SongPagingDto;
import project.singasong.song.enums.BrandType;
import project.singasong.song.enums.SearchConditionType;
import project.singasong.song.service.SongService;

@Controller
@RequiredArgsConstructor
public class SongController {

    private final SongService songService;

    @GetMapping("/song")
    public String song() {
        return "/song";
    }

    @GetMapping("/song/{brand}/{songId}")
    public String addSong(@PathVariable String brand, @PathVariable Long songId, Model model) {
        model.addAttribute("brand", brand);
        model.addAttribute("no", songId);

        return "/song-add";
    }

    @GetMapping("/song/{brand}")
    public @ResponseBody ResponseEntity findBySong(@PathVariable BrandType brand, @RequestParam SearchConditionType searchCondition,
            @RequestParam String searchWord, @RequestParam(defaultValue = "0") Long offset, Model model) {

        SongPagingDto songPagingDto = SongPagingDto.of(brand.toString(), searchCondition, searchWord, offset);
        List<Song> songList = songService.findBySong(songPagingDto);

        if(!songList.isEmpty()) {
            model.addAttribute("offset", songList.get(songList.size()-1).getId());
        }

        return ResponseEntity.ok().body(songList);
    }

}
