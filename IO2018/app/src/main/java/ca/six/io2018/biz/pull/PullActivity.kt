package ca.six.io2018.biz.pull

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import androidx.work.WorkManager
import androidx.work.WorkStatus
import android.arch.lifecycle.Observer
import ca.six.io2018.model.MockedSp
import java.util.*

class PullActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // UUID实现了Serializable接口. 也能由toString(), fromString()与String互转
        val uuid = UUID.fromString(MockedSp.pullId)
        WorkManager.getInstance().getStatusById(uuid)
                .observe(this, Observer<WorkStatus> { status ->
                    if (status != null){
                        val pulledResult = status.outputData.getString("key_pulled_result", "")
                        println("szw Activity getResultFromBackend : $pulledResult")
                    }
                })
    }
}