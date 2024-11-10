package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.domain.pojo.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user")
@RestController   // @RestController=@RequestMapping + @ResponseBody
@Api(description = "用户控制器")
public class UserController {
    @Autowired
    private UserService userService;

    /*** 查询所有用户     */
    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "查询所有用户", notes = "查询所有用户")
    public List<User> findAll() {
        return userService.findAll();
    }

    /*** 根据id查询用户     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "根据id查询用户", notes = "根据id查询用户")
    public User findById(@PathVariable Integer id) {
        System.out.println("微服务用户模块9101被调用");
        return userService.findById(id);
    }

    /*** 添加用户     */
    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "添加用户", notes = "添加用户")
    public String add(@RequestBody User user) {
        userService.add(user);
        return "添加成功";
    }

    /*** 修改用户     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "修改用户", notes = "修改用户")
    public String update(@RequestBody User user, @PathVariable Integer id) {
        //设置id
        user.setId(id);
        userService.update(user);
        return "修改成功";
    }

    /*** 根据id删除用户     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "根据id删除用户", notes = "根据id删除用户")
    public String deleteById(@PathVariable Integer id) {
        userService.deleteById(id);
        return "删除成功";
    }
}