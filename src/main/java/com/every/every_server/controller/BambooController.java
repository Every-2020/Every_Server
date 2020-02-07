package com.every.every_server.controller;

import com.every.every_server.domain.entity.BambooPost;
import com.every.every_server.domain.vo.bamboo.post.BambooPostVO;
import com.every.every_server.domain.vo.http.Response;
import com.every.every_server.domain.vo.http.ResponseData;
import com.every.every_server.service.bamboo.BambooServiceImpl;
import com.every.every_server.service.jwt.JwtServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public Response getBambooPosts(@RequestHeader String token) {
        List<BambooPostVO> postList = new ArrayList<>();

        try {
            Integer memberIdx = jwtService.validateToken(token);
            postList = bambooService.getBambooPosts(memberIdx);

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
}