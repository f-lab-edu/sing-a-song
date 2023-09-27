package project.singasong.statistics.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.common.domain.ClassStatistics;
import project.common.domain.LikeStatistics;
import project.singasong.statistics.dto.StatisticsDto;
import project.singasong.statistics.repository.StatisticsRepository;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final StatisticsRepository statisticsRepository;

    public List<ClassStatistics> findByClassStatistics(StatisticsDto classStatistics) {
        return statisticsRepository.findByClassStatistics(classStatistics);
    }

    public List<LikeStatistics> findByLikeStatistics(StatisticsDto likeStatistics) {
        return statisticsRepository.findByLikeStatistics(likeStatistics);
    }

}
