package project.singasong.statistics.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import project.common.domain.ClassStatistics;
import project.common.domain.LikeStatistics;
import project.common.enums.BrandType;
import project.common.enums.Gender;
import project.singasong.statistics.dto.StatisticsDto;
import project.singasong.statistics.service.StatisticsService;

@Controller
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping("/statistics/class")
    public String classStatistics() {
        return "/class-statistics";
    }

    @GetMapping("/statistics/like")
    public String likeStatistics() {
        return "/like-statistics";
    }

    @GetMapping("/api/statistics/class/{brand}/{gender}/{ageGroup}")
    public @ResponseBody ResponseEntity getClassStatistics(@PathVariable BrandType brand, @PathVariable Gender gender, @PathVariable String ageGroup, Model model) {
        StatisticsDto classStatistics = StatisticsDto.builder()
            .brand(brand)
            .gender(gender)
            .ageGroup(ageGroup)
            .build();
        List<ClassStatistics> classStatisticsList = statisticsService.findByClassStatistics(classStatistics);

        if(!classStatisticsList.isEmpty()) {
            model.addAttribute("classStatisticsList", classStatisticsList);
        }

        return ResponseEntity.ok().body(classStatisticsList);
    }

    @GetMapping("/api/statistics/like/{brand}")
    public @ResponseBody ResponseEntity getLikeStatistics(@PathVariable BrandType brand, Model model) {
        StatisticsDto likeStatistics = StatisticsDto.builder()
            .brand(brand)
            .build();
        List<LikeStatistics> likeStatisticsList = statisticsService.findByLikeStatistics(likeStatistics);

        if(!likeStatisticsList.isEmpty()) {
            model.addAttribute("likeStatisticsList", likeStatisticsList);
        }

        return ResponseEntity.ok().body(likeStatisticsList);
    }

}
