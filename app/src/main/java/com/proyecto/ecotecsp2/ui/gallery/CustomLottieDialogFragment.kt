package com.proyecto.ecotecsp2.ui.gallery


import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.proyecto.ecotecsp2.R

class CustomLottieDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_premium)
      //  dialog.setCancelable(false) // Optional: Make the dialog non-cancelable

        // The LottieAnimationView is configured in the layout file
        return dialog
    }
}