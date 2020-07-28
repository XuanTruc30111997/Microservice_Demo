package truc.microservice.movie_service.config.movieConfig;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.web.client.RestTemplate;
import truc.microservice.movie_service.batchProcessor.MovieRatingProcessor;
import truc.microservice.movie_service.batchReader.RESTMovieReader;
import truc.microservice.movie_service.batchWriter.MovieWriter;
import truc.microservice.movie_service.batchWriter.RESTMovieWriter;
import truc.microservice.movie_service.mapper.MovieRatingFileMapper;
import truc.microservice.movie_service.model.Movie;
import truc.microservice.movie_service.model.MovieInsert;
import truc.microservice.movie_service.model.MovieRating;

import javax.sql.DataSource;

@Configuration
public class MovieRatingConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    JobCompletionNotificationListener listener;

    @Bean
    public Job movieJob(Step movieRatingStep1)
    {

        return jobBuilderFactory.get("movieJob")
                .incrementer(new RunIdIncrementer())
                .start(movieRatingStep1)
                .build();
    }

    @Bean
    public Step movieRatingStep1()
    {
        return stepBuilderFactory.get("movieRatingStep1")
                .<MovieRating, Movie>chunk(1)
                .reader(reader())
                .processor(movieRatingItemProcessor())
                .writer(movieWriter())
                .build();

    }

    @Bean
    @StepScope
    Resource inputFileResource()
    {
        return new ClassPathResource("sample-data.csv");
    }

    @Bean
    @StepScope
    public FlatFileItemReader<MovieRating> reader() {
        return new FlatFileItemReaderBuilder<MovieRating>()
                .name("ratingItemReader")
                .resource(inputFileResource())
                .delimited()
                .names(new String[]{"idMovie", "name", "star"})
//                .fieldSetMapper(new BeanWrapperFieldSetMapper<MovieRating>() {{
//                    setTargetType(MovieRating.class);
//                }})
                .fieldSetMapper(new MovieRatingFileMapper())
                .build();
    }

   /* @Bean
    public JdbcBatchItemWriter<MovieRating> movieRatingItemWriter(DataSource dataSource)
    {
        return new JdbcBatchItemWriterBuilder<MovieRating>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO movie (id, name, avgStar) VALUES (:idMovie, :name, :star)")
                .dataSource(dataSource)
                .build();
    }*/

    @Bean
    ItemWriter<Movie> movieWriter(){
        return new MovieWriter();
    }

    @Bean
    ItemProcessor<MovieRating, Movie> movieRatingItemProcessor() {
        return new MovieRatingProcessor();
    }

}
