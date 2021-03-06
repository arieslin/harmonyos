package com.huawei.firstdemo;

import com.huawei.firstdemo.slice.FourthAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class FourthAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(FourthAbilitySlice.class.getName());
    }
}
