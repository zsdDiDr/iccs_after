package com.example.iccs.controller;

import com.example.iccs.bean.Server;
import com.example.iccs.bean.User;
import com.example.iccs.service.IAdminService;
import com.example.iccs.service.IServerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

/**
 * @author zsd
 * @date 2022/1/18.
 */
@Controller
@RequestMapping("/admin")
public class AdminActionController {

    private final static Logger LOGGER = LoggerFactory.getLogger(AdminActionController.class);

    @Autowired
    private IAdminService iAdminService;

    @Autowired
    private IServerService serverService;

    /**
     * 获取用户名
     *
     * @param session
     * @return
     */
    @CrossOrigin
    @PostMapping("/get/name")
    @ResponseBody
    public User getName(HttpSession session) {
        LOGGER.info("---执行获取用户名操作---");
        try {
            User user = (User) session.getAttribute("user");
            LOGGER.info("获取到的用户信息为："+user);
            return user;
        } catch (NullPointerException e) {
            LOGGER.error("session域不存在该用户");
            return null;
        }
    }

    /**
     * 移除用户名
     *
     * @param session
     * @return
     */
    @CrossOrigin
    @PostMapping("/remove/name")
    @ResponseBody
    public void removeName(HttpSession session) {
        LOGGER.info("---执行移除用户名操作---");
        session.removeAttribute("user");
        LOGGER.info("移除成功");
    }

    /**
     * 修改用户信息
     *
     * @param session
     * @param user
     */
    @CrossOrigin
    @PostMapping("/update/info")
    @ResponseBody
    public boolean update(HttpSession session,@RequestBody User user) {
        LOGGER.info("---执行更新用户名操作---");
        User oldUser = (User) session.getAttribute("user");
        user.setUid(oldUser.getUid());
        user.setCircleUrl(oldUser.getCircleUrl());
        user.setPassword(oldUser.getPassword());
        iAdminService.update(user);
        //通过比较两次的内容，来判断是否更新成功
        String str0 = iAdminService.login(user).get(0).toString();
        String str1 = user.toString();
        if (str0.equals(str1)){
            session.setAttribute("user",user);
            LOGGER.info("更新后的数据为："+user);
            return true;
        }
        return false;
    }


    /**
     * 添加服务器
     *
     * @param server
     * @return
     */
    @CrossOrigin
    @PostMapping("/add/server")
    @ResponseBody
    public List<Server> addServer(@RequestBody Server server) {
        LOGGER.info("---执行新增服务器操作---");
        String token = UUID.randomUUID().toString().replace("-","").toUpperCase();
        server.setToken(token);
        server.setCreateDate(new Date());
        serverService.addServer(server);
        List<Server> serverList = serverService.findServer(server);
        if (serverList.size()>0){
            LOGGER.info("新增的服务器信息："+server);
            LOGGER.info("查询服务器结果："+serverList);
            return serverList;
        }
        return null;
    }


    /**
     * 查询服务器
     *
     * @param session
     * @return
     */
    @CrossOrigin
    @PostMapping("/get/server")
    @ResponseBody
    public List<Server> getServer(HttpSession session) {
        LOGGER.info("---执行获取服务器操作---");
        List<Server> serverList = serverService.findServer(null);
        if (serverList.size()>0){
            for (int i = 0; i < serverList.size(); i++) {
                /*计算设备数*/
                int count = serverService.countDevice(serverList.get(i));
                serverList.get(i).setCount(count);
                serverService.updateServer(serverList.get(i));
            }
            LOGGER.info("查询服务器结果："+serverList);
            session.setAttribute("serverlist",serverList);
            return serverList;
        }
        return null;
    }


    /**
     * 更新服务器
     *
     * @param session
     * @param server
     * @return
     */
    @CrossOrigin
    @PostMapping("/update/server")
    @ResponseBody
    public boolean updateServer(HttpSession session,@RequestBody Server server) {
        LOGGER.info("---执行更新服务器操作---");
        List<Server> serverList = (List<Server>) session.getAttribute("serverlist");
        if (serverList.size()>0){
            for (int i = 0; i < serverList.size(); i++) {
                if (serverList.get(i).getSid() == server.getSid()){
                    serverList.get(i).setSname(server.getSname());
                    serverService.updateServer(serverList.get(i));

                    String serverStr = serverService.findServer(serverList.get(i)).get(0).toString();
                    String str = serverList.get(i).toString();
                    if (serverStr.equals(str)){
                        return true;
                    }
                }
            }
        }
        return false;
    }


    /**
     * 根据id获取单个服务器信息
     *
     * @param server
     * @return
     */
    @CrossOrigin
    @PostMapping("/get/serverMsg")
    @ResponseBody
    public Server getServerMsg(@RequestBody Server server) {
        LOGGER.info("---执行根据id获取单个服务器信息操作---");
        List<Server> list = serverService.findServer(server);
        return list.get(0);
    }


}
