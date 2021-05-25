

package cn.bfcod.lost_and_found.form;

import lombok.Data;

/**
 * 密码表单
 *
 * @author bfcod
 */
@Data
public class PasswordForm {
    /**
     * 用户id
     */
    private Long id;
    /**
     * 原密码
     */
    private String password;
    /**
     * 新密码
     */
    private String newPassword;
    /**
     * 验证码
     * */
    private String captcha;
    /**
     * uuid
     * */
    private String uuid;

}
