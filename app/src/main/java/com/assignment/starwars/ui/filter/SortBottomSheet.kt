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

class SortBottomSheet : BottomSheetDialogFragment() {

    private var mBottomSheetBehavior: BottomSheetBehavior<*>? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        val view = View.inflate(context, R.layout.dialog_sort, null)
        dialog.setContentView(view)
        mBottomSheetBehavior = BottomSheetBehavior.from(view.parent as View)

        view.findViewById<TextView>(R.id.name).setOnClickListener {
            Toast.makeText(requireContext(), "NAME", Toast.LENGTH_SHORT).show()
        }

        view.findViewById<TextView>(R.id.gender).setOnClickListener {
            Toast.makeText(requireContext(), "GENDER", Toast.LENGTH_SHORT).show()
        }
        return dialog
    }
}