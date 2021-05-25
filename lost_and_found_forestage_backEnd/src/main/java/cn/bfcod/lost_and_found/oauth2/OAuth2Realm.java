

package cn.bfcod.lost_and_found.oauth2;

import cn.bfcod.lost_and_found.entity.StudentEntity;
import cn.bfcod.lost_and_found.entity.StudentTokenEntity;
import cn.bfcod.lost_and_found.redis.SysConfigRedis;
import cn.bfcod.lost_and_found.service.ShiroService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 认证
 *
 * @author bfcod
 */
@Component
public class OAuth2Realm extends AuthorizingRealm {
    @Autowired
    private ShiroService shiroService;
    @Autowired
    private SysConfigRedis sysConfigRedis;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof OAuth2Token;
    }

    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        StudentEntity user = (StudentEntity)principals.getPrimaryPrincipal();
////        Long userId = user.getId();
////
////        //用户权限列表
////        Set<String> permsSet = shiroService.getUserPermissions(userId);
////
////        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
////        info.setStringPermissions(permsSet);
////        return info;
        return null;
    }

    /**
     * 认证(每一次请求时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String accessToken = (String) token.getPrincipal();

        //根据accessToken，查询用户信息
        StudentTokenEntity tokenEntity = sysConfigRedis.getToken(accessToken);
//        StudentTokenEntity tokenEntity = shiroService.queryByToken(accessToken);
        //token失效
        if (tokenEntity == null || tokenEntity.getExpireTime().getTime() < System.currentTimeMillis()) {
            if (tokenEntity != null) {
                sysConfigRedis.deleteToken(tokenEntity);
            }
            throw new IncorrectCredentialsException("token失效，请重新登录");
        }else{
            System.err.println("shiro验证studentRedis.getToken:\n" + tokenEntity.toString());
        }

        //查询用户信息
        StudentEntity user = shiroService.queryUser(tokenEntity.getUserId());
        //账号锁定
//        if(user.getStatus() == 0){
//            throw new LockedAccountException("账号已被锁定,请联系管理员");
//        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, accessToken, getName());
        return info;
    }
}
