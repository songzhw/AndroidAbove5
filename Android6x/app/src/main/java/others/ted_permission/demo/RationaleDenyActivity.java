package others.ted_permission.demo;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

import others.ted_permission.PermissionListener;
import others.ted_permission.TedPermission;

public class RationaleDenyActivity extends Activity {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                Toast.makeText(RationaleDenyActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                Toast.makeText(RationaleDenyActivity.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }
        };

        new TedPermission(this)
                .setPermissionListener(permissionlistener)
                .setRationaleMessage("we need permission for read contact and find your location")
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.READ_CONTACTS, Manifest.permission.ACCESS_FINE_LOCATION)
                .check();

    }
}