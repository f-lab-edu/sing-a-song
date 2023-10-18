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
import project.batch.song.service.ExternalService;
import project.batch.song.service.SongService;
import project.common.domain.Song;
import project.common.enums.BrandType;

@Configuration
@RequiredArgsConstructor
public class SongJobConfig extends BatchAutoConfiguration {

    private final ExternalService externalService;
    private final SongService songService;

    @Bean
    public Job syncSongJob(JobRepository jobRepository,
        PlatformTransactionManager transactionManager) {
        return new JobBuilder("syncSongJob", jobRepository)
            .start(getKumyoungSongChunk(jobRepository, transactionManager))
            .next(getTjSongChunk(jobRepository, transactionManager))
            .incrementer(new RunIdIncrementer())
            .build();
    }

    @Bean
    public Step getKumyoungSongChunk(
        JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("getKumyoungSongChunk", jobRepository)
            .chunk(50, transactionManager)
            .reader(songListReader(BrandType.KUMYOUNG))
            .processor(songListProcessor())
            .writer(songListWriter())
            .build();
    }

    @Bean
    public Step getTjSongChunk(
        JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("getTjSongChunk", jobRepository)
            .chunk(50, transactionManager)
            .reader(songListReader(BrandType.TJ))
            .processor(songListProcessor())
            .writer(songListWriter())
            .build();
    }

    protected ItemReader<Song> songListReader(BrandType brand) {
        return new ListItemReader<>(externalService.getSongListByReleaseDate(brand));
    }

    protected ItemProcessor<? super Object, ?> songListProcessor() {
        return item -> item;
    }

    protected ItemWriter<? super Object> songListWriter() {
        return items -> items.forEach((item) -> songService.create((Song) item));
    }

}
