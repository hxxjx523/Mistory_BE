package org.mas.mistory.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mas.mistory.dto.CreatePostRequest;
import org.mas.mistory.dto.PostWithCommentResponse;
import org.mas.mistory.dto.PostListResponse;
import org.mas.mistory.entity.BoardType;
import org.mas.mistory.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

    // board_type(카테고리)별로 게시글 조회
//    @GetMapping("/posts/boards/{boardType}")
//    public ResponseEntity<List<PostListResponse>> getPostsByBoardType(@PathVariable BoardType boardType) {
//        List<PostListResponse> posts = postService.getPostsByBoardType(boardType);
//        return ResponseEntity.ok().body(posts);
//    }

    // 게시글 작성
    @PostMapping("/posts")
    public ResponseEntity<String> createPost(@RequestBody CreatePostRequest request) {
        postService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body("게시글이 작성되었습니다.");

    }

    // 게시글 및 댓글 목록 상세 조회
//    @GetMapping("/posts/{postId}")
//    public ResponseEntity<PostWithCommentResponse> getPostDetail(@PathVariable Long postId) {
//        PostWithCommentResponse postDetail = postService.getPostDetail(postId);
//
//        return ResponseEntity.ok().body(postDetail);
//    }


    @GetMapping("/posts")
    public ResponseEntity<List<PostWithCommentResponse>> getAllPostsWithComments(@RequestParam BoardType boardType) {
        List<PostWithCommentResponse> posts = postService.getPostsWithCommentByBoardType(boardType);

        return ResponseEntity.ok().body(posts);
    }
}
