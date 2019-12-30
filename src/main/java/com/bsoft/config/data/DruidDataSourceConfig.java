package com.bsoft.config.data;

 import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
 import org.springframework.boot.context.properties.ConfigurationProperties;
 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;
 import org.springframework.context.annotation.Primary;
 import org.springframework.core.env.Environment;

 import javax.sql.DataSource;

 /**
 * Created by Vee on 2019-12-27.
 */
@Configuration
public class DruidDataSourceConfig {

    /**
     * 主DataSource 配置
     */
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    @Bean(name = "primaryDruidDataSource")
    public DataSource primaryDruidDataSource(Environment environment) {
        return DruidDataSourceBuilder.create().build(environment, "spring.datasource.druid.");
    }


//    /**
//     * 从DataSource 配置
//     */
//    @ConfigurationProperties(prefix = "spring.datasource.druid.slave")
//    @Bean(name = "slaveDruidDataSource")
//    public DataSource slaveDruidDataSource(Environment environment) {
//        return DruidDataSourceBuilder.create().build(environment, "spring.datasource.druid.slave.");
//    }
//    /***
//     * Oracle数据源配置
//     * @Author: Vee
//     * @Param: [environment]
//     * @return: javax.sql.DataSource
//     * @Date:  2019/12/27
//     **/
//     @ConfigurationProperties(prefix = "spring.datasource.oracle")
//     @Bean(name = "oracleDruidDataSource")
//     public DataSource oracleDruidDataSource(Environment environment) {
//         return DruidDataSourceBuilder.create().build(environment, "spring.datasource.oracle.");
//     }

}

