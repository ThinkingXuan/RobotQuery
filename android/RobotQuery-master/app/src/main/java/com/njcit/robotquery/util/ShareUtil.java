package com.njcit.robotquery.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

import com.njcit.robotquery.moudle.chat.fixtures.MessagesListFixtures;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by youxuan on 2017/4/12 0012.
 * 调用系统分享
 */

public class ShareUtil {

    public static void Share(Activity activity, MessagesListFixtures.Message message) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        // 查询所有可以分享的Activity
        List<ResolveInfo> resInfo = activity.getPackageManager().queryIntentActivities(intent,
                PackageManager.MATCH_DEFAULT_ONLY);
        if (!resInfo.isEmpty()) {
            List<Intent> targetedShareIntents = new ArrayList<Intent>();
            for (ResolveInfo info : resInfo) {
                Intent targeted = new Intent(Intent.ACTION_SEND);
                targeted.setType("text/plain");
                ActivityInfo activityInfo = info.activityInfo;
                Log.v("logcat", "packageName=" + activityInfo.packageName + "Name=" + activityInfo.name);
                // 分享出去的内容
                targeted.putExtra(Intent.EXTRA_TEXT, message.getText());
                // 分享出去的标题
                targeted.putExtra(Intent.EXTRA_SUBJECT, "主题");
                targeted.setPackage(activityInfo.packageName);
                targeted.setClassName(activityInfo.packageName, info.activityInfo.name);
                PackageManager pm = activity.getPackageManager();
                // 微信有2个怎么区分-。- 朋友圈还有微信
                if (info.activityInfo.applicationInfo.loadLabel(pm).toString().equals("微信")
                        || info.activityInfo.applicationInfo.loadLabel(pm).toString().equals("QQ")) {
                    targetedShareIntents.add(targeted);
                }
            }
            // 选择分享时的标题
            Intent chooserIntent = Intent.createChooser(targetedShareIntents.remove(0), "选择分享");
            if (chooserIntent == null) {
                return;
            }
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, targetedShareIntents.toArray(new Parcelable[]{}));
            try {
                activity.startActivity(chooserIntent);
            } catch (android.content.ActivityNotFoundException ex) {

                Toast.makeText(activity, "找不到该分享应用组件", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
