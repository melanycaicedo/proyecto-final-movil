package com.proyecto.ecotecsp2.ui.slideshow
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.proyecto.ecotecsp2.MainActivity
import com.proyecto.ecotecsp2.R
import com.proyecto.ecotecsp2.Singer
import com.proyecto.ecotecsp2.SingerAdapter
import com.proyecto.ecotecsp2.SingerAdapterPremiun
import com.proyecto.ecotecsp2.SingerPremium
import com.proyecto.ecotecsp2.databinding.FragmentSlideshowBinding
import com.proyecto.ecotecsp2.ui.gallery.GalleryViewModel

class SlideshowFragment : Fragment() {
    private lateinit var mediaPlayer: MediaPlayer
    private val audioUrl = "https://www.youtube.com/watch?v=QCZZwZQ4qNs"
    private lateinit var recyclerView: RecyclerView
    private lateinit var singerAdapter: SingerAdapter
    private lateinit var singerList: ArrayList<Singer>

    private lateinit var singerAdapterPremium: SingerAdapterPremiun
    private lateinit var singerListPremium: ArrayList<SingerPremium>
    // private var isPremiumMode = false
    private var _binding: FragmentSlideshowBinding? = null
    private val binding get() = _binding!!
    val karolImages = arrayOf(R.drawable.karol1, R.drawable.karol2, R.drawable.karol3, R.drawable.karol4, R.drawable.karol5)
    val badBunnyImages = arrayOf(R.drawable.bad1, R.drawable.bad2, R.drawable.bad3, R.drawable.bad4, R.drawable.bad5)
    val karolSongs = arrayOf("200 Copas", "Mi Ex Tenía Razón", "Provenza", "Makinon", "Si Antes Te Hubiera Conocido")
    val badBunnySongs = arrayOf("Estamos Bien", "Haciendo Que Me Amas", "Yoniguni", "Amorfoda", "Moscow Mule")
    val karolMusic = arrayOf(R.raw.karol200copas, R.raw.karolmiexteniarazon, R.raw.karolprovenza, R.raw.karolgmakinon, R.raw.karolsiantestehubieraconocido)
    val badBunnyMusic = arrayOf(R.raw.badbunnyestamosbien, R.raw.badbunnyhaciendquemeama, R.raw.badbunnyyoniguni, R.raw.badbunnyamorfoda, R.raw.badbunnymoscowmule)

    val karolvideoMusic = arrayOf(R.raw.karonvideo1, R.raw.karonvideo1, R.raw.karonvideo1, R.raw.karonvideo1, R.raw.karonvideo1)
    val badBunnyvideoMusic = arrayOf(R.raw.karonvideo1, R.raw.karonvideo1, R.raw.karonvideo1, R.raw.karonvideo1, R.raw.karonvideo1)

    private lateinit var premiumModeViewModel: GalleryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        premiumModeViewModel = ViewModelProvider(requireActivity()).get(GalleryViewModel::class.java)

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerViewAlbum
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Inicializar la lista de cantantes
        singerList = ArrayList()
        singerListPremium = ArrayList()

        // Inicialmente mostrar 5 canciones estándar
        showStandardSongs()


        premiumModeViewModel.isPremiumMode.observe(viewLifecycleOwner, Observer { isPremiumMode ->

            if (isPremiumMode) {
                showPremiumSongs()
            } else {
                showStandardSongs()
            }
        })

//        // Inicializar el adaptador
        singerAdapter = SingerAdapter(requireContext(), singerList)
        recyclerView.adapter = singerAdapter

    }

    private fun showPremiumSongs() {
        singerListPremium.clear()
        for (i in 0 until 10) {
            if (i == 0) {
                singerListPremium.add(SingerPremium("karol G:  " + karolSongs[i], karolImages[i], karolMusic[i] , karolvideoMusic[i]))
            }
            if (i < 5) {
                if(i>0) {
                    singerListPremium.add(SingerPremium(" " + karolSongs[i], karolImages[i], karolMusic[i], karolvideoMusic[i]))
                }

            } else {
                if (i == 5) {
                    singerListPremium.add(SingerPremium("Bad Bunny:  " + badBunnySongs[i - 5], badBunnyImages[i - 5], badBunnyMusic[i - 5] , badBunnyvideoMusic[i-5]))
                }else{
                    singerListPremium.add(SingerPremium("" + badBunnySongs[i - 5], badBunnyImages[i - 5], badBunnyMusic[i - 5],badBunnyvideoMusic[i-5]))
                }

            }
        }
        singerAdapterPremium = SingerAdapterPremiun(requireContext(), singerListPremium)
        recyclerView.adapter = singerAdapterPremium
    }

    private fun showStandardSongs() {
        singerList.clear()
        for (i in 0 until 5) {
            if (i == 0) {
                singerList.add(Singer("karol G:  " + karolSongs[i], karolImages[i], karolMusic[i]))
            }
            if (i < 2) {
                if(i>0) {
                    singerList.add(Singer("" + karolSongs[i], karolImages[i], karolMusic[i]))
                }

            } else {
                if (i == 2) {
                singerList.add(Singer("Bad Bunny:  " + badBunnySongs[i - 2], badBunnyImages[i - 2], badBunnyMusic[i - 2]))
           }else{
                    singerList.add(Singer("" + badBunnySongs[i - 2], badBunnyImages[i - 2], badBunnyMusic[i - 2]))
                }

            }
        }
        singerAdapter = SingerAdapter(requireContext(), singerList)
        recyclerView.adapter = singerAdapter
    }

    private fun addSingerNameTextView(singerName: String) {
        val textView = TextView(requireContext()).apply {
            text = singerName
            textSize = 54f
            setTextColor(resources.getColor(R.color.white))
            setPadding(16, 16, 16, 16)
        }
        binding.root.addView(textView)
    }

//    private fun showPremiumSongs() {
//        singerListPremium.clear()
//        for (i in 0 until 10) {
//            if (i < 5) {
//                singerListPremium.add(SingerPremium("" + karolSongs[i], -1, karolMusic[i], karolvideoMusic[i]))
//            } else {
//                singerListPremium.add(SingerPremium("" + badBunnySongs[i - 5],-1, badBunnyMusic[i - 5], badBunnyvideoMusic[i - 5]))
//            }
//            val textView = TextView(requireContext()).apply {
//                text = if (i < 5) "Karol G ${karolSongs[i]}" else "Bad Bunny ${badBunnySongs[i - 5]}"
//                textSize = 16f
//                setTextColor(resources.getColor(R.color.black))
//                setPadding(16, 16, 16, 16)
//            }
//            binding.root.addView(textView)
//
//        }
//        singerAdapterPremium = SingerAdapterPremiun(requireContext(), singerListPremium)
//        recyclerView.adapter = singerAdapterPremium
//    }

//    private fun showStandardSongs() {
//        singerList.clear()
//        for (i in 0 until 5) {
//            if (i < 2) {
//                singerList.add(Singer("" + karolSongs[i], 0, karolMusic[i]))
//            } else {
//                singerList.add(Singer("" + badBunnySongs[i], 0, badBunnyMusic[i]))
//            }
//
//
//        }
//        singerAdapter = SingerAdapter(requireContext(), singerList)
//        recyclerView.adapter = singerAdapter
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
