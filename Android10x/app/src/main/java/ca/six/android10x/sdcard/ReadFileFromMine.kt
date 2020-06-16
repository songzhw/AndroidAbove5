package ca.six.android10x.sdcard

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileInputStream

class ReadFileFromMine : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tv = TextView(this)
        tv.setTextSize(30f)
        setContentView(tv)

        val externalFilesDirs = this.getExternalFilesDirs(null)
        val path = externalFilesDirs[0].absolutePath
        // externalFilesDirs[0].absolutePath //=> /storage/emulated/0/Android/data/ca.six.android10x/files

        val file = File(path, "temp.txt")
        val inputStream = FileInputStream(file);
        val b = ByteArray(inputStream.available());
        inputStream.read(b);
        val result = String(b)
        tv.text = result
        println("szw fileContent = $result") //=> 成功读出文本的内容



    }
}