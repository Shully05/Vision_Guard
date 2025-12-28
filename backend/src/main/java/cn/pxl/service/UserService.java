package cn.pxl.service;

import cn.pxl.entity.User;
import com.github.pagehelper.PageInfo;
//import com.github.pagehelper.PageInfo;


import java.util.List;

public interface UserService {
    PageInfo<User> selectAll(User user);
   // PageInfo<User> selectAllByPage(Integer pageNum, Integer pageSize);
    List<User> selectByCon( String username, String phone);

    int add(User user);

    int deleteBatch(int[] ids);

    int update(User user);
}
