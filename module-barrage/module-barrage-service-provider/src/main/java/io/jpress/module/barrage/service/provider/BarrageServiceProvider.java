package io.jpress.module.barrage.service.provider;

import com.jfinal.aop.Inject;
import io.jboot.aop.annotation.Bean;
import io.jboot.db.model.Column;
import io.jpress.module.barrage.service.BarrageService;
import io.jpress.module.barrage.model.Barrage;
import io.jpress.commons.service.JPressServiceBase;
import io.jpress.service.UserService;

@Bean
public class BarrageServiceProvider extends JPressServiceBase<Barrage> implements BarrageService {

    @Inject
    private UserService userService;


    @Override
    public Barrage findById(Object id){
        Barrage barrage = super.findById(id);
        userService.join(barrage, "user_id");
        return barrage;
    }

    @Override
    public boolean deleteById(Object id){
        Barrage barrage = findById(id);
        if (barrage == null){
            return false;
        }

        return super.deleteById(id);
    }

    @Override
    public boolean batchDeleteByIds(Object... ids){
        for (Object id : ids) {
            deleteById(id);
        }
        return true;
    }

    @Override
    public boolean doChangeStatus(long id, String status) {
        Barrage barrage = findById(id);
        barrage.setStatus(status);
        return update(barrage);
    }

    @Override
    public long findCountByStatus(String status) {
        return DAO.findCountByColumn(Column.create("status",status));
    }


}