package io.jpress.module.barrage.directive;

import com.jfinal.plugin.activerecord.Page;
import com.jfinal.template.Env;
import com.jfinal.template.io.Writer;
import com.jfinal.template.stat.Scope;
import io.jboot.web.controller.JbootControllerContext;
import io.jboot.web.directive.annotation.JFinalDirective;
import io.jboot.web.directive.base.JbootDirectiveBase;
import io.jpress.module.barrage.model.Barrage;
import io.jpress.module.barrage.service.BarrageService;
import io.jpress.web.base.TemplateControllerBase;

import java.util.List;

@JFinalDirective("barrageList")
public class BarragePageDirective extends JbootDirectiveBase {

    private BarrageService barrageService;

    @Override
    public void onRender(Env env, Scope scope, Writer writer) {

        TemplateControllerBase controller = (TemplateControllerBase) JbootControllerContext.get();

        Barrage barrage = controller.getAttr("barrage");
//        Article article = controller.getAttr("article");
        if (barrage != null) {
            List<Barrage> barrageList = barrageService.findAll();
            scope.setGlobal("barrageList", barrageList);
            renderBody(env, scope, writer);
        }
    }
}
