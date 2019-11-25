package ca.six.android10x.sdcard

import android.os.Bundle
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileOutputStream

class WriteFilesToMine : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Environment.getExternalStorageDirectory()  //=> /storage/emulated/0
        val file = File(Environment.getExternalStorageDirectory(), "one.txt")
        val fos = FileOutputStream(file)
        val info = "I am a uuid!"
        fos.write(info.toByteArray())
        fos.close()
        System.out.println("写入成功：")

    }
}