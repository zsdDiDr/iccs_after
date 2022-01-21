package com.example.iccs.dao;

import com.example.iccs.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户DAO层
 *
 * @author zsd
 * @date 2022/1/13
 */
@Mapper
@Repository
public interface IAdminDao {

    /**
     * 查询指定用户
     *
     * @param user
     * @return
     */
    List<User> findUser(User user);


    /**
     * 插入
     *
     * @param user
     */
    void insert(User user);

    /**
     * 修改信息
     *
     * @param user
     */
    void update(User user);

}
