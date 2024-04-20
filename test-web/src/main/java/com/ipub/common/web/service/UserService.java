package com.ipub.common.web.service;

import com.ipub.common.base.aspect.annotation.LogMethod;
import com.ipub.common.web.entity.User;
import com.ipub.common.web.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UserService
 *
 * @author wen.zhou
 * @since 2024/4/20
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    public List<User> queryUser(User user) {
        return this.queryUser(null, user);
    }

    @LogMethod
    public List<User> queryUser(String acct, User user) {
        return userMapper.queryUser(acct, user);
    }
}
