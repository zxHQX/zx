package cn.powertime.utils.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @author yyd
 * @version V1.0
 * @Package cn.powertime.evaluation.config
 * @date 2019/12/19 16:39
 */
@Configuration
public class WebConfig  implements WebMvcConfigurer {
    //增加拦截器
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new InterceptorConfig())    //指定拦截器类
                .addPathPatterns("/**");        //指定该类拦截的url
    }

}
