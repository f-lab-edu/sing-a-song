package project.batch.statistics.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import project.common.domain.ClassStatistics;
import project.common.domain.LikeStatistics;
import project.common.enums.BrandType;

@Mapper
@Repository
public interface StatisticsRepository {
    //성별, 연령대별 통계
    List<ClassStatistics> findByClassification(BrandType brandType);
    int classCreate(ClassStatistics classStatistics);

    //좋아요 통계
    List<LikeStatistics> findByLike(BrandType brandType);
    int likeCreate(LikeStatistics likeStatistics);
}
