package com.huawei.firstdemo;

import com.huawei.firstdemo.slice.NextAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class NextAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(NextAbilitySlice.class.getName());
    }
}
