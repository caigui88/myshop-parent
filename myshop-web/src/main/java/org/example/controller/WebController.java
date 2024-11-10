package org.example.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.client.UserController;
import org.example.domain.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/*** 购票Controller */
@RequestMapping("/web")
@RestController
@Api(tags = "购票web核心Api")

public class WebController {
//    @Autowired
//    private DiscoveryClient discoveryClient;
//
//    @Autowired
//    private LoadBalancerClient client;

    @Autowired
    @Qualifier("userController")
    private UserController userController;

//    @Autowired
//    private RestTemplate restTemplate;

    /* * * 购票方法     */
//    @HystrixCommand(fallbackMethod = "fallback")
    @RequestMapping(value = "/order", method = RequestMethod.GET)
    @ApiOperation(value = "远程方法：根据用户Id查询用户的方法", notes = "购票")
    public String order(Integer id) {
        //模拟当前用户
//        Integer id = 4;
        // 到Eureka发现用户微服务
        // 调用用户服务获取用户信息
//        ServiceInstance serviceInstance = client.choose("myshop-user");
//        System.out.println("获取到服务实例IP为：" +  serviceInstance.getHost() + "\n 端口为：" + serviceInstance.getPort());
//        User user = restTemplate.getForObject("http://"+
//                serviceInstance.getHost() + ":"+
//                serviceInstance.getPort() + "/user/" + id,
//                User.class);
//        User user = restTemplate.getForObject("http://myshop-user/user/" + id, User.class);
        User user = userController.findById(id);
        System.out.println(user.getUsername()+"====正在购票");
        return user.getUsername()+"====购票成功";
    }

    public String fallback(Integer id){
        System.out.println("服务调用失败，熔断器已启动");
        return "服务调用失败，熔断器已启动";
    }
}