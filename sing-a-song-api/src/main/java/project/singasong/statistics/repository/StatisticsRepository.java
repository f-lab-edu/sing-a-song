package project.singasong.statistics.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import project.common.domain.ClassStatistics;
import project.common.domain.LikeStatistics;
import project.singasong.statistics.dto.StatisticsDto;

@Mapper
@Repository
public interface StatisticsRepository {
    List<ClassStatistics> findByClassStatistics(StatisticsDto classStatistics);
    List<LikeStatistics> findByLikeStatistics(StatisticsDto likeStatistics);
}
