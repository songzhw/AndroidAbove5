package ca.six.jet.security

import android.app.Activity
import android.os.Bundle

// ''androidx.security:security-crypto' 至少得是minSdk >= 23, 不然会有Manifest Merge Error
class SecurityDemo : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}