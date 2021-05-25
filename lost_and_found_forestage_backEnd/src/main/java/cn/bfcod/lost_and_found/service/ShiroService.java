

package cn.bfcod.lost_and_found.service;

import cn.bfcod.lost_and_found.entity.StudentEntity;
import cn.bfcod.lost_and_found.entity.StudentTokenEntity;

import java.util.Set;

/**
 * shiro相关接口
 *
 * @author bfcod
 */
public interface ShiroService {
    /**
     * 获取用户权限列表
     */
    Set<String> getUserPermissions(long userId);

    StudentTokenEntity queryByToken(String token);

    /**
     * 根据用户ID，查询用户
     * @param userId
     */
    StudentEntity queryUser(Long userId);
}
