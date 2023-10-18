package project.singasong.playlistSong.controller;

import jakarta.servlet.http.HttpServletRequest;
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
import project.singasong.playlistSong.domain.PlaylistSong;
import project.singasong.playlistSong.dto.PlaylistSongPagingDto;
import project.singasong.playlistSong.service.PlaylistSongService;

@Controller
@RequiredArgsConstructor
public class PlaylistSongController {

    private final PlaylistSongService playlistSongService;

    @GetMapping("/playlist/song/{playlistId}")
    public String playlistSong(@PathVariable Long playlistId, Model model) {
        model.addAttribute("playlistSongList", findPlaylistSong(playlistId, 0, model));
        return "playlist-song";
    }

    @GetMapping("/playlist/song/all/{playlistId}")
    public String playlistSongAll(@PathVariable Long playlistId, HttpServletRequest request, Model model) {
        model.addAttribute("playlistSongList", findByPlaylistIdAndLike(playlistId, 0, request, model));
        return "playlist-song-all";
    }

    @GetMapping("/api/playlist/song/{playlistId}")
    public @ResponseBody ResponseEntity findByPlaylistId(@PathVariable Long playlistId, @RequestParam long offset, Model model) {
        return ResponseEntity.ok().body(findPlaylistSong(playlistId, offset, model));
    }

    @PostMapping("/api/playlist/song/{playlistId}/{songId}")
    public @ResponseBody ResponseEntity create(@PathVariable Long playlistId, @PathVariable Long songId) {
        PlaylistSong playlistSong = PlaylistSong.builder()
            .playlistId(playlistId)
            .songId(songId)
            .build();

        return ResponseEntity.ok().body(playlistSongService.create(playlistSong));
    }

    @DeleteMapping("/api/playlist/song/{playlistId}/{songId}")
    public @ResponseBody ResponseEntity delete(@PathVariable Long playlistId, @PathVariable Long songId) {
        return ResponseEntity.ok().body(playlistSongService.delete(playlistId, songId));
    }

    private List<PlaylistSongPagingDto> findPlaylistSong(Long playlistId, long offset, Model model) {
        List<PlaylistSongPagingDto> playlistSongList = playlistSongService.findByPlaylistId(PlaylistSongPagingDto.of(playlistId, offset));
        model.addAttribute("playlistId", playlistId);

        if(!playlistSongList.isEmpty()) {
            model.addAttribute("offset", playlistSongList.get(playlistSongList.size()-1).getSongId());
        }

        return playlistSongList;
    }

    private List<PlaylistSongPagingDto> findByPlaylistIdAndLike(Long playlistId, long offset,
        HttpServletRequest request, Model model) {

        Long userId = (Long) request.getSession().getAttribute("userId");
        List<PlaylistSongPagingDto> playlistSongList = playlistSongService.findByPlaylistIdAndLike(PlaylistSongPagingDto.userOf(playlistId, userId, offset));

        model.addAttribute("playlistId", playlistId);

        if(!playlistSongList.isEmpty()) {
            model.addAttribute("offset", playlistSongList.get(playlistSongList.size()-1).getSongId());
        }

        return playlistSongList;
    }

}
