package cn.bfcod.lost_and_found.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import cn.bfcod.common.utils.PageUtils;
import cn.bfcod.common.utils.R;
import cn.bfcod.lost_and_found.entity.PickThingsEntity;
import cn.bfcod.lost_and_found.entity.PickthingsRelationEntity;
import cn.bfcod.lost_and_found.entity.StudentEntity;
import cn.bfcod.lost_and_found.service.PickThingsService;
import cn.bfcod.lost_and_found.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.bfcod.lost_and_found.service.PickthingsRelationService;


/**
 * 寻到物品关联
 *
 * @author bfcod
 * @email 2573948452@qq.com
 * @date 2021-03-05 23:55:19
 */
@RestController
@RequestMapping("lost_and_found/pickthingsrelation")
public class PickthingsRelationController {
    @Autowired
    private PickthingsRelationService pickthingsRelationService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private PickThingsService pickThingsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("lost_and_found:pickthingsrelation:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = pickthingsRelationService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("lost_and_found:pickthingsrelation:info")
    public R info(@PathVariable("id") Long id) {
        PickthingsRelationEntity pickthingsRelation = pickthingsRelationService.getById(id);

        return R.ok().put("pickthingsRelation", pickthingsRelation);
    }

    /**
     * 信息
     */
    @RequestMapping("/userInfo/{id}")
    @Transactional
    //@RequiresPermissions("lost_and_found:pickthingsrelation:info")
    public R relationuserinfo(@PathVariable("id") Long id) {
        PickthingsRelationEntity pickthingsRelation = pickthingsRelationService.getByPickthingsId(id);
        if (pickthingsRelation==null){
            return R.error(707, "未找到关系数据");
        }
        if (pickthingsRelation.getStudentId() == 0 || pickthingsRelation.getStudentId() == null) {
            return R.error(707, "未找到用户信息");
        }
        StudentEntity student = studentService.getById(pickthingsRelation.getStudentId());
        if (student.getId() == 0 || student.getId() == null) {
            return R.error(707, "未找到用户信息");
        }
        return R.ok().put("student", student);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @Transactional
    //@RequiresPermissions("lost_and_found:pickthingsrelation:save")
    public R save(@RequestBody PickthingsRelationEntity pickthingsRelation) {
        boolean flag=pickthingsRelationService.save(pickthingsRelation);
        if (!flag){
            return R.error(707, "认领失败");
        }else{
            PickThingsEntity pickThings=new PickThingsEntity();
            pickThings.setId(pickthingsRelation.getPickthingsId());
            pickThings.setStatus(0);
            if (!pickThingsService.updateById(pickThings)){
                return R.error(707, "认领失败");
            }
        }
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("lost_and_found:pickthingsrelation:update")
    public R update(@RequestBody PickthingsRelationEntity pickthingsRelation) {
        pickthingsRelationService.updateById(pickthingsRelation);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("lost_and_found:pickthingsrelation:delete")
    public R delete(@RequestBody Long[] ids) {
        pickthingsRelationService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
