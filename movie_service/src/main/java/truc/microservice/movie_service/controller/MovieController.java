package truc.microservice.movie_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import truc.microservice.movie_service.model.Movie;
import truc.microservice.movie_service.model.MovieInsert;
import truc.microservice.movie_service.service.movie.MovieService;
import org.apache.commons.io.FileUtils;

import javax.print.DocFlavor;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @Autowired
    RestTemplate restTemplate;

    private final String TOKEN_KEY = "Token";
    private final String TOKEN_VALUE = "OkYkRrYacAXINmcBlvWONaUePJJZjAnu";

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getbyId(@PathVariable("id") Integer id)
    {
        return new ResponseEntity<>(movieService.findById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Object> create(@RequestBody MovieInsert movieInsert)
    {
        try
        {
            return new ResponseEntity<>(movieService.insertMovie(movieInsert), HttpStatus.CREATED);
        }
        catch (IllegalArgumentException ex)
        {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/ahihi")
    public ByteArrayResource ahihi()
    {
//        return restTemplate.getForObject("http://localhost:8085/amazonS3/test-ahihi/movie.json", ByteArrayResource.class);

        // For Kubernetes
        return restTemplate.getForObject("http://localhost:31300/amazonS3/test-ahihi/movie.json", ByteArrayResource.class);
    }

    @GetMapping("/daily")
    public ResponseEntity<Object> daily(@RequestParam("url") String url) {
        try {
            ByteArrayResource resource = downloadFile(url);

            return ResponseEntity
                    .ok()
                    .header("Content-type", "application/octet-stream")
                    .header("Content-disposition", "attachment; filename=\"")
                    .body(resource);
        } catch (FileNotFoundException e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        } catch (IOException e) {
            return new ResponseEntity<>(e, HttpStatus.NO_CONTENT);
        }

//        return new ResponseEntity<>(HttpStatus.OK);
    }

    private ByteArrayResource downloadFile(String url) throws IOException {
        URL myUrl = new URL(url);
        HttpURLConnection httpURLConnection = (HttpURLConnection) myUrl.openConnection();
        httpURLConnection.setRequestProperty("token", "OkYkRrYacAXINmcBlvWONaUePJJZjAnu");
        int responseCode = httpURLConnection.getResponseCode();

        InputStream inputStream = httpURLConnection.getInputStream();
        byte[] data = inputStream.readAllBytes();
        ByteArrayResource resource = new ByteArrayResource(data);

        return resource;
    }

}
