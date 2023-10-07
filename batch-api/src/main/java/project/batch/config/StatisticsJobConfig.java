package project.batch.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import project.batch.statistics.service.StatisticsService;
import project.common.domain.ClassStatistics;
import project.common.domain.LikeStatistics;
import project.common.enums.BrandType;

@Configuration
@RequiredArgsConstructor
public class StatisticsJobConfig extends BatchAutoConfiguration {

    private final StatisticsService statisticsService;

    @Bean
    public Job syncStatisticsJob(JobRepository jobRepository,
        PlatformTransactionManager transactionManager) {
        return new JobBuilder("syncStatisticsJob", jobRepository)
            .start(getClassStatisticsRankingOfKumyoung(jobRepository, transactionManager))
            .next(getClassStatisticsRankingOfTj(jobRepository, transactionManager))
            .next(getLikeRankingOfKumyoung(jobRepository, transactionManager))
            .next(getLikeRankingOfTj(jobRepository, transactionManager))
            .incrementer(new RunIdIncrementer())
            .build();
    }

    /**
     * 성별, 연령대별 통계
     */
    @Bean
    public Step getClassStatisticsRankingOfKumyoung(
        JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("getClassStatisticsRankingOfKumyoung", jobRepository)
            .chunk(30, transactionManager)
            .reader(classStatisticsListReader(BrandType.KUMYOUNG))
            .processor(classStatisticsListProcessor())
            .writer(classStatisticsListWriter())
            .build();
    }

    @Bean
    public Step getClassStatisticsRankingOfTj(
        JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("getClassStatisticsRankingOfTj", jobRepository)
            .chunk(30, transactionManager)
            .reader(classStatisticsListReader(BrandType.TJ))
            .processor(classStatisticsListProcessor())
            .writer(classStatisticsListWriter())
            .build();
    }

    protected ItemReader<ClassStatistics> classStatisticsListReader(BrandType brandType) {
        return new ListItemReader<>(statisticsService.findByClassification(brandType));
    }

    protected ItemProcessor<? super Object, ?> classStatisticsListProcessor() {
        return item -> item;
    }

    protected ItemWriter<? super Object> classStatisticsListWriter() {
        return items -> items.forEach((item) -> statisticsService.classCreate((ClassStatistics) item));
    }

    /**
     * 좋아요 통계
     */
    @Bean
    public Step getLikeRankingOfKumyoung(
        JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("getLikeRankingOfKumyoung", jobRepository)
            .chunk(30, transactionManager)
            .reader(likeListReader(BrandType.KUMYOUNG))
            .processor(likeListProcessor())
            .writer(likeListWriter())
            .build();
    }

    @Bean
    public Step getLikeRankingOfTj(
        JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("getLikeRankingOfTj", jobRepository)
            .chunk(30, transactionManager)
            .reader(likeListReader(BrandType.TJ))
            .processor(likeListProcessor())
            .writer(likeListWriter())
            .build();
    }

    protected ItemReader<LikeStatistics> likeListReader(BrandType brandType) {
        return new ListItemReader<>(statisticsService.findByLike(brandType));
    }

    protected ItemProcessor<? super Object, ?> likeListProcessor() {
        return item -> item;
    }

    protected ItemWriter<? super Object> likeListWriter() {
        return items -> items.forEach((item) -> statisticsService.likeCreate((LikeStatistics) item));
    }

}
