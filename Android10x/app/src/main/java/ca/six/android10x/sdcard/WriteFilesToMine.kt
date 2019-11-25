package ca.six.android10x.sdcard

import android.os.Bundle
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileOutputStream
import android.content.pm.PackageManager
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.Manifest.permission.READ_EXTERNAL_STORAGE

@Deprecated("this screen will crash, due to Android 10's strict policy")
class WriteFilesToMine : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val REQUEST_CODE_PERMISSION_STORAGE = 111
        val permissions = arrayOf(
            READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE
        )

        for (str in permissions) {
            if (this.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                this.requestPermissions(permissions, REQUEST_CODE_PERMISSION_STORAGE)
                return
            }
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permission: Array<String>,
        grantResults: IntArray
    ) {
        //requestCode就是requestPermissions()的第三个参数
        //permission就是requestPermissions()的第二个参数
        //grantResults是结果，0调试通过，-1表示拒绝

        // Environment.getExternalStorageDirectory()  //=> /storage/emulated/0
        val file = File(Environment.getExternalStorageDirectory(), "one.txt")
        val fos = FileOutputStream(file)  //=> java.io.FileNotFoundException: /storage/emulated/0/one.txt: open failed: EACCES (Permission denied)
        val info = "I am a uuid!"
        fos.write(info.toByteArray())
        fos.close()
        System.out.println("写入成功：")
    }
}