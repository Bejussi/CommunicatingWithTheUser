package com.bejussi.communicatingwiththeuser.Dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.bejussi.communicatingwiththeuser.R

class ChoiceDialog: DialogFragment() {

    private val colors = arrayOf("Yellow","Green","Blue","Red")

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(R.string.choice_dialog_title)
            builder.setItems(colors) {
                dialog, which ->
                Toast.makeText(it, colors[which], Toast.LENGTH_SHORT).show()
            }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}