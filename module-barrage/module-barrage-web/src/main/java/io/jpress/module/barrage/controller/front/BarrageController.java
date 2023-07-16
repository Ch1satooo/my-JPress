package io.jpress.module.barrage.controller.front;

import com.jfinal.aop.Inject;
import io.jboot.web.controller.annotation.RequestMapping;
import io.jpress.module.barrage.model.Barrage;
import io.jpress.module.barrage.service.BarrageService;
import io.jpress.service.OptionService;
import io.jpress.service.UserService;
import io.jpress.web.base.TemplateControllerBase;

import java.util.List;

@RequestMapping("/barrage")
public class BarrageController extends TemplateControllerBase {

    @Inject
    private BarrageService barrageService;

    @Inject
    private UserService userService;

    @Inject
    private OptionService optionService;

    public void index() {
        List<Barrage> barrage = barrageService.findAll();

        setAttr("barrage", barrage);
        render("barrage.html");
    }

//    对应前端form表单
    public void postBarrage(){

    }
}
