

package cn.bfcod.config;


import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * mybatis-plus配置
 *
 * @author bfcod
 */
@Configuration
@EnableTransactionManagement  // 开启事务
//@MapperScan("cn.bfcod.lost_and_found.dao.*")
public class MybatisPlusConfig {

    /**
     * 分页插件
     * 旧版
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
//    新版方式 “mybatis-plus 3.4.1”后用这种方式
//    @Bean
//    public MybatisPlusInterceptor mybatisPlusInterceptor() {
//        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
//        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
//        return interceptor;
//    }

}
