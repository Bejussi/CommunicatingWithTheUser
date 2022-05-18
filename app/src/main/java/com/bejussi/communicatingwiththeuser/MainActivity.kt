package com.bejussi.communicatingwiththeuser

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_toast_layout.*
import kotlinx.android.synthetic.main.custom_toast_layout.view.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        standardToastButton.setOnClickListener(this)
        customToastButton.setOnClickListener(this)
        regularSnackBarButton.setOnClickListener(this)
        actionSnackBarButton.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.standardToastButton -> showStandartToast()
            R.id.customToastButton -> showCustomToast()
            R.id.regularSnackBarButton -> showRegularSnackBar()
            R.id.actionSnackBarButton -> showActionSnackBar()
        }
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