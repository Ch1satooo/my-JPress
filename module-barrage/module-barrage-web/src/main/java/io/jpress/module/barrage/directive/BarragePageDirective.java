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

@JFinalDirective("barrages")
public class BarragePageDirective extends JbootDirectiveBase {

    private BarrageService barrageService;

    @Override
    public void onRender(Env env, Scope scope, Writer writer) {

        TemplateControllerBase controller = (TemplateControllerBase) JbootControllerContext.get();

        List<Barrage> barrages = controller.getAttr("barrages");
//        Article article = controller.getAttr("article");
        if (barrages != null) {
        //List<Barrage> barrageList = barrageService.findAll();
        scope.setGlobal("barrages", barrages);
        renderBody(env, scope, writer);
    }
}
}
