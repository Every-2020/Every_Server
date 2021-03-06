package com.every.every_server.controller;

import com.every.every_server.domain.vo.bamboo.post.BambooPostVO;
import com.every.every_server.domain.vo.bamboo.post.BambooWritePostVO;
import com.every.every_server.domain.vo.bamboo.reply.BambooModifyReplyVO;
import com.every.every_server.domain.vo.bamboo.reply.BambooReplyVO;
import com.every.every_server.domain.vo.bamboo.reply.BambooWriteReplyVO;
import com.every.every_server.domain.vo.http.Response;
import com.every.every_server.domain.vo.http.ResponseData;
import com.every.every_server.service.bamboo.BambooServiceImpl;
import com.every.every_server.service.jwt.JwtServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/bamboo")
public class BambooController {
    @Autowired
    private BambooServiceImpl bambooService;
    @Autowired
    private JwtServiceImpl jwtService;

    /**
     * 게시글 목록 조회
     */
    @GetMapping("/post")
    public Response getBambooPosts(
            @RequestHeader String token,
            @RequestParam @Nullable String order) {
        List<BambooPostVO> postList = new ArrayList<>();

        try {
            Integer memberIdx = jwtService.validateToken(token);
            postList = bambooService.getBambooPosts(memberIdx, order);

            Map<String, Object> data = new HashMap<>();
            data.put("posts", postList);
            return new ResponseData(HttpStatus.OK, "대나무숲 목록 조회 성공.", data);
        } catch (HttpClientErrorException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류.");
        }
    }

    /**
     * 게시글 목록 조회
     */
    @GetMapping("/post/{idx}")
    public Response getBambooPost(
            @RequestHeader String token,
            @PathVariable("idx") Integer idx) {

        try {
            Integer memberIdx = jwtService.validateToken(token);
            BambooPostVO post = bambooService.getBambooPost(memberIdx, idx);

            Map<String, Object> data = new HashMap<>();
            data.put("post", post);
            return new ResponseData(HttpStatus.OK, "대나무숲 조회 성공.", data);
        } catch (HttpClientErrorException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류.");
        }
    }

    /**
     * 게시글 작성
     */
    @PostMapping("/post")
    public Response writeBambooPost(
            @RequestHeader String token,
            @RequestBody BambooWritePostVO bambooWritePostVO) {
        try {
            Integer memberIdx = jwtService.validateToken(token);
            bambooService.writeBambooPost(memberIdx, bambooWritePostVO);

            return new Response(HttpStatus.CREATED, "대나무숲 작성 성공.");
        } catch (HttpClientErrorException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류.");
        }
    }

    /**
     * 댓글 목록 조회
     */
    @GetMapping("/reply")
    public Response getBambooReplys(
            @RequestHeader String token,
            @RequestParam Integer post) {
        try {
            Integer memberIdx = jwtService.validateToken(token);
            List<BambooReplyVO> replyList = bambooService.getBambooReplies(memberIdx, post);

            Map<String, Object> data = new HashMap<>();
            data.put("replies", replyList);
            return new ResponseData(HttpStatus.OK, "대나무숲 댓글 조회 성공.", data);
        } catch (HttpClientErrorException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류.");
        }
    }

    /**
     * 댓글 작성
     */
    @PostMapping("/reply")
    public Response writeBambooReply(
            @RequestHeader String token,
            @RequestBody BambooWriteReplyVO bambooWriteReplyVO) {
        try {
            Integer memberIdx = jwtService.validateToken(token);
            bambooService.writeBambooReply(memberIdx, bambooWriteReplyVO);

            return new Response(HttpStatus.CREATED, "대나무숲 댓글 작성 성공.");
        } catch (HttpClientErrorException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류.");
        }

    }

    /**
     * 댓글 수정
     */
    @PutMapping("/reply/{idx}")
    public Response modifyBambooReply(
            @RequestHeader String token,
            @RequestBody BambooModifyReplyVO bambooModifyReplyVO,
            @PathVariable("idx") Integer idx) {
        try {
            Integer memberIdx = jwtService.validateToken(token);
            bambooService.modifyBambooReply(memberIdx, idx, bambooModifyReplyVO);

            return new Response(HttpStatus.OK, "대나무숲 댓글 수정 성공.");
        } catch (HttpClientErrorException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw  new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류.");
        }
    }

    /**
     * 댓글 삭제
     */
    @DeleteMapping("/reply/{idx}")
    public Response deleteBambooReply(
            @RequestHeader String token,
            @PathVariable("idx") Integer idx) {
        try {
            Integer memberIdx = jwtService.validateToken(token);
            bambooService.deleteBambooReply(memberIdx, idx);

            return new Response(HttpStatus.OK, "대나무숲 댓글 삭제 성공.");
        } catch (HttpClientErrorException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw  new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류.");
        }
    }
}
