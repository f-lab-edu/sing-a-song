package project.singasong.playlist.controller;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import project.singasong.common.customAnnotation.CheckOwner;
import project.singasong.playlist.domain.Playlist;
import project.singasong.playlist.dto.CreatePlaylistDto;
import project.singasong.playlist.dto.PlaylistPagingDto;
import project.singasong.playlist.dto.UpdatePlaylistDto;
import project.singasong.playlist.service.PlaylistService;

@Controller
@RequiredArgsConstructor
public class PlaylistController {

    private final PlaylistService playlistService;

    @GetMapping("/playlist")
    public String playlist(Model model, HttpServletRequest request, @RequestParam(defaultValue = "0") long offset) {
        Long userId = (Long) request.getSession().getAttribute("userId");

        if(userId == null) {
            return "redirect:/";
        }

        List<Playlist> playlist = playlistService.findByUserId(PlaylistPagingDto.of(userId, offset));
        model.addAttribute("playlist", playlist);
        model.addAttribute("offset", playlist.get(playlist.size()-1).getId());

        return "playlist";
    }

    @GetMapping("/playlist-add")
    public String playlistAdd() {
        return "playlist-add";
    }

    @CheckOwner
    @GetMapping("/playlist-update/{playlistId}")
    public String playlistUpdate(@PathVariable Long playlistId, Model model) {
        model.addAttribute("playlistId", playlistId);
        return "playlist-update";
    }

    @GetMapping("/playlist/{userId}")
    public ResponseEntity findByUserId(Model model, @PathVariable Long userId, @RequestParam(defaultValue = "0") long offset) {
        List<Playlist> playlist = playlistService.findByUserId(PlaylistPagingDto.of(userId, offset));

        if(!playlist.isEmpty()) {
            model.addAttribute("playlist", playlist);
            model.addAttribute("offset", playlist.get(playlist.size()-1).getId());
        }

        return ResponseEntity.ok().body(playlist);
    }

    @PostMapping("/playlist")
    public ResponseEntity create(@RequestBody CreatePlaylistDto createPlaylist) {
        Playlist playlist = Playlist.builder()
            .userId(createPlaylist.getUserId())
            .title(createPlaylist.getTitle())
            .build();

        return ResponseEntity.ok().body(playlistService.create(playlist));
    }

    @CheckOwner
    @PatchMapping("/playlist/{id}")
    public @ResponseBody ResponseEntity update(@PathVariable Long id, @RequestBody UpdatePlaylistDto updateSongList) {
        Playlist playlist = Playlist.builder()
            .id(id)
            .title(updateSongList.getTitle())
            .build();

        return ResponseEntity.ok().body(playlistService.update(playlist));
    }

    @CheckOwner
    @DeleteMapping("/playlist/{id}")
    public @ResponseBody ResponseEntity delete(@PathVariable Long id) {
        return ResponseEntity.ok().body(playlistService.delete(id));
    }

}
