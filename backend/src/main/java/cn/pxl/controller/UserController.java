package cn.pxl.controller;

import cn.pxl.common.Result;
import cn.pxl.entity.User;
import cn.pxl.service.UserService;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    //从容器中取出对应的service
    @Autowired//依赖注入
    private UserService userService;

    //get:强调从服务器得到数据
    //delete post
    //put:更新数据，修改
    //@DeleteMapping
    @GetMapping("/selectAll")
    public Result selectAll(User user){
         PageInfo<User> users=userService.selectAll(user);
         return Result.success(users);
    }

    //根据用户用户名和电话查询用户信息
    @GetMapping("/selectByCon")
    public Result selectByCon(String username,String phone){
        List<User> list=userService.selectByCon(username,phone);
        return Result.success(list);
    }

    //添加用户信息，添加到数据库postmapping
    @PostMapping("/add")//传参——user对象
    public Result add(@RequestBody User user){
        int i=userService.add(user);
        if(i==1){
            return Result.success();
        }
        return Result.error("添加失败");
    }

    //根据id进行批量删除
    @DeleteMapping("/del/{ids}")
    public Result deleteBatch(@PathVariable int[] ids){
        int i=userService.deleteBatch(ids);
        if(i>0){
            return Result.success();
        }
        return Result.error("批量返回失败");
    }

    //修改用户信息
    @PostMapping("/update")
    public Result update(@RequestBody User user){
        System.out.println(user);
        int i=userService.update(user);
        return Result.success();
    }
}
