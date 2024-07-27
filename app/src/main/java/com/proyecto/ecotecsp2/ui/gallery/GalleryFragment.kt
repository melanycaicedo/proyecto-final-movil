//// GalleryFragment.kt
//package com.proyecto.ecotecsp2.ui.gallery
//
//import android.os.Bundle
//import android.text.Editable
//import android.text.TextWatcher
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Button
//import android.widget.EditText
//import android.widget.ImageView
//import androidx.fragment.app.Fragment
//import androidx.lifecycle.ViewModelProvider
//import androidx.navigation.fragment.findNavController
//import com.proyecto.ecotecsp2.R
//import com.proyecto.ecotecsp2.databinding.FragmentGalleryBinding
//
//class GalleryFragment : Fragment() {
//
//    private var _binding: FragmentGalleryBinding? = null
//    private val binding get() = _binding!!
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        val galleryViewModel = ViewModelProvider(this).get(GalleryViewModel::class.java)
//
//        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
//        val root: View = binding.root
//
//        val cardImage: ImageView = binding.cardImage
//        val cardNumber: EditText = binding.cardNumber
//        val expiryDate: EditText = binding.expiryDate
//
//        cardNumber.addTextChangedListener(object : TextWatcher {
//            private var isUpdating = false
//            private val space = "   "
//
//            override fun afterTextChanged(s: Editable?) {
//                if (isUpdating) {
//                    isUpdating = false
//                    return
//                }
//
//                isUpdating = true
//
//                // Elimina los espacios existentes
//                val raw = s.toString().replace("   ", "")
//
//                // Limita el número de caracteres a 16
//                if (raw.length > 16) {
//                    isUpdating = false
//                    cardNumber.setText(raw.substring(0, 16))
//                    cardNumber.setSelection(25)
//                    return
//                }
//
//                // Aplica el formato con espacios
//                val formatted = StringBuilder()
//                for (i in raw.indices) {
//                    if (i > 0 && i % 4 == 0) {
//                        formatted.append(space)
//                    }
//                    formatted.append(raw[i])
//                }
//
//                // Actualiza el texto con el formato aplicado
//                cardNumber.setText(formatted.toString())
//                cardNumber.setSelection(formatted.length)
//
//                // Cambiar la imagen del ImageView basado en el primer dígito
//                if (raw.isNotEmpty()) {
//                    when (raw[0]) {
//                        '4' -> cardImage.setImageResource(R.drawable.visacards)
//                        '5' -> cardImage.setImageResource(R.drawable.mastercard)
//                        else -> cardImage.setImageResource(R.drawable.blackscard)
//                    }
//                } else {
//                    cardImage.setImageResource(R.drawable.blackscard)
//                }
//            }
//
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//                // No es necesario implementar
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                // No es necesario implementar
//            }
//        })
//        expiryDate.addTextChangedListener(object : TextWatcher {
//            private var isUpdating = false
//
//            override fun afterTextChanged(s: Editable?) {
//                if (isUpdating) {
//                    isUpdating = false
//                    return
//                }
//
//                isUpdating = true
//
//                // Elimina los slash existentes
//                val raw = s.toString().replace("/", "")
//
//                // Limita el número de caracteres a 4
//                if (raw.length > 4) {
//                    isUpdating = false
//                    expiryDate.setText(raw.substring(0, 4))
//                    expiryDate.setSelection(5)
//                    return
//                }
//
//                // Aplica el formato con slash
//                val formatted = StringBuilder()
//                for (i in raw.indices) {
//                    if (i == 2) {
//                        formatted.append('/')
//                    }
//                    formatted.append(raw[i])
//                }
//
//                // Actualiza el texto con el formato aplicado
//                expiryDate.setText(formatted.toString())
//                expiryDate.setSelection(formatted.length)
//            }
//
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//                // No es necesario implementar
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                // No es necesario implementar
//            }
//        })
//
//        val subscribeButton: Button = binding.subscribeButton
//        subscribeButton.setOnClickListener {
//            // Navegar de regreso al menú principal
//            findNavController().navigate(R.id.action_nav_gallery_to_nav_home)
//        }
//
//        return root
//    }
//
//
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//}

