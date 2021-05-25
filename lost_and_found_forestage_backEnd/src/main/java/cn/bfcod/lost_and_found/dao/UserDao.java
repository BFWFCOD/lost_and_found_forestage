package cn.bfcod.lost_and_found.dao;

import cn.bfcod.lost_and_found.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 管理员
 * 
 * @author bfcod
 * @email 2573948452@qq.com
 * @date 2021-03-05 23:55:19
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {
	
}
