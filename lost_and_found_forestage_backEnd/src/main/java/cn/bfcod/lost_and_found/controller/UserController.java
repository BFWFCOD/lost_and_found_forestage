package cn.bfcod.lost_and_found.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import cn.bfcod.common.utils.PageUtils;
import cn.bfcod.common.utils.R;
import cn.bfcod.lost_and_found.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.bfcod.lost_and_found.service.UserService;


/**
 * 管理员
 *
 * @author bfcod
 * @email 2573948452@qq.com
 * @date 2021-03-05 23:55:19
 */
@RestController
@RequestMapping("lost_and_found/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("lost_and_found:user:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("lost_and_found:user:info")
    public R info(@PathVariable("id") Long id){
		UserEntity user = userService.getById(id);

        return R.ok().put("user", user);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("lost_and_found:user:save")
    public R save(@RequestBody UserEntity user){
		userService.save(user);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("lost_and_found:user:update")
    public R update(@RequestBody UserEntity user){
		userService.updateById(user);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("lost_and_found:user:delete")
    public R delete(@RequestBody Long[] ids){
		userService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