package com.proyecto.ecotecsp2.ui.gallery

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.navigation.NavigationView
import com.proyecto.ecotecsp2.R
import com.proyecto.ecotecsp2.databinding.FragmentGalleryBinding
import com.proyecto.ecotecsp2.ui.home.HomeFragment

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!
    private lateinit var premiumModeViewModel: GalleryViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        premiumModeViewModel = ViewModelProvider(requireActivity()).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val cardImage: ImageView = binding.cardImage
        val cardNumber: EditText = binding.cardNumber
        val expiryDate: EditText = binding.expiryDate


        cardNumber.addTextChangedListener(object : TextWatcher {
            private var isUpdating = false
            private val space = "   "

            override fun afterTextChanged(s: Editable?) {
                if (isUpdating) {
                    isUpdating = false
                    return
                }

                isUpdating = true

                // Elimina los espacios existentes
                val raw = s.toString().replace("   ", "")

                // Limita el número de caracteres a 16
                if (raw.length > 16) {
                    isUpdating = false
                    cardNumber.setText(raw.substring(0, 16))
                    cardNumber.setSelection(16)
                    return
                }

                // Aplica el formato con espacios
                val formatted = StringBuilder()
                for (i in raw.indices) {
                    if (i > 0 && i % 4 == 0) {
                        formatted.append(space)
                    }
                    formatted.append(raw[i])
                }

                // Actualiza el texto con el formato aplicado
                cardNumber.setText(formatted.toString())
                cardNumber.setSelection(formatted.length)

                // Cambiar la imagen del ImageView basado en el primer dígito
                if (raw.isNotEmpty()) {
                    when (raw[0]) {
                        '4' -> cardImage.setImageResource(R.drawable.visacards)
                        '5' -> cardImage.setImageResource(R.drawable.mastercard)
                        else -> cardImage.setImageResource(R.drawable.blackscard)
                    }
                } else {
                    cardImage.setImageResource(R.drawable.blackscard)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No es necesario implementar
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // No es necesario implementar
            }
        })

        expiryDate.addTextChangedListener(object : TextWatcher {
            private var isUpdating = false

            override fun afterTextChanged(s: Editable?) {
                if (isUpdating) {
                    isUpdating = false
                    return
                }

                isUpdating = true

                // Elimina los slash existentes
                val raw = s.toString().replace("/", "")

                // Limita el número de caracteres a 4
                if (raw.length > 4) {
                    isUpdating = false
                    expiryDate.setText(raw.substring(0, 4))
                    expiryDate.setSelection(5)
                    return
                }

                // Aplica el formato con slash
                val formatted = StringBuilder()
                for (i in raw.indices) {
                    if (i == 2) {
                        formatted.append('/')
                    }
                    formatted.append(raw[i])
                }

                // Actualiza el texto con el formato aplicado
                expiryDate.setText(formatted.toString())
                expiryDate.setSelection(formatted.length)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No es necesario implementar
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // No es necesario implementar
            }
        })




//        val subscribeButton: Button = binding.subscribeButton
//        subscribeButton.setOnClickListener {
//
//            showDialog()
//
//            premiumModeViewModel.activatePremiumMode()
//            Log.i("GALLERY", "isPremiumMode GALLERY: ${premiumModeViewModel.isPremiumMode.value}")
//            // Obtener el DrawerLayout
//            val drawerLayout = activity?.findViewById<DrawerLayout>(R.id.drawer_layout)
//            // Obtener el NavigationView
//            val navView = drawerLayout?.findViewById<NavigationView>(R.id.nav_view)
//            // Ocultar el ítem del menú nav_gallery
//            navView?.menu?.findItem(R.id.nav_gallery)?.isVisible = false
//
//            // Navegar de regreso al menú principal
//            findNavController().navigate(R.id.action_nav_gallery_to_nav_home)
//        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val subscribeButton: Button = binding.subscribeButton
        subscribeButton.setOnClickListener {
            showDialog()
            premiumModeViewModel.activatePremiumMode()
            Log.i("GALLERY", "isPremiumMode GALLERY: ${premiumModeViewModel.isPremiumMode.value}")

            // Obtener el DrawerLayout
            val drawerLayout = activity?.findViewById<DrawerLayout>(R.id.drawer_layout)
            // Obtener el NavigationView
            val navView = drawerLayout?.findViewById<NavigationView>(R.id.nav_view)
            // Ocultar el ítem del menú nav_gallery
            navView?.menu?.findItem(R.id.nav_gallery)?.isVisible = false

            // Navegar de regreso al menú principal
            findNavController().navigate(R.id.action_nav_gallery_to_nav_home)
        }
    }


    private fun showDialog() {
        val dialog = CustomLottieDialogFragment()
        dialog.show(parentFragmentManager, "CustomLottieDialogFragment")
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
