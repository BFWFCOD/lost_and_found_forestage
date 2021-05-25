package cn.bfcod.lost_and_found.service.impl;

import cn.bfcod.common.utils.PageUtils;
import cn.bfcod.common.utils.Query;
import cn.bfcod.lost_and_found.dao.LostThingsDao;
import cn.bfcod.lost_and_found.entity.LostThingsEntity;
import cn.bfcod.lost_and_found.service.LostThingsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("lostThingsService")
public class LostThingsServiceImpl extends ServiceImpl<LostThingsDao, LostThingsEntity> implements LostThingsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
//        IPage<LostThingsEntity> page = this.page(
//                new Query<LostThingsEntity>().getPage(params),
//                new QueryWrapper<LostThingsEntity>()
//        );
        //    获取key
        String key = (String) params.get("key");
        QueryWrapper<LostThingsEntity> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(key)) {
            queryWrapper.eq("id", key).or().like("things_name", key).or().like("lost_place", key).or().like("things_type", key).or().like("things_des", key).or().like("student_name", key);
        }
        IPage<LostThingsEntity> page = this.page(
                new Query<LostThingsEntity>().getPage(params), queryWrapper
        );

        return new PageUtils(page);
    }
    @Override
    public PageUtils getByStudentNo(Map<String, Object> params,Long studentNo) {
        QueryWrapper<LostThingsEntity> queryWrapper = new QueryWrapper<>();
        if (studentNo!=null) {
            queryWrapper.eq("student_no", studentNo);
        }
        IPage<LostThingsEntity> page = this.page(
                new Query<LostThingsEntity>().getPage(params), queryWrapper
        );
        return new PageUtils(page);
    }

}