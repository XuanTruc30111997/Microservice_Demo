package truc.microservice.movie_service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import truc.microservice.movie_service.model.MovieRating;
import truc.microservice.movie_service.runner.JobRunner;
import truc.microservice.movie_service.service.movieRating.MovieRatingService;

@RestController
@RequestMapping("/api/movie_rating")
public class MovieRatingController {

    private final Logger LOGGER = LoggerFactory.getLogger(MovieRatingController.class);

    @Autowired
    MovieRatingService movieRatingService;

    @Autowired
    JobRunner jobRunner;

    @GetMapping("/{id}")
    public ResponseEntity<MovieRating> getByMovieId(@PathVariable("id") Integer id)
    {
        return new ResponseEntity<>(movieRatingService.getMovieRatingByIdMovie(id), HttpStatus.OK);
    }

    @GetMapping()
    public String performBatch(@RequestParam("bucketName") String bucketName, @RequestParam("fileName") String fileName)
    {
        LOGGER.info(">>>> Start to perform batch <<<<<<");

        jobRunner.runMovieBatchJob(bucketName, fileName);

        LOGGER.info(">>>> Perform batch completed <<<<<<");
        return String.format("Run batch job completed");
    }
}
