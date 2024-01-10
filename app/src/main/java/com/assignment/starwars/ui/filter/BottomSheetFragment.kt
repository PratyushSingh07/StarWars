package com.assignment.starwars.ui.filter

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.assignment.starwars.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetFragment : BottomSheetDialogFragment() {

    private var mBottomSheetBehavior: BottomSheetBehavior<*>? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        val view = View.inflate(context, R.layout.dialog_options, null)
        dialog.setContentView(view)
        mBottomSheetBehavior = BottomSheetBehavior.from(view.parent as View)
        val sort = view.findViewById<TextView>(R.id.sort)
        sort.setOnClickListener {
            Toast.makeText(activity?.applicationContext, "SORT", Toast.LENGTH_SHORT).show()
            onSortSelected()
        }

        val filter = view.findViewById<TextView>(R.id.filter)
        filter.setOnClickListener {
            Toast.makeText(activity?.applicationContext, "FILTER", Toast.LENGTH_SHORT).show()
            onFilterSelected()
        }
        return dialog
    }

    private fun onSortSelected() {
        val sortBottomSheet = SortBottomSheet()
        sortBottomSheet.show((activity as FragmentActivity).supportFragmentManager, "")
    }

    private fun onFilterSelected() {
        val filterBottomSheet = FilterBottomSheet()
        filterBottomSheet.show((activity as FragmentActivity).supportFragmentManager, "")
    }

}