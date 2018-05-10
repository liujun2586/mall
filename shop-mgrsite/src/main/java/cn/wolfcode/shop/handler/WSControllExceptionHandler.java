package cn.wolfcode.shop.handler;

import cn.wolfcode.shop.exception.WSException;
import cn.wolfcode.shop.vo.WSResponseVo;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 统一处理异常
 */
@ControllerAdvice
public class WSControllExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(WSControllExceptionHandler.class);

    /**
     * 处理异常的方法
     */
    @ExceptionHandler(Exception.class)
    public void handlerException(Exception e,HttpServletResponse response, HandlerMethod method) throws IOException {
        logger.error("container:出错",e);
        //判断当前访问的方法是返回界面还是返回数据
        ResponseBody annotation = method.getMethodAnnotation(ResponseBody.class);

        if(annotation==null){
            //当前响应回去的是界面
            response.sendRedirect("/error/50x.html");
            return;
        }

        response.setContentType("application/json;charset=utf-8");

        WSResponseVo vo = new WSResponseVo();
        vo.mark("系统繁忙,正在殴打老板");

        if(e instanceof WSException){
            //如果当前是自定义异常,返回给界面看原因
            vo.mark(e.getMessage());
        }
        response.getWriter().print(JSON.toJSON(vo));

    }

}
