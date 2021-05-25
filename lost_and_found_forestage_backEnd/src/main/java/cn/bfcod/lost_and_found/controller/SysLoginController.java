

package cn.bfcod.lost_and_found.controller;

import cn.bfcod.common.annotation.SysLog;
import cn.bfcod.common.utils.R;
import cn.bfcod.common.validator.Assert;
import cn.bfcod.lost_and_found.entity.StudentEntity;
import cn.bfcod.lost_and_found.form.PasswordForm;
import cn.bfcod.lost_and_found.form.StudentLoginForm;
import cn.bfcod.lost_and_found.service.StudentService;
import cn.bfcod.lost_and_found.service.SysCaptchaService;
import cn.bfcod.lost_and_found.service.SysUserTokenService;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

/**
 * 登录相关
 *
 * @author bfcod
 */
@RestController
public class SysLoginController extends AbstractController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private SysUserTokenService sysUserTokenService;
    @Autowired
    private SysCaptchaService sysCaptchaService;

    /**
     * 验证码
     */
    @GetMapping("captcha.jpg")
    public void captcha(HttpServletResponse response, String uuid)throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //获取图片验证码
        BufferedImage image = sysCaptchaService.getCaptcha(uuid);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }

    /**
     * 登录
     */
    @PostMapping("/sys/login")
    public Map<String, Object> login(@RequestBody StudentLoginForm form)throws IOException {
//        boolean captcha = sysCaptchaService.validate(form.getUuid(), form.getCaptcha());
//        if(!captcha){
//            return R.error("验证码不正确");
//        }

        //用户信息
        StudentEntity user = studentService.queryByStudentNo(form.getStudentNo());

        //账号不存在、密码错误
        if(user == null || !user.getPassword().equals(new Sha256Hash(form.getPassword(), user.getSalt()).toHex())) {
            return R.error("账号或密码不正确");
        }
        System.out.println("user:"+user+","+"form:"+form);


        //生成token，并保存到数据库
        R r = sysUserTokenService.createToken(user.getId());
        return r.put("user",user);
    }

    /**
     * 注册
     */
    @PostMapping("/sys/register")
    public Map<String, Object> register(@RequestBody StudentEntity student)throws IOException {

        //用学号查询用户信息
        StudentEntity user = studentService.queryByStudentNo(student.getStudentNo());
        //学号未注册
        if(user == null) {
            studentService.saveUser(student);
        }else {
            return R.error("账号已存在，请输入正确的学号或换个学号");
        }
//        System.out.println("user:"+user+","+"student:"+student);
        return R.ok().put("student", student);
    }

    /**
     * 修改登录用户密码
     */
    @SysLog("修改密码")
    @PostMapping("/sys/password")
    public R password(@RequestBody PasswordForm form){
        boolean captcha = sysCaptchaService.validate(form.getUuid(), form.getCaptcha());
        if(!captcha){
            return R.error("验证码不正确");
        }
        Assert.isBlank(form.getNewPassword(), "新密码不为能空");

        //sha256加密
        String password = new Sha256Hash(form.getPassword(), getUser().getSalt()).toHex();
        //sha256加密
        String newPassword = new Sha256Hash(form.getNewPassword(), getUser().getSalt()).toHex();

        //更新密码
        boolean flag = studentService.updatePassword(getUserId(), password, newPassword);
        if(!flag){
            return R.error("原密码不正确");
        }

        return R.ok();
    }

    /**
     * 退出
     */
    @PostMapping("/sys/logout")
    public R logout() {
        sysUserTokenService.logout(getUserId());
        return R.ok();
    }
}
