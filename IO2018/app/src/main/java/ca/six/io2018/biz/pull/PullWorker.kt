package ca.six.io2018.biz.pull

import androidx.work.Data
import androidx.work.Worker

class PullWorker : Worker() {
    override fun doWork(): WorkerResult {
        // 模拟设置页面中的"是否接受推送"是否被勾选
        val isOkay = this.inputData.getBoolean("key_accept_bg_work", false)
        if(isOkay) {
            Thread.sleep(5000) //模拟长时间工作

            val pulledResult = startPull()
            val output = Data.Builder().putString("key_pulled_result", pulledResult).build()
            outputData = output
            return WorkerResult.SUCCESS
        } else {
            return WorkerResult.FAILURE
        }
    }

    fun startPull() : String{
        return "szw [worker] pull messages from backend"
    }
}