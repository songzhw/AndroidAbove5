package ca.six.io2018.biz.pull

import androidx.work.WorkManager
import androidx.work.ktx.PeriodicWorkRequestBuilder
import java.util.concurrent.TimeUnit

class PullEngine {
    fun schedulePull(){
        val pullRequest = PeriodicWorkRequestBuilder<PullWorker>(24, TimeUnit.HOURS).build()
        WorkManager.getInstance().enqueue(pullRequest)
    }
}