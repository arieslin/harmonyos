package com.huawei.firstdemo;

import com.huawei.firstdemo.slice.MainAbilitySlice;
import com.huawei.firstdemo.slice.ThirdAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class MainAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        // 开发者显示声明需要使用的权限
        requestPermissionsFromUser(new String[]{"ohos.permission.DISTRIBUTED_DATASYNC"}, 0);
        super.onStart(intent);
        super.setMainRoute(MainAbilitySlice.class.getName());
        // 添加路由
        addActionRoute("action.third", ThirdAbilitySlice.class.getName());
    }
}
