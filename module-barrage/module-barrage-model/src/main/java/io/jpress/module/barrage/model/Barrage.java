package io.jpress.module.barrage.model;

import io.jboot.db.annotation.Table;
import io.jboot.utils.StrUtil;
import io.jboot.web.json.JsonIgnore;
import io.jpress.commons.utils.CommonsUtils;
import io.jpress.commons.utils.JsoupUtils;
import io.jpress.model.User;
import io.jpress.module.barrage.model.base.BaseBarrage;

import java.util.HashMap;
import java.util.Map;

/**
 * Generated by JPress.
 */
@Table(tableName = "barrage", primaryKey = "id")
public class Barrage extends BaseBarrage<Barrage> {

    private static final long serialVersionUID = 1L;

    public static final String STATUS_NORMAL = "normal"; //正常
    public static final String STATUS_UNAUDITED = "unaudited"; //待审核
    public static final String STATUS_TRASH = "trash"; //垃圾箱

    //用 MAP 对几种状态码与状态文字进行映射
    private static final Map<String, String> statusStrings = new HashMap<>();

    static {
        statusStrings.put(STATUS_NORMAL, "正常");
        statusStrings.put(STATUS_UNAUDITED, "待审核");
        statusStrings.put(STATUS_TRASH, "垃圾箱");
    }

    @Override
    public String getAuthor(){
        User user = get("user");
        return user != null ? user.getNickname() : super.getAuthor();
            //判断user不为空,则返回user的昵称作为作者显示名。如果user为空,则调用父类的getAuthor()方法获取默认的作者名。
    }

    //使用Jsoup的方法提取HTML中的内容，用于从前端获取文章摘要
    public String getText() {
        return JsoupUtils.getText(getContent());
    }


    //在保存和更新数据库数据时，使用escapeModel()方法进行对指定字段的转义，防止xss（跨站攻击）
    //为了防止这种攻击，在保存用户提交的内容之前，需要对一些特殊字符进行转换或者过滤。
    @Override
    public boolean save() {
        CommonsUtils.escapeModel(this, "content");
        JsoupUtils.clean(this, "content");
        return super.save();
    }

    @Override
    public boolean update() {
        CommonsUtils.escapeModel(this, "content");
        JsoupUtils.clean(this, "content");
        return super.update();
    }

    public boolean isNormal() {
        return STATUS_NORMAL.equals(getStatus());
    }

    public boolean isUnaudited() {
        return STATUS_UNAUDITED.equals(getStatus());
    }

    public boolean isTrash() {
        return STATUS_TRASH.equals(getStatus());
    }

}

