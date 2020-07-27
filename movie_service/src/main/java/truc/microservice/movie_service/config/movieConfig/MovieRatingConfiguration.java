package truc.microservice.movie_service.config.movieConfig;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import truc.microservice.movie_service.model.Movie;
import truc.microservice.movie_service.model.MovieRating;
import truc.microservice.movie_service.model.Rating;
import truc.microservice.movie_service.service.movie.MovieService;

import javax.sql.DataSource;

@Configuration
@EnableTask
@EnableBatchProcessing
public class MovieRatingConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    JobCompletionNotificationListener listener;

    @Bean
    public Job movieJob(Step movieRatingStep1, JobCompletionNotificationListener listener)
    {
//        return jobBuilderFactory.get("movieJob")
//                .incrementer(new RunIdIncrementer())
//                .listener(listener)
//                .flow(movieRatingStep1)
//                .end()
//                .build();

        return jobBuilderFactory.get("movieJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .start(movieRatingStep1)
                .build();
    }

    @Bean
    public Step movieRatingStep1(JdbcBatchItemWriter<MovieRating> movieRatingItemWriter)
    {
        return stepBuilderFactory.get("movieRatingStep1")
                .<MovieRating, MovieRating>chunk(1)
                .reader(reader())
                .processor(movieRatingItemProcessor())
                .writer(movieRatingItemWriter)
                .build();

    }

    @Bean
    public FlatFileItemReader<MovieRating> reader() {
        return new FlatFileItemReaderBuilder<MovieRating>()
                .name("ratingItemReader")
                .resource(new ClassPathResource("sample-data.csv"))
                .delimited()
                .names(new String[]{"idMovie", "name", "star"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<MovieRating>() {{
                    setTargetType(MovieRating.class);
                }})
                .build();
    }

    @Bean
    public JdbcBatchItemWriter<MovieRating> movieRatingItemWriter(DataSource dataSource)
    {
        return new JdbcBatchItemWriterBuilder<MovieRating>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO movie (id, name, avgStar) VALUES (:idMovie, :name, :star)")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    ItemProcessor<MovieRating, MovieRating> movieRatingItemProcessor() {
        return new MovieRatingProcessor();
    }

}
