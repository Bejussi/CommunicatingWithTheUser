package com.bejussi.communicatingwiththeuser.Dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.bejussi.communicatingwiththeuser.R

class CustomDialog: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val inflater = requireActivity().layoutInflater
        val v = inflater.inflate(R.layout.custom_dialog, null)

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setView(v)
            builder.setTitle(R.string.custom_dialog_title)
            builder.setPositiveButton(R.string.custom_dialog_action) {
                dialog, which ->
            }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}