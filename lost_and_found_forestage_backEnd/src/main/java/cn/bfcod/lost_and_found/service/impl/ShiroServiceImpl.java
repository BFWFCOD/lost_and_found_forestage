
package cn.bfcod.lost_and_found.service.impl;

//import cn.bfcod.common.utils.Constant;
import cn.bfcod.lost_and_found.dao.StudentDao;
import cn.bfcod.lost_and_found.dao.StudentTokenDao;
import cn.bfcod.lost_and_found.entity.StudentEntity;
import cn.bfcod.lost_and_found.entity.StudentTokenEntity;
import cn.bfcod.lost_and_found.service.ShiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShiroServiceImpl implements ShiroService {

    @Autowired
    private StudentDao studentDao;
    @Autowired
    private StudentTokenDao studentTokenDao;

    @Override
    public Set<String> getUserPermissions(long userId) {
//        List<String> permsList;

        //系统管理员，拥有最高权限
//        if(userId == Constant.SUPER_ADMIN){
//            List<SysMenuEntity> menuList = sysMenuDao.selectList(null);
//            permsList = new ArrayList<>(menuList.size());
//            for (SysMenuEntity menu : menuList) {
//                permsList.add(menu.getPerms());
//            }
//        } else {
//            permsList = studentDao.queryAllPerms(userId);
//        }
        //用户权限列表
        Set<String> permsSet = new HashSet<>();
//        for (String perms : permsList) {
//            if (StringUtils.isBlank(perms)) {
//                continue;
//            }
//            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
//        }
        return permsSet;
    }

    @Override
    public StudentTokenEntity queryByToken(String token) {
        return studentTokenDao.queryByToken(token);
    }

    @Override
    public StudentEntity queryUser(Long userId) {
        return studentDao.selectById(userId);
    }
}
