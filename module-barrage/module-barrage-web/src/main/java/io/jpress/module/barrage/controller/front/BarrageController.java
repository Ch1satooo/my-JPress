package io.jpress.module.barrage.controller.front;

import com.jfinal.aop.Inject;
import com.jfinal.kit.Ret;
import io.jboot.utils.StrUtil;
import io.jboot.web.controller.annotation.RequestMapping;
import io.jpress.JPressOptions;
import io.jpress.commons.utils.CloudWordFilterUtil;
import io.jpress.commons.wordsfilter.WordFilterUtil;
import io.jpress.model.User;
import io.jpress.module.barrage.model.Barrage;
import io.jpress.module.barrage.service.BarrageService;
import io.jpress.service.OptionService;
import io.jpress.service.UserService;
import io.jpress.web.base.TemplateControllerBase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/barrage")
public class BarrageController extends TemplateControllerBase {

    @Inject
    private BarrageService barrageService;

    @Inject
    private UserService userService;

    @Inject
    private OptionService optionService;

    public void index() {
        List<Barrage> barrages = barrageService.findAll();

        setAttr("barrages", barrages);
        render("barrage.html");
    }

//    对应前端form表单，发布弹幕
    public void postBarrage() {

        //controller中使用model对象封装了操作数据，就可以使用getPara方法进行属性值的调用
        Long userId = getParaToLong("userId");
        String nickname = getPara("nickname");
        String content = getPara("content");

        if (StrUtil.isBlank(content)) {
            renderJson(Ret.fail().set("message", "内容不能为空"));
            return;
        } else {
            content = StrUtil.escapeHtml(content);
        }

        if (WordFilterUtil.isMatchedFilterWords(content)) {
            renderJson(Ret.fail().set("message", "非法内容，无法发布弹幕信息"));
            return;
        }

        if (CloudWordFilterUtil.isIllegalWords(content)) {
            renderJson(Ret.fail().set("message", "非法内容，无法发布弹幕"));
            return;
        }


        //是否开启弹幕功能
        Boolean barrageEnable = JPressOptions.isTrueOrEmpty("barrage_enable");
        if (barrageEnable == null || !barrageEnable) {
            renderJson(Ret.fail().set("message", "弹幕功能已关闭"));
            return;
        }


        //是否允许未登录用户发布弹幕
        Boolean unLoginEnable = optionService.findAsBoolByKey("barrage_unlogin_enable");
        if (unLoginEnable == null || !unLoginEnable) {
            if (getLoginedUser() == null) {
                renderJson(Ret.fail().set("message", "未登录用户不能发送弹幕").set("errorCode", 9));
                return;
            }
        }

        //在控制器中构造并设置 Barrage 对象，用于后续插入弹幕的数据。
        Barrage barrage = new Barrage();

        barrage.setUserId(userId);
        barrage.setContent(content);
        barrage.setAuthor(nickname);

        User barrageUser = getLoginedUser();
        if (barrageUser != null) {
            barrage.setUserId(barrageUser.getId());
            barrage.setAuthor(barrageUser.getNickname());
        }

        //是否是管理员必须审核
        Boolean reviewEnable = optionService.findAsBoolByKey("barrage_review_enable");
        if (reviewEnable != null && reviewEnable == true) {
            barrage.setStatus(Barrage.STATUS_UNAUDITED);
        }
        /**
         * 无需管理员审核、直接发布
         */
        else {
            barrage.setStatus(Barrage.STATUS_NORMAL);
        }


        barrageService.saveOrUpdate(barrage);

        //创建返回封装数据的 Ret 对象
        Ret ret = Ret.ok().set("code", 0);

        //用于将视图渲染的数据放入 Map 中
//        Map<String, Object> paras = new HashMap<>();
//        paras.put("comment", comment);
//        paras.put("article", article);
//        if (conmmentUser != null) {
//            paras.put("user", conmmentUser.keepSafe());
//            comment.put("user", conmmentUser.keepSafe());
//        }
//
//        renderHtmltoRet("/WEB-INF/views/commons/article/defaultArticleCommentItem.html", paras, ret);
//        //邮件或短信通知
//        ArticleNotifyKit.notify(article, comment, conmmentUser);

        if (isAjaxRequest()) {
            renderJson(ret);
        } else {
            redirect(getReferer());
        }
    }

}
