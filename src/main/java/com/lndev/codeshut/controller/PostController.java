package com.lndev.codeshut.controller;

import org.springframework.web.bind.annotation.*;
import com.lndev.codeshut.dto.PostDto;
import com.lndev.codeshut.service.PostService;
import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {


    private final PostService postService;

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts(){  
        return ResponseEntity.ok().body(postService.getPosts());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long id){
        return ResponseEntity.ok().body(postService.getPost(id));
    }
}
