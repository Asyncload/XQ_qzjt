package com.lbxq.screen;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

// LSPosed 模块入口，兼容 API 101，同时测试微信+支付宝
public class XscreenModule implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        // 全局异常捕获，防止模块崩溃带崩LSPosed
        try {
            // 微信：com.tencent.mm
            // 支付宝：com.eg.android.AlipayGphone
            String pkg = lpparam.packageName;

            if ("com.tencent.mm".equals(pkg) || "com.eg.android.AlipayGphone".equals(pkg)) {
                // 打印日志，确认模块被正常加载
                XposedBridge.log("✅ XscreenModule 成功 Hook 到：" + pkg);

                // 后续截图逻辑可以在这里扩展
                hookTargetApp(lpparam);
            }
        } catch (Throwable t) {
            // 捕获所有异常，模块出问题也不会崩框架
            XposedBridge.log("❌ XscreenModule 发生异常：" + t.getMessage());
        }
    }

    // 单独抽出来的Hook逻辑，方便后续扩展
    private void hookTargetApp(XC_LoadPackage.LoadPackageParam lpparam) {
        try {
            // 这里可以写你针对目标APP的截图相关Hook代码
            // 示例：先不写复杂逻辑，只做测试
            XposedBridge.log("ℹ️ 进入目标APP Hook 分支：" + lpparam.packageName);
        } catch (Throwable t) {
            XposedBridge.log("❌ hookTargetApp 出错：" + t.getMessage());
        }
    }
}
