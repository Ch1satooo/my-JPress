package io.jpress.module.barrage.service.provider;

import io.jboot.aop.annotation.Bean;
import io.jpress.module.barrage.service.BarrageService;
import io.jpress.module.barrage.model.Barrage;
import io.jpress.commons.service.JPressServiceBase;

@Bean
public class BarrageServiceProvider extends JPressServiceBase<Barrage> implements BarrageService {

}