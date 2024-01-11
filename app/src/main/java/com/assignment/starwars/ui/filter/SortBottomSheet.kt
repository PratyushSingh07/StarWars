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
    private var sortOptionListener: SortOptionListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        val view = View.inflate(context, R.layout.dialog_sort, null)
        dialog.setContentView(view)
        mBottomSheetBehavior = BottomSheetBehavior.from(view.parent as View)

        view.findViewById<TextView>(R.id.name).setOnClickListener {
            sortOptionListener?.onSortOptionSelected(SortOption.NAME)
            dismiss()
        }

        view.findViewById<TextView>(R.id.updated).setOnClickListener {
//            sortOptionListener?.onSortOptionSelected(SortOption.MALE)
            dismiss()
        }

        view.findViewById<TextView>(R.id.created).setOnClickListener {
//            sortOptionListener?.onSortOptionSelected(SortOption.FEMALE)
            dismiss()
        }

        view.findViewById<TextView>(R.id.height).setOnClickListener {
//            sortOptionListener?.onSortOptionSelected(SortOption.FEMALE)
            dismiss()
        }

        view.findViewById<TextView>(R.id.mass).setOnClickListener {
//            sortOptionListener?.onSortOptionSelected(SortOption.FEMALE)
            dismiss()
        }

        return dialog
    }
}