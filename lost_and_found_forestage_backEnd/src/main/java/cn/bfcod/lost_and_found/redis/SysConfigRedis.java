

package cn.bfcod.lost_and_found.redis;


import cn.bfcod.lost_and_found.entity.StudentTokenEntity;
import cn.bfcod.lost_and_found.entity.SysConfigEntity;
import cn.bfcod.common.utils.RedisKeys;
import cn.bfcod.common.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 系统配置Redis
 *
 * @author bfcod
 */
@Component
public class SysConfigRedis {
    @Autowired
    private RedisUtils redisUtils;

    public void saveOrUpdate(SysConfigEntity config) {
        if(config == null){
            return ;
        }
        String key = RedisKeys.getSysConfigKey(config.getParamKey());
        redisUtils.set(key, config);
    }

    public void delete(String configKey) {
        String key = RedisKeys.getSysConfigKey(configKey);
        redisUtils.delete(key);
    }

    public SysConfigEntity get(String configKey){
        String key = RedisKeys.getSysConfigKey(configKey);
        return redisUtils.get(key, SysConfigEntity.class);
    }

    public StudentTokenEntity getToken(String Key){
        String key = RedisKeys.getTokenKey(Key);
        return redisUtils.get(key, StudentTokenEntity.class);
    }

    public void saveOrUpdateToken(StudentTokenEntity tokenEntity) {
        if(tokenEntity == null){
            return ;
        }
        String key = RedisKeys.getTokenKey(tokenEntity.getToken());
        redisUtils.set(key, tokenEntity);
    }

    public void deleteToken(StudentTokenEntity tokenEntity) {
        String key = RedisKeys.getTokenKey(tokenEntity.getToken());
        redisUtils.delete(key);
    }
}
