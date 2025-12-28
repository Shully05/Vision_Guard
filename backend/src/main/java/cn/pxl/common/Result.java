package cn.pxl.common;
//统一封装返回结构

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    public static final String SUCCESS="0";//final-不可以修改的
    public static final String ERROR="1";

    private String code;//当前请求返回时是否正确/成功code=0是成功
    private String msg;//错误信息；
    private Object data;//存放需要返回的数据

    //成功 静态 只返回成功的状态码
    public static Result success(){
        Result result=new Result();
        result.setCode(SUCCESS);

        return result;
    }

    //成功，返回状态码以及具体的数据
    public static Result success(Object data) {
        Result result = new Result();
        result.setCode(SUCCESS);
        result.setData(data);
        return result;
    }

    public static Result error(){
        Result result=new Result();
        result.setCode(ERROR);
        return result;
    }

    //成功，返回状态码以及具体的数据
    public static Result error(String msg){
        Result result=new Result();
        result.setCode(ERROR);
        result.setData(msg);
        result.setMsg(msg);
        return result;
    }
}
