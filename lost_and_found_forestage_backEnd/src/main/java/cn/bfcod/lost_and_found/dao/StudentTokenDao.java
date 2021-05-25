package cn.bfcod.lost_and_found.dao;

import cn.bfcod.lost_and_found.entity.StudentTokenEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户Token
 *
 * @author bfcod
 */
@Mapper
public interface StudentTokenDao extends BaseMapper<StudentTokenEntity> {

    StudentTokenEntity queryByToken(String token);
	
}
