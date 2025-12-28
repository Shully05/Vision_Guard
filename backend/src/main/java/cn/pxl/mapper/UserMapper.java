package cn.pxl.mapper;

import cn.pxl.entity.User;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> selectAll();

    //通过注解生成参数的名字@param
    List<User> selectByCon(@Param("username") String username, @Param("phone") String phone);

    int add(User user);

    int deleteBatch(@Param("ids") int[] ids);

    int update(@Param("user") User user);
}
