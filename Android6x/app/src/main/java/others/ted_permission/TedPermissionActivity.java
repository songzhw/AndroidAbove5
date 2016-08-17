package others.ted_permission;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.WindowManager;


import java.util.ArrayList;

import de.greenrobot.event.EventBus;
import others.ted_permission.busevent.TedPermissionEvent;

/**
 * Created by TedPark on 16. 2. 17..
 */
public class TedPermissionActivity extends Activity {

    public static final int REQ_CODE_PERMISSION_REQUEST = 10;
    public static final int REQ_CODE_REQUEST_SETTING = 20;


    public static final String EXTRA_PERMISSIONS = "permissions";
    public static final String EXTRA_RATIONALE_MESSAGE = "rationale_message";
    public static final String EXTRA_DENY_MESSAGE = "deny_message";
    public static final String EXTRA_PACKAGE_NAME = "package_name";
    public static final String EXTRA_SETTING_BUTTON = "setting_button";
    public static final String EXTRA_RATIONALE_CONFIRM_TEXT = "rationale_confirm_text";
    public static final String EXTRA_DENIED_DIALOG_CLOSE_TEXT = "denied_dialog_close_text";

    String rationale_message, denyMessage, packageName, deniedCloseButtonText, rationaleConfirmText;
    String[] permissions;
    boolean hasSettingButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        initData(savedInstanceState);
        checkPermissions(false);
    }


    private void initData(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            permissions = savedInstanceState.getStringArray(EXTRA_PERMISSIONS);
            rationale_message = savedInstanceState.getString(EXTRA_RATIONALE_MESSAGE);
            denyMessage = savedInstanceState.getString(EXTRA_DENY_MESSAGE);
            packageName = savedInstanceState.getString(EXTRA_PACKAGE_NAME);


            hasSettingButton = savedInstanceState.getBoolean(EXTRA_SETTING_BUTTON, true);

            rationaleConfirmText = savedInstanceState.getString(EXTRA_RATIONALE_CONFIRM_TEXT);
            deniedCloseButtonText = savedInstanceState.getString(EXTRA_DENIED_DIALOG_CLOSE_TEXT);
        } else {

            Intent intent = getIntent();
            permissions = intent.getStringArrayExtra(EXTRA_PERMISSIONS);
            rationale_message = intent.getStringExtra(EXTRA_RATIONALE_MESSAGE);
            denyMessage = intent.getStringExtra(EXTRA_DENY_MESSAGE);
            packageName = intent.getStringExtra(EXTRA_PACKAGE_NAME);
            hasSettingButton = intent.getBooleanExtra(EXTRA_SETTING_BUTTON, true);
            rationaleConfirmText = intent.getStringExtra(EXTRA_RATIONALE_CONFIRM_TEXT);
            deniedCloseButtonText = intent.getStringExtra(EXTRA_DENIED_DIALOG_CLOSE_TEXT);

        }


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putStringArray(EXTRA_PERMISSIONS, permissions);
        outState.putString(EXTRA_RATIONALE_MESSAGE, rationale_message);
        outState.putString(EXTRA_DENY_MESSAGE, denyMessage);
        outState.putString(EXTRA_PACKAGE_NAME, packageName);
        outState.putBoolean(EXTRA_SETTING_BUTTON, hasSettingButton);
        outState.putString(EXTRA_SETTING_BUTTON, deniedCloseButtonText);
        outState.putString(EXTRA_RATIONALE_CONFIRM_TEXT, rationaleConfirmText);

        super.onSaveInstanceState(outState);
    }

    private void checkPermissions(boolean fromOnActivityResult) {
        ArrayList<String> needPermissions = new ArrayList<>();

        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                needPermissions.add(permission);
            }
        }

        boolean isShowRationale = false;
        for (String permission : needPermissions) {
            isShowRationale = ActivityCompat.shouldShowRequestPermissionRationale(this, permission);
        }


        if (needPermissions.isEmpty()) {
            permissionGranted();
        } else if (fromOnActivityResult) { //From Setting Activity
            permissionDenied(needPermissions);
        } else if (isShowRationale && !TextUtils.isEmpty(rationale_message)) { //Need Show Rationale
            showRationaleDialog(needPermissions);
        } else { //Need Request Permissions
            requestPermissions(needPermissions);
        }
    }

    private void permissionGranted() {
        EventBus.getDefault().post(new TedPermissionEvent(true, null));
        finish();
        overridePendingTransition(0, 0);
    }


    private void permissionDenied(ArrayList<String> deniedpermissions) {
        EventBus.getDefault().post(new TedPermissionEvent(false, deniedpermissions));
        finish();
        overridePendingTransition(0, 0);
    }


    public void requestPermissions(ArrayList<String> needPermissions) {
        ActivityCompat.requestPermissions(this, needPermissions.toArray(new String[needPermissions.size()]), REQ_CODE_PERMISSION_REQUEST);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        ArrayList<String> deniedPermissions = new ArrayList<>();
        for (int i = 0; i < permissions.length; i++) {
            String permission = permissions[i];
            if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                deniedPermissions.add(permission);
            }
        }

        if (deniedPermissions.isEmpty()) {
            permissionGranted();
        } else {
            showPermissionDenyDialog(deniedPermissions);
        }
    }


    private void showRationaleDialog(final ArrayList<String> needPermissions) {
        new AlertDialog.Builder(this)
                .setMessage(rationale_message)
                .setCancelable(false)
                .setPositiveButton(rationaleConfirmText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        requestPermissions(needPermissions);
                    }
                })
                .show();
    }

    public void showPermissionDenyDialog(final ArrayList<String> deniedPermissions) {
        if (TextUtils.isEmpty(denyMessage)) {
            // denyMessage 설정 안함
            permissionDenied(deniedPermissions);
            return;
        }


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(denyMessage)
                .setCancelable(false)
                .setNegativeButton(deniedCloseButtonText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        permissionDenied(deniedPermissions);
                    }
                });

        if (hasSettingButton) {
            builder.setPositiveButton("Setting", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    try {
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                                .setData(Uri.parse("package:" + packageName));
                        startActivityForResult(intent, REQ_CODE_REQUEST_SETTING);
                    } catch (ActivityNotFoundException e) {
                        e.printStackTrace();
                        Intent intent = new Intent(Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS);
                        startActivityForResult(intent, REQ_CODE_REQUEST_SETTING);
                    }
                }
            });
        }
        builder.show();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQ_CODE_REQUEST_SETTING:
                checkPermissions(true);
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
        }

    }

}
