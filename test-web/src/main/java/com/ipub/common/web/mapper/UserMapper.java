package com.ipub.common.web.mapper;

import com.ipub.common.web.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * UserMapper
 *
 * @author wen.zhou
 * @since 2024/4/20
 */
@Mapper
public interface UserMapper {
    List<User> queryUser(@Param("acct") String acct, @Param("user") User user);
}
