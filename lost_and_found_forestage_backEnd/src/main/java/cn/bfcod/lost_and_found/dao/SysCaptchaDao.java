

package cn.bfcod.lost_and_found.dao;

import cn.bfcod.lost_and_found.entity.SysCaptchaEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 验证码
 *
 * @author bfcod
 */
@Mapper
public interface SysCaptchaDao extends BaseMapper<SysCaptchaEntity> {

}
