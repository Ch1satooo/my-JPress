/**
 * Copyright (c) 2016-2023, Michael Yang 杨福海 (fuhai999@gmail.com).
 * <p>
 * Licensed under the GNU Lesser General Public License (LGPL) ,Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.jpress.module.barrage.controller.admin;

import com.jfinal.aop.Inject;
import com.jfinal.kit.Ret;
import com.jfinal.plugin.activerecord.Page;
import io.jboot.web.controller.annotation.RequestMapping;
import io.jboot.web.validate.EmptyValidate;
import io.jboot.web.validate.Form;
import io.jpress.JPressConsts;
import io.jpress.core.menu.annotation.AdminMenu;
import io.jpress.module.barrage.model.Barrage;
import io.jpress.module.barrage.service.BarrageService;
import io.jpress.service.MenuService;
import io.jpress.web.base.AdminControllerBase;
import java.util.Date;


@RequestMapping(value = "/admin/barrage", viewPath = JPressConsts.DEFAULT_ADMIN_VIEW)
public class _BarrageController extends AdminControllerBase {

    @Inject
    private BarrageService service;
    @Inject
    private MenuService menuService;


    @AdminMenu(text = "管理", groupId = "barrage", order = 0)
    public void index() {
        Page<Barrage> entries=service.paginate(getPagePara(), 10);
        setAttr("page", entries);
        render("barrage/barrage_list.html");
    }


    public void edit() {
        int entryId = getParaToInt(0, 0);

        Barrage entry = entryId > 0 ? service.findById(entryId) : null;
        setAttr("barrage", entry);
        set("now",new Date());
        render("barrage/barrage_edit.html");
    }

    public void doSave() {
        Barrage entry = getModel(Barrage.class,"barrage");
        service.saveOrUpdate(entry);
        renderJson(Ret.ok().set("id", entry.getId()));
    }


    public void doDel() {
        Long id = getIdPara();
        render(service.deleteById(id) ? Ret.ok() : Ret.fail());
    }

    @EmptyValidate(@Form(name = "ids"))
    public void doDelByIds() {
        service.batchDeleteByIds(getParaSet("ids").toArray());
        renderOkJson();
    }


    @AdminMenu(text = "设置", groupId = "barrage", order = 1)
    public void setting(){ render("barrage/setting.html"); }

}
