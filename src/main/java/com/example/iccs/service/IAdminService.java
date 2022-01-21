package com.example.iccs.service;

import com.example.iccs.bean.User;
import java.util.List;

/**
 * @author zsd
 * @date 2022/1/13
 */
public interface IAdminService {

    /**
     * 登录
     *
     * @param user
     * @return
     */
    List<User> login(User user);


    /**
     * 注册
     *
     * @param user
     * @return
     */
    List<User> register(User user);


    /**
     * 更新
     *
     * @param user
     */
    void update(User user);

}
