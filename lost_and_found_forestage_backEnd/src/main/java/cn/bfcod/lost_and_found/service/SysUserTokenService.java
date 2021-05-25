

package cn.bfcod.lost_and_found.service;

import cn.bfcod.common.utils.R;
import cn.bfcod.lost_and_found.entity.StudentTokenEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 用户Token
 *
 * @author bfcod
 */
public interface SysUserTokenService extends IService<StudentTokenEntity> {

	/**
	 * 生成token
	 * @param userId  用户ID
	 */
	R createToken(long userId);

	/**
	 * 退出，修改token值
	 * @param userId  用户ID
	 */
	void logout(long userId);

}
