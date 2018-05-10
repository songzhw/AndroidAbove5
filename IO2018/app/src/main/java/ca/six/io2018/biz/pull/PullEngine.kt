package ca.six.io2018.biz.pull

import androidx.work.WorkManager
import androidx.work.ktx.PeriodicWorkRequestBuilder
import ca.six.io2018.model.MockedSp
import java.util.concurrent.TimeUnit

class PullEngine {
    fun schedulePull(){
        val pullRequest = PeriodicWorkRequestBuilder<PullWorker>(24, TimeUnit.HOURS).build()
        WorkManager.getInstance().enqueue(pullRequest)

        val pullRequestID = pullRequest.id
        MockedSp.pullId = pullRequestID.toString() // 模拟存在SharedPreference中
    }
}