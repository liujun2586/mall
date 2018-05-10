package cn.wolfcode.shop.handler;

import cn.wolfcode.shop.exception.WSException;
import cn.wolfcode.shop.vo.WSResponseVo;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
        logger.error("系统出错:",e);
        response.setContentType("application/json;charset=utf-8");

        WSResponseVo vo = new WSResponseVo();
        vo.mark("系统繁忙,正在殴打老板");

        if(e instanceof WSException){
            //如果当前是自定义异常,返回给界面看原因
            vo.mark(e.getMessage());
        }else if(e instanceof BindException){
            //获取所有的错误信息
            List<ObjectError> errors = ((BindException) e).getAllErrors();
            StringBuilder sb = new StringBuilder(200);
            for (ObjectError error : errors) {
                sb.append(error.getDefaultMessage()+";");
            }
            vo.setCode(4000);
            vo.setMsg(sb.deleteCharAt(sb.length()-1).toString());
        }
        response.getWriter().print(JSON.toJSON(vo));
    }
}
