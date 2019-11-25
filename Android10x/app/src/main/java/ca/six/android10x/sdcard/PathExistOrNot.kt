package ca.six.android10x.sdcard

import android.os.Bundle
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity

class PathExistOrNot : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isSdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)
        println("szw isSdCardExist = $isSdCardExist")

        println("szw1 = ${this.filesDir.absolutePath}") //=> /data/user/0/ca.six.android10x/files
        println("szw2 = ${Environment.getExternalStorageDirectory().absolutePath}") //=> /storage/emulated/0

        // 取出来是一个File[], 基本上第0个就是内置sd卡; 第1, 2...是外围sd卡(们)了.
        val externalFilesDirs = this.getExternalFilesDirs(null)
        externalFilesDirs.forEach { file ->
            println("szw3 file = ${file.absolutePath}")
        }
        //=> szw3 file = /storage/emulated/0/Android/data/ca.six.android10x/files
        //=> szw3 file = /storage/16FF-3C19/Android/data/ca.six.android10x/files
    }
}