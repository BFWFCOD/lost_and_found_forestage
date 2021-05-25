package cn.bfcod.lost_and_found.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import cn.bfcod.common.utils.PageUtils;
import cn.bfcod.common.utils.R;
import cn.bfcod.lost_and_found.entity.LostThingsEntity;
import cn.bfcod.lost_and_found.entity.LostthingsRelationEntity;
import cn.bfcod.lost_and_found.entity.StudentEntity;
import cn.bfcod.lost_and_found.service.LostThingsService;
import cn.bfcod.lost_and_found.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.bfcod.lost_and_found.service.LostthingsRelationService;


/**
 * 失物认领关联
 *
 * @author bfcod
 * @email 2573948452@qq.com
 * @date 2021-03-05 23:55:19
 */
@RestController
@RequestMapping("lost_and_found/lostthingsrelation")
public class LostthingsRelationController {
    @Autowired
    private LostthingsRelationService lostthingsRelationService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private LostThingsService lostThingsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("lost_and_found:lostthingsrelation:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = lostthingsRelationService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("lost_and_found:lostthingsrelation:info")
    public R info(@PathVariable("id") Long id){
		LostthingsRelationEntity lostthingsRelation = lostthingsRelationService.getById(id);
        return R.ok().put("lostthingsRelation", lostthingsRelation);
    }

    /**
     * 信息
     */
    @RequestMapping("/userInfo/{id}")
    //@RequiresPermissions("lost_and_found:lostthingsrelation:info")
    public R relationuserinfo(@PathVariable("id") Long id){
        LostthingsRelationEntity lostthingsRelation = lostthingsRelationService.getByLostthingsId(id);
        if (lostthingsRelation==null){
            return R.error(707, "未找到关系数据");
        }
        if (lostthingsRelation.getStudentId() == 0 || lostthingsRelation.getStudentId() == null) {
            return R.error(707, "未找到用户信息");
        }
        StudentEntity student = studentService.getById(lostthingsRelation.getStudentId());
        if (student.getId() == 0 || student.getId() == null) {
            return R.error(707, "未找到用户信息");
        }
        return R.ok().put("student", student);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("lost_and_found:lostthingsrelation:save")
    public R save(@RequestBody LostthingsRelationEntity lostthingsRelation){
        boolean flag=lostthingsRelationService.save(lostthingsRelation);
        if (!flag){
            return R.error(707, "寻到失败");
        }else{
            LostThingsEntity lostThings=new LostThingsEntity();
            lostThings.setId(lostthingsRelation.getLostthingsId());
            lostThings.setStatus(0);
            if (!lostThingsService.updateById(lostThings)){
                return R.error(707, "寻到失败");
            }
        }
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("lost_and_found:lostthingsrelation:update")
    public R update(@RequestBody LostthingsRelationEntity lostthingsRelation){
		lostthingsRelationService.updateById(lostthingsRelation);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("lost_and_found:lostthingsrelation:delete")
    public R delete(@RequestBody Long[] ids){
		lostthingsRelationService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
