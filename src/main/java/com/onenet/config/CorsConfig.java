package com.onenet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 配置跨域
 * Created by thinker on 2019/5/5.
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*"); // 1. 允许任何域名使用
        config.addAllowedHeader("*"); // 2. 允许任何头
        config.addAllowedMethod("*"); // 3. 允许任何方法（POST、GET等）
        source.registerCorsConfiguration("/**", config); // 4. CORS配置对所有接口都有效

        System.out.println("跨域设置成功");
        return new CorsFilter(source);
    }
}
