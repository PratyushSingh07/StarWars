package com.assignment.starwars.ui.filter

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.assignment.starwars.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetFragment : BottomSheetDialogFragment() {

    private var mBottomSheetBehavior: BottomSheetBehavior<*>? = null
    private var listener: SortOptionListener? = null

    fun setBottomSheetListener(listener: SortOptionListener) {
        this.listener = listener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        val view = View.inflate(context, R.layout.dialog_options, null)
        dialog.setContentView(view)
        mBottomSheetBehavior = BottomSheetBehavior.from(view.parent as View)


        view.findViewById<TextView>(R.id.name).setOnClickListener {
            dismiss()
            listener?.onSortOptionSelected(Options.NAME)
        }

        view.findViewById<TextView>(R.id.height).setOnClickListener {
            dismiss()
            Toast.makeText(activity?.applicationContext, "NAME", Toast.LENGTH_SHORT).show()
            listener?.onSortOptionSelected(Options.HEIGHT)
        }
        view.findViewById<TextView>(R.id.mass).setOnClickListener {
            dismiss()
            listener?.onSortOptionSelected(Options.MASS)
        }
        view.findViewById<TextView>(R.id.created).setOnClickListener {
            dismiss()
            Toast.makeText(activity?.applicationContext, "NAME", Toast.LENGTH_SHORT).show()
            listener?.onSortOptionSelected(Options.CREATED)
        }
        view.findViewById<TextView>(R.id.updated).setOnClickListener {
            dismiss()
            Toast.makeText(activity?.applicationContext, "NAME", Toast.LENGTH_SHORT).show()
            listener?.onSortOptionSelected(Options.UPDATED)
        }

        view.findViewById<TextView>(R.id.male).setOnClickListener {
            dismiss()
            Toast.makeText(activity?.applicationContext, "NAME", Toast.LENGTH_SHORT).show()
            listener?.onSortOptionSelected(Options.MALE)
        }

        view.findViewById<TextView>(R.id.female).setOnClickListener {
            dismiss()
            listener?.onSortOptionSelected(Options.FEMALE)
        }
        return dialog
    }

}