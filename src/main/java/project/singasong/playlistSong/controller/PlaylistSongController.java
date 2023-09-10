package project.singasong.playlistSong.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import project.singasong.playlistSong.domain.PlaylistSong;
import project.singasong.playlistSong.dto.PlaylistSongPagingDto;
import project.singasong.playlistSong.service.PlaylistSongService;

@Controller
@RequiredArgsConstructor
public class PlaylistSongController {

    private final PlaylistSongService playlistSongService;

    @GetMapping("/playlist/song/{playlistId}")
    public String playlistSong(@PathVariable Long playlistId, Model model) {
        PlaylistSongPagingDto playlistSongPagingDto = PlaylistSongPagingDto.builder()
            .playlistId(playlistId)
            .offset(0)
            .build();

        List<PlaylistSongPagingDto> playlistSongList = playlistSongService.findByPlaylistId(playlistSongPagingDto);

        model.addAttribute("playlistId", playlistId);
        model.addAttribute("playlistSongList", playlistSongList);

        if(!playlistSongList.isEmpty()) {
            model.addAttribute("offset", playlistSongList.get(playlistSongList.size()-1).getSongId());
        }

        return "playlist-song";
    }

    @GetMapping("/playlist/song/{playlistId}/{offset}")
    public @ResponseBody ResponseEntity getFindByPlaylistId(@PathVariable Long playlistId, @PathVariable Long offset, Model model) {
        PlaylistSongPagingDto playlistSongPagingDto = PlaylistSongPagingDto.builder()
            .playlistId(playlistId)
            .offset(offset)
            .build();

        List<PlaylistSongPagingDto> playlistSongList = playlistSongService.findByPlaylistId(playlistSongPagingDto);

        model.addAttribute("playlistId", playlistId);

        if(!playlistSongList.isEmpty()) {
            model.addAttribute("offset", playlistSongList.get(playlistSongList.size()-1).getSongId());
        }

        return ResponseEntity.ok().body(playlistSongList);
    }

    @PostMapping("/playlist/song/{playlistId}/{songId}")
    public @ResponseBody ResponseEntity create(@PathVariable Long playlistId, @PathVariable Long songId) {
        PlaylistSong playlistSong = PlaylistSong.builder()
            .playlistId(playlistId)
            .songId(songId)
            .build();

        return ResponseEntity.ok().body(playlistSongService.create(playlistSong));
    }

    @DeleteMapping("/playlist/song/{playlistId}/{songId}")
    public @ResponseBody ResponseEntity delete(@PathVariable Long playlistId, @PathVariable Long songId) {
        return ResponseEntity.ok().body(playlistSongService.delete(playlistId, songId));
    }

}
