package com.ipub.common.web.controller;

import com.ipub.common.web.entity.User;
import com.ipub.common.web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * DemoApi
 *
 * @author wen.zhou
 * @since 2024/4/20
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserApi {
    private final UserService userService;

    @PostMapping("/query")
    public List<User> queryUser(@RequestBody User req) {
        return userService.queryUser(req);
    }

    @GetMapping("/get/{acct}")
    public List<User> testGet(@PathVariable("acct") String acct, User req) {
        return userService.queryUser(acct, req);
    }

    @GetMapping("/get")
    public List<User> testGet2(User req) {
        return userService.queryUser(req);
    }
}
