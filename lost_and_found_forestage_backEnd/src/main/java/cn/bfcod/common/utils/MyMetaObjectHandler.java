package cn.bfcod.common.utils;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component //此注解表示 将其交给spring去管理
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void updateFill(MetaObject metaObject) {
    }

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("publishTime", new Date(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
        this.setFieldValByName("delStatus", 0, metaObject); //新增数据就默认设置0
    }
}
