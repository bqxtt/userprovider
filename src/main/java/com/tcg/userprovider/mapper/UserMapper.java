package com.tcg.userprovider.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tcg.userprovider.entity.User;

/**
 * @author 14861
 * @date 2020/5/23
 */
@Mapper
public interface UserMapper {
    /**
     * 新建用户
     * 
     * @param user
     *            用户实体类
     * @return 数据库操作结果
     */
    @Insert("insert into userInfo(username,password,email) values(#{username},#{password},#{email})")
    public int addUser(User user);

    /**
     * 通过用户名查找用户
     * 
     * @param username
     *            用户名
     * @return 用户实体类
     */
    @Select("select * from userInfo where username = #{username}")
    public User findUserByUsername(@Param("username") String username);
}
