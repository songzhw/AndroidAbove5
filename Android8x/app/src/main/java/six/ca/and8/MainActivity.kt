package six.ca.and8

import android.app.ListActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.SimpleAdapter

import java.text.Collator
import java.util.ArrayList
import java.util.Collections
import java.util.Comparator
import java.util.HashMap

class MainActivity : ListActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        listAdapter = SimpleAdapter(this, data,
                android.R.layout.simple_list_item_1, arrayOf("title"),
                intArrayOf(android.R.id.text1))
        listView.isTextFilterEnabled = true
    }

    protected val data: List<Map<String, Any>>
        get() {
            val myData = ArrayList<Map<String, Any>>()

            val mainIntent = Intent(Intent.ACTION_MAIN, null)
            mainIntent.addCategory("ca.six.and8")

            val pm = packageManager
            val list = pm.queryIntentActivities(mainIntent, 0) ?: return myData

            val len = list.size
            val entries = HashMap<String, Boolean>()
            for (i in 0..len - 1) {
                val info = list[i]
                val labelSeq = info.loadLabel(pm)
                val label = labelSeq?.toString() ?: info.activityInfo.name

                addItem(myData, label, activityIntent(
                        info.activityInfo.applicationInfo.packageName,
                        info.activityInfo.name))
            }

            Collections.sort(myData, sDisplayNameComparator)

            return myData
        }


    protected fun addItem(data: MutableList<Map<String, Any>>, name: String, intent: Intent) {
        val temp = HashMap<String, Any>()
        temp.put("title", name)
        temp.put("intent", intent)
        data.add(temp)
    }

    protected fun activityIntent(pkg: String, componentName: String): Intent {
        val result = Intent()
        result.setClassName(pkg, componentName)
        return result
    }


    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        val map = l.getItemAtPosition(position) as Map<String, Any>

        val intent = map["intent"] as Intent
        startActivity(intent)
    }

    companion object {

        private val sDisplayNameComparator = object : Comparator<Map<String, Any>> {
            private val collator = Collator.getInstance()

            override fun compare(map1: Map<String, Any>, map2: Map<String, Any>): Int {
                return collator.compare(map1["title"], map2["title"])
            }
        }
    }

}

