package project.batch.statistics.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.batch.statistics.repository.StatisticsRepository;
import project.common.domain.ClassStatistics;
import project.common.domain.LikeStatistics;
import project.common.enums.BrandType;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final StatisticsRepository statisticsRepository;

    //성별, 연령대별 통계
    public List<ClassStatistics> findByClassification(BrandType brandType) {
        List<ClassStatistics> classificationList = statisticsRepository.findByClassification(brandType);
        log.info("Classification List of {} size : {}", brandType, classificationList.size());
        return classificationList;
    }

    public void classCreate(ClassStatistics classStatistics) {
        statisticsRepository.classCreate(classStatistics);
    }

    //좋아요 통계
    public List<LikeStatistics> findByLike(BrandType brandType) {
        List<LikeStatistics> likeList = statisticsRepository.findByLike(brandType);
        log.info("Like List of {} size : {}", brandType, likeList.size());
        return likeList;
    }

    public void likeCreate(LikeStatistics likeStatistics) {
        statisticsRepository.likeCreate(likeStatistics);
    }

}
