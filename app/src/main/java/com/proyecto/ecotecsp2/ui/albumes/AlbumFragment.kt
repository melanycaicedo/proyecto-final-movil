package com.proyecto.ecotecsp2.ui.albumes

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.proyecto.ecotecsp2.R
import com.proyecto.ecotecsp2.databinding.FragmentAlbumBinding
import com.proyecto.ecotecsp2.databinding.FragmentGalleryBinding
import com.proyecto.ecotecsp2.ui.gallery.GalleryViewModel

class AlbumFragment : Fragment() {

    private var _binding: FragmentAlbumBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    companion object {
        fun newInstance() = AlbumFragment()
    }

    private val viewModel: AlbumViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(AlbumViewModel::class.java)

        _binding = FragmentAlbumBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textAlbum
        galleryViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}