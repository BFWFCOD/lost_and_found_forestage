package cn.bfcod.lost_and_found.service;

import cn.bfcod.common.utils.PageUtils;
import cn.bfcod.lost_and_found.entity.PickThingsEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 寻到物品
 *
 * @author bfcod
 * @email 2573948452@qq.com
 * @date 2021-03-05 23:55:20
 */
public interface PickThingsService extends IService<PickThingsEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils getByStudentNo(Map<String, Object> params,Long studentNo);

}

