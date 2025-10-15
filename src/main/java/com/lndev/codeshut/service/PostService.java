package com.lndev.codeshut.service;

import java.util.Collections;
import java.util.List;

import com.lndev.codeshut.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import com.lndev.codeshut.dto.PostDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

    @Value("${json.placeholder.url}")
    private String BASE_URL;
    Logger logger = LoggerFactory.getLogger(PostService.class);
    private final RestTemplate restTemplate;
    private final RestClient restClient;
    
    public List<PostDto> getAllPosts(){
        logger.trace("Trying to get all posts");
        try {
            logger.info("Getting all posts");
             PostDto[] posts = restTemplate.getForObject(BASE_URL+"/posts", PostDto[].class);
             logger.debug("Fetch successfully");
             logger.trace("Returning all posts"+posts.length);
        return List.of(posts);
        } catch (Exception e) {
            logger.error("error in fetching posts");
          throw new RuntimeException(e.getMessage());
        }
    }

    public List<PostDto> getPosts() {

        List<PostDto> posts = restClient.get()
                .uri("/posts")
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                    throw new RuntimeException("Client error: " + res.getStatusCode());
                })
                .onStatus(HttpStatusCode::is5xxServerError, (req, res) -> {
                    throw new RuntimeException("Server error: " + res.getStatusCode());
                })
                .body(new ParameterizedTypeReference<List<PostDto>>() {});

        if(posts == null){
            return Collections.emptyList();
        }
        return posts;
    }

    public PostDto getPostById(Long id){
        try {
            return restTemplate.getForObject(BASE_URL+"/posts/"+id, PostDto.class);
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public PostDto getPost(Long id){
        return restClient.get()
                .uri("/posts/{id}",id)
                .retrieve()
                .onStatus(status -> status == HttpStatus.NOT_FOUND , (req , res) -> {
                    throw new ResourceNotFoundException("Post not found");
                })
                .body(PostDto.class);
    }


}
