package cn.bfcod.lost_and_found.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import cn.bfcod.common.utils.PageUtils;
import cn.bfcod.common.utils.R;
import cn.bfcod.lost_and_found.entity.PickThingsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.bfcod.lost_and_found.service.PickThingsService;


/**
 * 寻到物品
 *
 * @author bfcod
 * @email 2573948452@qq.com
 * @date 2021-03-05 23:55:20
 */
@RestController
@RequestMapping("lost_and_found/pickthings")
public class PickThingsController extends AbstractController {
    @Autowired
    private PickThingsService pickThingsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("lost_and_found:pickthings:list")
    public R list(@RequestParam Map<String, Object> params){
        System.out.println(params);
        PageUtils page = pickThingsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 根据帖子id查询
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("lost_and_found:pickthings:info")
    public R info(@PathVariable("id") Long id){
		PickThingsEntity pickThings = pickThingsService.getById(id);

        return R.ok().put("pickThings", pickThings);
    }

    /**
     * 根据用户学号查询
     */
    @RequestMapping("/personal")
    //@RequiresPermissions("lost_and_found:pickthings:info")
    public R personal(@RequestParam Map<String, Object> params){
        PageUtils page = pickThingsService.getByStudentNo(params,getStudentNo());

        return R.ok().put("page", page);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("lost_and_found:pickthings:save")
    public R save(@RequestBody PickThingsEntity pickThings){
		pickThingsService.save(pickThings);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("lost_and_found:pickthings:update")
    public R update(@RequestBody PickThingsEntity pickThings){
		pickThingsService.updateById(pickThings);

        return R.ok();
    }
    /**
     * 修改状态
     */
    @RequestMapping("/update/status")
    //@RequiresPermissions("product:brand:update")
    public R updateStatus(@RequestBody PickThingsEntity pickThings) {
        pickThingsService.updateById(pickThings);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("lost_and_found:pickthings:delete")
    public R delete(@RequestBody Long[] ids){
		pickThingsService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
