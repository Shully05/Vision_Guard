package cn.pxl.service.impl;

import cn.pxl.entity.User;
import cn.pxl.mapper.UserMapper;
import cn.pxl.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service//声明当前类是一个service逻辑层，业务逻辑层，主要目的是交给spring容器，ioc容器
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper usermapper;
    //查询所有用户
    @Override
    public PageInfo<User> selectAll(User user) {
        //开启分页
        int pageNum= user.getPageNum();
        int pageSize= user.getPageSize();
        PageHelper.startPage(pageNum,pageSize);
        List<User> users=usermapper.selectAll();
        //用一个pageInfo对象来封装查询结果
        PageInfo<User> userPageInfo=new PageInfo<>(users);
        return userPageInfo;
    }
//    public List<User> selectAll(User user) {
//        return usermapper.selectAll();
//    }
    @Override
    public List<User> selectByCon(String username, String phone){
            List<User> list=usermapper.selectByCon(username,phone);
            return list;
    }

    @Override
    public int add(User user) {
        int i=usermapper.add(user);//返回值为受影响的函数
        return i;
    }

    @Override
    public int deleteBatch(int[] ids) {
        int i=usermapper.deleteBatch(ids);
        return i;
    }

    @Override
    public int update(User user) {
        int i=usermapper.update(user);
        return i;
    }

}

