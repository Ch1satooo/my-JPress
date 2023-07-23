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
import io.jboot.db.model.Columns;
import io.jboot.utils.StrUtil;
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
    private BarrageService barrageService;
    @Inject
    private MenuService menuService;


    @AdminMenu(text = "管理", groupId = "barrage", order = 0)
    public void list() {
        String status = getPara("status");

        Columns columns = Columns.create()
                .eq("user_id", getParaToLong("userId"))
                .likeAppendPercent("content", getPara("keyword"));

        Page<Barrage> page =
                StrUtil.isBlank(status)
                        ? barrageService._paginateWithoutTrash(getPagePara(), getPageSizePara(), columns)
                        : barrageService._paginateByStatus(getPagePara(), getPageSizePara(), columns, status);

        setAttr("page", page);

        long unauditedCount = barrageService.findCountByStatus(Barrage.STATUS_UNAUDITED);
        long trashCount = barrageService.findCountByStatus(Barrage.STATUS_TRASH);
        long normalCount = barrageService.findCountByStatus(Barrage.STATUS_NORMAL);

        setAttr("unauditedCount", unauditedCount);
        setAttr("trashCount", trashCount);
        setAttr("normalCount", normalCount);
        setAttr("totalCount", unauditedCount + trashCount + normalCount);


//        Page<Barrage> entries = barrageService.paginate(getPagePara(), 10);
//        setAttr("page", entries);
        render("barrage/barrage_list.html");
    }


    public void edit() {
        //获取要查询的弹幕ID
        int entryId = getParaToInt(0, 0);
        //按ID数据库查询
        Barrage entry = entryId > 0 ? barrageService.findById(entryId) : null;

        setAttr("barrage", entry);
        //set当前时间到 model
        set("now",new Date());
        render("barrage/barrage_edit.html");
    }

    public void doSave() {
        Barrage entry = getModel(Barrage.class,"barrage");
        barrageService.saveOrUpdate(entry);
        renderJson(Ret.ok().set("id", entry.getId()));
    }

    public void doDel() {
        Long id = getIdPara();
        render(barrageService.deleteById(id) ? Ret.ok() : Ret.fail());
    }

    @EmptyValidate(@Form(name = "ids"))
    public void doDelByIds() {
        barrageService.batchDeleteByIds(getParaSet("ids").toArray());
        renderOkJson();
    }


    @AdminMenu(text = "设置", groupId = "barrage", order = 1)
    public void setting(){ render("barrage/setting.html"); }

    public void doTrash() {
        Long id = getIdPara();
        render(barrageService.doChangeStatus(id, Barrage.STATUS_TRASH) ? OK : FAIL);
    }

    public void doNormal() {
        Long id = getIdPara();
        render(barrageService.doChangeStatus(id, Barrage.STATUS_NORMAL) ? OK : FAIL);
    }

    public void doChangeStatus(Long id, String status) {
        render(barrageService.doChangeStatus(id, status) ? OK : FAIL);
    }

}
