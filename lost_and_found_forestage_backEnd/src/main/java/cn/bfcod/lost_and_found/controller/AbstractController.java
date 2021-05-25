

package cn.bfcod.lost_and_found.controller;

import cn.bfcod.lost_and_found.entity.StudentEntity;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller公共组件
 *
 * @author bfcod
 */
public abstract class AbstractController {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	protected StudentEntity getUser() {
		return (StudentEntity) SecurityUtils.getSubject().getPrincipal();
	}

	protected Long getUserId() {
		return getUser().getId();
	}
	protected Long getStudentNo() {
		return getUser().getStudentNo();
	}
}
