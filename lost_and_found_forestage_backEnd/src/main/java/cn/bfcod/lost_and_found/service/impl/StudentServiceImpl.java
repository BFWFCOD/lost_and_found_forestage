package cn.bfcod.lost_and_found.service.impl;

import cn.bfcod.common.utils.PageUtils;
import cn.bfcod.common.utils.Query;
import cn.bfcod.lost_and_found.dao.StudentDao;
import cn.bfcod.lost_and_found.entity.StudentEntity;
import cn.bfcod.lost_and_found.service.StudentService;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.stereotype.Service;

import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.transaction.annotation.Transactional;


@Service("studentService")
public class StudentServiceImpl extends ServiceImpl<StudentDao, StudentEntity> implements StudentService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<StudentEntity> page = this.page(
                new Query<StudentEntity>().getPage(params),
                new QueryWrapper<StudentEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional
    public void saveUser(StudentEntity user) {
        //sha256加密
        String salt = RandomStringUtils.randomAlphanumeric(20);
        user.setPassword(new Sha256Hash(user.getPassword(), salt).toHex());
        user.setSalt(salt);
        this.save(user);
    }

    @Override
    public StudentEntity queryByStudentNo(Long studentNo) {
        return baseMapper.queryByStudentNo(studentNo);
    }
    @Override
    public StudentEntity queryById(Long id) {
        return baseMapper.queryById(id);
    }

    @Override
    public boolean updatePassword(Long userId, String password, String newPassword) {
        StudentEntity userEntity = new StudentEntity();
        userEntity.setPassword(newPassword);
        return this.update(userEntity,
                new QueryWrapper<StudentEntity>().eq("id", userId).eq("password", password));
    }

}