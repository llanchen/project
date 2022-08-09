package configuration;

import common.properties.ProjectProperties;
import lombok.AllArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@AllArgsConstructor
public class CorsConfig {

    private ProjectProperties projectProperties;

    /* 在前后端分离的时候，前后端代码不是由一个服务器中间件启动
    *
    * 一般会占用不同的端口，后端要设置白名单，允许前端对应的端口访问
    *
    * */
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(projectProperties.getAllowedOrigins());
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
        corsSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(corsSource);
    }
}
