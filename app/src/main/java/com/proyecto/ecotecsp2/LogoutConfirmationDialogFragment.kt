package com.proyecto.ecotecsp2

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.proyecto.ecotecsp2.R

class LogoutConfirmationDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Cerrar sesión")
                .setMessage("¿Estás seguro que deseas cerrar sesión?")
                .setPositiveButton("Aceptar",
                    DialogInterface.OnClickListener { dialog, id ->
                        // Acciones al hacer clic en Aceptar (iniciar actividad de login)
                        val intent = Intent(requireContext(), Login::class.java)
                        startActivity(intent)
                        requireActivity().finish()
                    })
                .setNegativeButton("Cancelar",
                    DialogInterface.OnClickListener { dialog, id ->
                        // Cancelar y cerrar el diálogo
                        dialog.dismiss()
                    })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}