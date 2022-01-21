package com.example.iccs.controller;

import com.example.iccs.bean.User;
import com.example.iccs.eunm.ResultEnum;
import com.example.iccs.service.IAdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.concurrent.ExecutionException;


/**
 * 用户控制层
 *
 * @author zsd
 * @date 2022/1/11
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final static Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private IAdminService iAdminService;


    /**
     * 登录
     *
     * @param user
     * @param session
     * @return
     */
    @CrossOrigin
    @PostMapping("/login")
    @ResponseBody
    public int login(@RequestBody User user,HttpSession session){
        LOGGER.info("---执行登录操作---");
        List<User> userList = iAdminService.login(user);
        LOGGER.info("查询结果为："+userList.toString());
        if (userList.size()==1 && user.getUsername().equals(userList.get(0).getUsername()) && user.getPassword().equals(userList.get(0).getPassword())){
            LOGGER.info("登陆成功！");
            session.setAttribute("user",userList.get(0));
            return ResultEnum.SUCCESS.getCode();
        }else{
            LOGGER.error("查询失败：该用户不存在！");
            return ResultEnum.NOT_FOUND.getCode();
        }
    }


    /**
     * 注册
     *
     * @param user
     * @return
     */
    @CrossOrigin
    @PostMapping("/register")
    @ResponseBody
    public int register(@RequestBody User user){
        LOGGER.info("---执行注册操作---");
        try {
            List<User> userList = iAdminService.register(user);
            LOGGER.info("查询结果为："+userList.toString());
            if (userList.size()==1){
                LOGGER.info("注册成功！");
                return ResultEnum.SUCCESS.getCode();
            }else{
                LOGGER.error("查询失败：该用户不存在！");
                return ResultEnum.NOT_FOUND.getCode();
            }
        }catch (Exception e){
            LOGGER.error("系统内部异常");
            return ResultEnum.FAIL.getCode();
        }
    }

}
