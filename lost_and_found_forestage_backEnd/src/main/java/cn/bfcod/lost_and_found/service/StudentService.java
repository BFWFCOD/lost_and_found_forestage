package cn.bfcod.lost_and_found.service;

import cn.bfcod.common.utils.PageUtils;
import cn.bfcod.lost_and_found.entity.StudentEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 学生
 *
 * @author bfcod
 * @email 2573948452@qq.com
 * @date 2021-03-05 23:55:20
 */
public interface StudentService extends IService<StudentEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 保存用户
     */
    void saveUser(StudentEntity user);

    /**
     * 根据学号，查询用户
     */
    StudentEntity queryByStudentNo(Long studentNo);
    /**
     * 根据id，查询用户
     */
    StudentEntity queryById(Long id);
    /**
     * 修改密码
     * @param userId       用户ID
     * @param password     原密码
     * @param newPassword  新密码
     */
    boolean updatePassword(Long userId, String password, String newPassword);
}

