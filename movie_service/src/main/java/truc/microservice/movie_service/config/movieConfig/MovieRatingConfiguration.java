package truc.microservice.movie_service.config.movieConfig;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.client.RestTemplate;
import truc.microservice.movie_service.batchProcessor.MovieRatingProcessor;
import truc.microservice.movie_service.batchWriter.MovieWriter;
import truc.microservice.movie_service.mapper.MovieRatingFieldMapper;
import truc.microservice.movie_service.model.Movie;
import truc.microservice.movie_service.model.MovieRating;

@Configuration
@EnableBatchProcessing
public class MovieRatingConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    RestTemplate restTemplate;

    @Bean
    public Job movieJob(Step movieRatingStep1)
    {

        return jobBuilderFactory.get("movieJob")
                .incrementer(new RunIdIncrementer())
                .start(movieRatingStep1)
                .build();
    }

    @Bean
    public Step movieRatingStep1(ItemReader<MovieRating> jsonItemReader)
    {
        return stepBuilderFactory.get("movieRatingStep1")
                .<MovieRating, Movie>chunk(1)
                .reader(jsonItemReader)
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

    private Resource inputFile(String bucketName, String fileName)
    {
        // For Eureka
//        return restTemplate.getForObject("http://aws-service/amazonS3/" + bucketName + "/" + fileName, ByteArrayResource.class);

        // For Kubernetes
        return restTemplate.getForObject("http://aws-service:8085/amazonS3/" + bucketName + "/" + fileName, ByteArrayResource.class);
    }

    @Bean
    @StepScope
    public JsonItemReader<MovieRating> jsonItemReader(@Value("#{jobParameters[bucketName]}") String bucketName
            , @Value("#{jobParameters[fileName]}") String fileName)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        // configure the objectMapper as required
        JacksonJsonObjectReader<MovieRating> jsonObjectReader =
                new JacksonJsonObjectReader<>(MovieRating.class);
        jsonObjectReader.setMapper(objectMapper);

        return new JsonItemReaderBuilder<MovieRating>()
                .jsonObjectReader(jsonObjectReader)
                .resource(inputFile(bucketName, fileName))
                .name("tradeJsonItemReader")
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
