package com.bejussi.communicatingwiththeuser

import android.app.DatePickerDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import com.bejussi.communicatingwiththeuser.Dialogs.ChoiceDialog
import com.bejussi.communicatingwiththeuser.Dialogs.CustomDialog
import com.bejussi.communicatingwiththeuser.Dialogs.SimpleDialog
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_toast_layout.*
import kotlinx.android.synthetic.main.custom_toast_layout.view.*
import java.util.*
import androidx.core.app.NotificationCompat
import usercomms.joemarini.example.com.usercommunications.NotificationResultActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        standardToastButton.setOnClickListener(this)
        customToastButton.setOnClickListener(this)
        regularSnackBarButton.setOnClickListener(this)
        actionSnackBarButton.setOnClickListener(this)
        simpleDialogButton.setOnClickListener(this)
        dateDialogButton.setOnClickListener(this)
        choiseDialogButton.setOnClickListener(this)
        customDialogButton.setOnClickListener(this)
        simpleNotificationButton.setOnClickListener(this)

        // For API 26 and later, we have to create a channel otherwise the notification
        // won't be displayed. This can be called multiple times without harm - if there's
        // already a channel with the given ID then the call is ignored
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(NOTIFY_CHANNEL, "Notifications", importance)
            channel.description = "This is a notification channel"

            // Register the channel with the system
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager?.createNotificationChannel(channel)
        }
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.standardToastButton -> showStandartToast()
            R.id.customToastButton -> showCustomToast()
            R.id.regularSnackBarButton -> showRegularSnackBar()
            R.id.actionSnackBarButton -> showActionSnackBar()
            R.id.simpleDialogButton -> showSimpleDialog()
            R.id.dateDialogButton -> showDateDialog()
            R.id.choiseDialogButton -> showChoiseDialog()
            R.id.customDialogButton -> showCustomDialog()
            R.id.simpleNotificationButton -> showNotification()
        }
    }

    private fun showNotification() {
        // TODO: create the NotificationCompat Builder
        val builder = NotificationCompat.Builder(this, NOTIFY_CHANNEL)

        // TODO: Create the intent that will start the ResultActivity when the user
        // taps the notification or chooses an action button
        val intent = Intent(this, NotificationResultActivity::class.java)

        // TODO: Store the notification ID so we can cancel it later in the ResultActivity
        intent.putExtra("notifyID", NOTIFY_ID)

        // TODO: create a PendingIntent to fire for the notification
        val pendingIntent = PendingIntent.getActivity(this, NOTIFY_ID, intent,
            PendingIntent.FLAG_IMMUTABLE)

        // TODO: Set the three required items all notifications must have
        builder.setSmallIcon(R.drawable.ic_stat_sample_notification)
        builder.setContentTitle("Sample Notification")
        builder.setContentText("This is a sample notification")

        // TODO: Set the notification to cancel when the user taps on it
        builder.setAutoCancel(true)

        // TODO: Set the large icon to be our app's launcher icon
        builder.setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher))

        // TODO: Set the small subtext message
        builder.setSubText("Tap to view")

        // TODO: Set the content intent to launch our result activity
        builder.setContentIntent(pendingIntent)

        // TODO: Add an expanded layout to the notification


        // TODO: Add action buttons to the Notification if they are supported
        // Use the same PendingIntent as we use for the main notification action


        // TODO: Set the lock screen visibility of the notification


        // TODO: Build the finished notification and then display it to the user
        val notification = builder.build()
        val mgr = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mgr.notify(NOTIFY_ID, notification)

    }

    private fun showSimpleDialog() {
        val simpleDialog = SimpleDialog()
        simpleDialog.show(supportFragmentManager,"SimpleDialogFragment")
    }

    private fun showDateDialog() {
        val cal = Calendar.getInstance()
        val datePicker = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { view, year, monthofYear, dayOfMonth ->
                Toast.makeText(applicationContext,"Day: $dayOfMonth, month: $monthofYear, year: $year",Toast.LENGTH_SHORT).show()
        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH))
        datePicker.setTitle(R.string.date_dialog_title)
        datePicker.show()
    }

    private fun showChoiseDialog() {
        val choiceDialog = ChoiceDialog()
        choiceDialog.show(supportFragmentManager,"ChoiceDialogFragment")
    }

    private fun showCustomDialog() {
        val customDialog = CustomDialog()
        customDialog.show(supportFragmentManager,"CustomDialogFragment")
    }

    private fun showActionSnackBar() {
        Snackbar.make(myLayout,R.string.action_snack_bar_message,Snackbar.LENGTH_LONG)
            .setAction(R.string.actionText) {
                Toast.makeText(applicationContext,R.string.standard_toast_message,Toast.LENGTH_SHORT).show()
            }
            .setActionTextColor(Color.GREEN)
            .show()
    }

    private fun showRegularSnackBar() {
        Snackbar.make(myLayout,R.string.regular_snack_bar_message,Snackbar.LENGTH_SHORT).show()
    }

    private fun showCustomToast() {
        val layout = layoutInflater.inflate(R.layout.custom_toast_layout,customToastLayout)
        Toast(applicationContext).run {
            duration = Toast.LENGTH_LONG
            setGravity(Gravity.BOTTOM,0,100)
            view = layout
            show()
        }
    }

    private fun showStandartToast() {
        Toast.makeText(this,R.string.standard_toast_message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private val NOTIFY_ID = 1001
        private val NOTIFY_CHANNEL = "MY_CHANNEL"
    }
}