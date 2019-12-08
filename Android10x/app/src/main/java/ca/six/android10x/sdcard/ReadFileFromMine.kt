package ca.six.android10x.sdcard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileInputStream

class ReadFileFromMine : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val externalFilesDirs = this.getExternalFilesDirs(null)
        val path = externalFilesDirs[0].absolutePath
        // externalFilesDirs[0].absolutePath //=> /storage/emulated/0/Android/data/ca.six.android10x/files

        val file = File(path, "temp.txt")
        val inputStream = FileInputStream(file);
        val b = ByteArray(inputStream.available());
        inputStream.read(b);
        val result = String(b)
        println("szw fileContent = $result")

    }
}