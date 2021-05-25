

package cn.bfcod.lost_and_found.service;


import cn.bfcod.common.utils.PageUtils;
import cn.bfcod.lost_and_found.entity.SysLogEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;


/**
 * 系统日志
 *
 * @author bfcod
 */
public interface SysLogService extends IService<SysLogEntity> {

    PageUtils queryPage(Map<String, Object> params);

}
