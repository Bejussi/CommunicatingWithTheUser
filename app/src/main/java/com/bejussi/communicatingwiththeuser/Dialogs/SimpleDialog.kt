package com.bejussi.communicatingwiththeuser.Dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.bejussi.communicatingwiththeuser.R

class SimpleDialog: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(R.string.simple_dialog_title)
            builder.setMessage(R.string.simple_dialog_message)
            builder.setPositiveButton(R.string.simple_dialog_action_yes) {
                dialog, which ->
                Toast.makeText(it, R.string.simple_dialog_action_yes_toast_text, Toast.LENGTH_SHORT).show()
            }
            builder.setNegativeButton(R.string.simple_dialog_action_no) {
                    dialog, which ->
                Toast.makeText(it, R.string.simple_dialog_action_no_toast_text, Toast.LENGTH_SHORT).show()
            }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
    }
}