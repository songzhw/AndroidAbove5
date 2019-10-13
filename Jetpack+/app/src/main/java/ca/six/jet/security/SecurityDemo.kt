package ca.six.jet.security

import android.app.Activity
import android.os.Bundle
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import ca.six.jet.R
import kotlinx.android.synthetic.main.activity_security_sp.*

// ''androidx.security:security-crypto' 至少得是minSdk >= 23, 不然会有Manifest Merge Error
class SecurityDemo : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_security_sp)

        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        val sp = EncryptedSharedPreferences.create(
            "normal1", masterKeyAlias, this,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
        val editor = sp.edit()

        btnSaveSp.setOnClickListener {
            editor.putString("key200", "value200")
            editor.apply()
        }

        // 因为key也被加密了, 所以用"key200"取不出来值
        btnReadSp.setOnClickListener {
            val values = sp.all
            for ( key in values.keys){
                println("szw key = $key, value=${values.get(key)}")
            }
        }

        btnReadDecryptSp.setOnClickListener {

        }
    }
}