

package cn.bfcod.config;

import cn.bfcod.lost_and_found.oauth2.OAuth2Realm;
import cn.bfcod.lost_and_found.oauth2.OAuth2Filter;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro配置
 *
 * @author bfcod
 */
@Configuration
public class ShiroConfig {

    @Bean("securityManager")
    public SecurityManager securityManager(OAuth2Realm oAuth2Realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(oAuth2Realm);
        securityManager.setRememberMeManager(null);
        return securityManager;
    }

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);

        // setLoginUrl 如果不设置值，默认会自动寻找Web工程根目录下的"/login.jsp"页面 或 "/login" 映射
        shiroFilter.setLoginUrl("/home");
        // 设置无权限时跳转的 url;
        shiroFilter.setUnauthorizedUrl("/home");
        //oauth过滤
        Map<String, Filter> filters = new HashMap<>();
        filters.put("oauth2", new OAuth2Filter());
        shiroFilter.setFilters(filters);
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/webjars/**", "anon");
        filterMap.put("/druid/**", "anon");
        filterMap.put("/app/**", "anon");
        filterMap.put("/sys/login", "anon");
        filterMap.put("/sys/register", "anon");
        filterMap.put("/sys/password", "anon");
        filterMap.put("/lost_and_found/student/login", "anon");
        filterMap.put("/lost_and_found/student/register", "anon");
//        filterMap.put("/lost_and_found/student/logout", "logout");
        filterMap.put("/lost_and_found/pickthings/list", "anon");
        filterMap.put("/lost_and_found/lostthings/list", "anon");
        filterMap.put("/swagger/**", "anon");
        filterMap.put("/v2/api-docs", "anon");
        filterMap.put("/swagger-ui.html", "anon");
        filterMap.put("/swagger-resources/**", "anon");
        filterMap.put("/captcha.jpg", "anon");
        filterMap.put("/aaa.txt", "anon");
        filterMap.put("/**", "oauth2");
        shiroFilter.setFilterChainDefinitionMap(filterMap);
        return shiroFilter;

//        DefaultShiroFilterChainDefinition definition=new DefaultShiroFilterChainDefinition();
//        definition.addPathDefinition("/lost_and_found/student/logout", "logout");
//        definition.addPathDefinition("/**", "oauth2");
//        return definition;
    }

    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

}
