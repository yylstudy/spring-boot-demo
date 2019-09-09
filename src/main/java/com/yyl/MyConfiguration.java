package com.yyl;

import com.alibaba.druid.pool.DruidDataSource;
import com.yyl2.MyAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: TODO(这里描述)
 * @Date 2019/7/10 0010
 */
@Configuration("myConfiguration2")
@PropertySource("classpath:user.properties")
@MapperScan(value = "com.yyl",annotationClass = Dao.class)
public class MyConfiguration {
    /**
     * 采用@ConfigurationProperties的方式为不受控的第三方组件绑定属性时，非常有用
     * 同时bean的属性名和配置文件的属性名不需要完全一致，springboot 默认自动会采用驼峰的方式
     * @return
     */
    @Bean
    @ConfigurationProperties("druid")
    public DruidDataSource dataSource(){
        return new DruidDataSource();
    }

//    @Bean
//    @ConfigurationProperties("druid2")
//    public DruidDataSource dataSource3(){
//        return new DruidDataSource();
//    }
    @Bean
    public JdbcTemplate jdbcTemplate2(){
        return new JdbcTemplate(dataSource());
    }
//    @Bean
//    public JdbcTemplate jdbcTemplate3(){
//        return new JdbcTemplate(dataSource3());
//    }

}
