package others.ted_permission;

import android.content.Context;
import android.content.Intent;


import de.greenrobot.event.EventBus;
import others.ted_permission.busevent.TedPermissionEvent;

/**
 * Created by TedPark on 16. 2. 17..
 */
public class TedInstance {

    public PermissionListener listener;
    public String[] permissions;
    public String rationaleMessage;
    public String denyMessage;
    public boolean hasSettingBtn = true;

    public String deniedCloseButtonText;
    public String rationaleConfirmText;
    Context context;


    public TedInstance(Context context) {
        this.context = context;

        EventBus.getDefault().register(this);

        deniedCloseButtonText = "Close";
        rationaleConfirmText = "Confirm";
    }


    public void checkPermissions() {
        Intent intent = new Intent(context, TedPermissionActivity.class);
        intent.putExtra(TedPermissionActivity.EXTRA_PERMISSIONS, permissions);
        intent.putExtra(TedPermissionActivity.EXTRA_RATIONALE_MESSAGE, rationaleMessage);
        intent.putExtra(TedPermissionActivity.EXTRA_DENY_MESSAGE, denyMessage);
        intent.putExtra(TedPermissionActivity.EXTRA_PACKAGE_NAME, context.getPackageName());
        intent.putExtra(TedPermissionActivity.EXTRA_SETTING_BUTTON, hasSettingBtn);
        intent.putExtra(TedPermissionActivity.EXTRA_DENIED_DIALOG_CLOSE_TEXT, "Close");
        intent.putExtra(TedPermissionActivity.EXTRA_RATIONALE_CONFIRM_TEXT, "Confirm");
        context.startActivity(intent);
    }


    public void onEvent(TedPermissionEvent event) {
        if (event.hasPermission()) {
            listener.onPermissionGranted();
        } else {
            listener.onPermissionDenied(event.getDeniedPermissions());
        }
        EventBus.getDefault().unregister(this);
    }

}
