package usercomms.joemarini.example.com.usercommunications

import android.app.NotificationManager
import android.content.Context
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import com.bejussi.communicatingwiththeuser.R

class NotificationResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_result)

        // When launched from an addAction Intent, we must manually cancel
        // the notification otherwise it will stay in the status bar
        val i = intent
        val notifyID = i.getIntExtra("notifyID", 0)

        val mgr = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mgr.cancel(notifyID)
    }
}
