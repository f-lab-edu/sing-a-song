package project.singasong.statistics.controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import project.common.domain.ClassStatistics;
import project.common.domain.LikeStatistics;
import project.common.enums.BrandType;
import project.common.enums.Gender;
import project.singasong.statistics.dto.StatisticsDto;
import project.singasong.statistics.service.StatisticsService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping("/class")
    public String classStatistics() {
        return "/class-statistics";
    }

    @GetMapping("/like")
    public String likeStatistics() {
        return "/like-statistics";
    }

    @GetMapping("/class/{brand}/{gender}/{ageGroup}")
    public @ResponseBody ResponseEntity getClassStatistics(@PathVariable BrandType brand, @PathVariable Gender gender, @PathVariable String ageGroup, Model model) {
        StatisticsDto classStatistics = StatisticsDto.builder()
            .brand(brand)
            .gender(gender)
            .ageGroup(ageGroup)
            .formatDate(setDayOfWeek())
            .build();
        List<ClassStatistics> classStatisticsList = statisticsService.findByClassStatistics(classStatistics);

        if(!classStatisticsList.isEmpty()) {
            model.addAttribute("classStatisticsList", classStatisticsList);
        }

        return ResponseEntity.ok().body(classStatisticsList);
    }

    @GetMapping("/like/{brand}")
    public @ResponseBody ResponseEntity getLikeStatistics(@PathVariable BrandType brand, Model model) {
        StatisticsDto likeStatistics = StatisticsDto.builder()
            .brand(brand)
            .formatDate(setDayOfWeek())
            .build();
        List<LikeStatistics> likeStatisticsList = statisticsService.findByLikeStatistics(likeStatistics);

        if(!likeStatisticsList.isEmpty()) {
            model.addAttribute("likeStatisticsList", likeStatisticsList);
        }

        return ResponseEntity.ok().body(likeStatisticsList);
    }

    /**
     * 조회한 날짜 주의 일요일 구하기
     */
    private String setDayOfWeek() {
        LocalDate now = LocalDate.now();
        DayOfWeek dayOfWeek = now.getDayOfWeek();
        int value = dayOfWeek.getValue();

        int minusDay = 0;
        if(value != 0) {
            while(value != 0) {
                value -= 1;
                minusDay++;
            }
        }

        return now.minusDays(minusDay).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

}
