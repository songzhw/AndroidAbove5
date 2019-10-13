package ca.six.jet.security

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
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

        this.getSharedPreferences("normal1", Context.MODE_PRIVATE)
            .edit()
            .putString("key001", "value001")
            .apply()

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

        // 因为key也被加密了, 所以用"key001"取不出来值 (因为没有加密过的key001的新key存在)
        btnReadSp.setOnClickListener {
            val value = sp.getString("key001", "[empty11]")
            tvSpInfo.text = value
        }

        btnReadDecryptSp.setOnClickListener {
            val value = sp.getString("key200", "[empty22]")
            tvSpInfo.text = value
        }
    }
}