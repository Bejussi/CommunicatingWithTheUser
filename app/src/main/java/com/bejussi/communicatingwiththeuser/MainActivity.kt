package com.bejussi.communicatingwiththeuser

import android.app.DatePickerDialog
import android.graphics.Color
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
        }
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
}