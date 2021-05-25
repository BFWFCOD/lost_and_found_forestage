package cn.bfcod.lost_and_found.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.bfcod.common.utils.PageUtils;
import cn.bfcod.common.utils.Query;

import cn.bfcod.lost_and_found.dao.UserDao;
import cn.bfcod.lost_and_found.entity.UserEntity;
import cn.bfcod.lost_and_found.service.UserService;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserEntity> page = this.page(
                new Query<UserEntity>().getPage(params),
                new QueryWrapper<UserEntity>()
        );

        return new PageUtils(page);
    }

}