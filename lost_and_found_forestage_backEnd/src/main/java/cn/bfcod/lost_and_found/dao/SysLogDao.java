

package cn.bfcod.lost_and_found.dao;


import cn.bfcod.lost_and_found.entity.SysLogEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统日志
 *
 * @author bfcod
 */
@Mapper
public interface SysLogDao extends BaseMapper<SysLogEntity> {
	
}
