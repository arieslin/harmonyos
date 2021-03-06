package com.huawei.firstdemo.slice;

import com.huawei.firstdemo.FourthAbility;
import com.huawei.firstdemo.NextAbility;
import com.huawei.firstdemo.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.distributedschedule.interwork.DeviceInfo;
import ohos.distributedschedule.interwork.DeviceManager;

import java.util.List;

public class MainAbilitySlice extends AbilitySlice implements Component.ClickedListener {

    private Button switchToSliceButton;
    private Button switchToLocalFAButton;
    private Button switchToRemoteFAButton;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);

        initView();
    }

    public void initView() {

        switchToSliceButton = (Button) findComponentById(ResourceTable.Id_switch_slice_btn);
        switchToLocalFAButton = (Button) findComponentById(ResourceTable.Id_switch_localFA_btn);
        switchToRemoteFAButton = (Button) findComponentById(ResourceTable.Id_switch_remoteFA_btn);

        switchToSliceButton.setClickedListener(this);
        switchToLocalFAButton.setClickedListener(this);
        switchToRemoteFAButton.setClickedListener(this);

    }

    public void switchToSlice() {
        //切换到同一page下的slice  必备条件：1，config.json里面添加aciton 2，ability里面添加addActionRoute
        present(new ThirdAbilitySlice(), new Intent());
    }

    public void switchToLocalFA() {
        //切换到本地的ability，即FA  必备条件：1，新建ability即可
        switchToFA("", NextAbility.class.getName());
    }

    public void switchToRemoteFA() {
        //切换到远端的ability，即FA
        // 必备条件：1，config.json里面添加三个分布式权限 2，Ability声明需要使用的权限 3，新建ability 4，获取设备列表
        List<DeviceInfo> onlineDevices = DeviceManager.getDeviceList(DeviceInfo.FLAG_GET_ONLINE_DEVICE);
        if (onlineDevices.isEmpty()) {
            switchToLocalFA();
        } else {
            for (DeviceInfo device : onlineDevices) {
                switchToFA(device.getDeviceId(), FourthAbility.class.getName());
            }
        }
    }

    public void switchToFA(String deviceId, String abilityName) {
        //切换到指定的ability，即FA
        Intent intent = new Intent();
        Operation operation = new Intent.OperationBuilder()
                .withDeviceId(deviceId)
                .withBundleName(getBundleName())
                .withAbilityName(abilityName)
                .build();
        intent.setOperation(operation);
        startAbility(intent);
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }

    @Override
    public void onClick(Component component) {
        switch (component.getId()) {
            case ResourceTable.Id_switch_slice_btn:
                switchToSlice();
                break;
            case ResourceTable.Id_switch_localFA_btn:
                switchToLocalFA();
                break;
            case ResourceTable.Id_switch_remoteFA_btn:
                switchToRemoteFA();
                break;
            default:
                break;
        }

    }
}
