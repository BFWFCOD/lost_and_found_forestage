package cn.bfcod.lost_and_found.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import cn.bfcod.common.utils.PageUtils;
import cn.bfcod.common.utils.R;
import cn.bfcod.lost_and_found.entity.StudentEntity;
import cn.bfcod.lost_and_found.form.StudentLoginForm;
import cn.bfcod.lost_and_found.service.SysUserTokenService;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cn.bfcod.lost_and_found.service.StudentService;


/**
 * 学生
 *
 * @author bfcod
 * @email 2573948452@qq.com
 * @date 2021-03-05 23:55:20
 */
@RestController
@RequestMapping("lost_and_found/student")
public class StudentController extends AbstractController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private SysUserTokenService sysUserTokenService;


    /**
     * 登录
     */
//    @PostMapping("/login")
//    public Map<String, Object> login(@RequestBody StudentLoginForm form)throws IOException {
//
//        //用户信息
//        StudentEntity user = studentService.queryByStudentNo(form.getStudentNo());
//
//        //账号不存在、密码错误
//        if(user == null || !user.getPassword().equals(new Sha256Hash(form.getPassword(), user.getSalt()).toHex())) {
//            return R.error("账号或密码不正确");
//        }
//        System.out.println("user:"+user+","+"form:"+form);
//
//        //账号锁定
////        if(user.getStatus() == 0){
////            return R.error("账号已被锁定,请联系管理员");
////        }
//
//        //生成token，并保存到数据库
//        R r = sysUserTokenService.createToken(user.getId());
//        return r.put("user",user);
//    }

    /**
     * 注册
     */
//    @PostMapping("/register")
//    public Map<String, Object> register(@RequestBody StudentEntity student)throws IOException {
//
//        //用学号查询用户信息
//        StudentEntity user = studentService.queryByStudentNo(student.getStudentNo());
//        //学号未注册
//        if(user == null) {
//            studentService.saveUser(student);
//        }else {
//            return R.error("账号已存在，请输入正确的学号或换个学号");
//        }
////        System.out.println("user:"+user+","+"student:"+student);
//        return R.ok().put("student", student);
//    }

    /**
     * 退出
     */
//    @PostMapping("/logout")
//    public R logout() {
//        sysUserTokenService.logout(getUserId());
////        Subject subject = SecurityUtils.getSubject();
////        subject.logout(); // 退出用户
//
//        return R.ok();
//    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("lost_and_found:student:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = studentService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 获取登录的用户信息
     */
    @GetMapping("/info")
    public R info(){
        return R.ok().put("user", getUser());
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("lost_and_found:student:info")
    public R info(@PathVariable("id") String id){
		StudentEntity student = studentService.getById(id);

        return R.ok().put("student", student);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("lost_and_found:student:save")
    public R save(@RequestBody StudentEntity student){
		studentService.save(student);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("lost_and_found:student:update")
    public R update(@RequestBody StudentEntity student){
		studentService.updateById(student);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("lost_and_found:student:delete")
    public R delete(@RequestBody String[] studentNos){
		studentService.removeByIds(Arrays.asList(studentNos));

        return R.ok();
    }

}
