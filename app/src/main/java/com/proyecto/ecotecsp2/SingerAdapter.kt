
package com.proyecto.ecotecsp2

import android.content.Context
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SingerAdapter(
    private val context: Context,
    private val singerList: List<Singer>
) : RecyclerView.Adapter<SingerAdapter.ViewHolder>() {

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_singer, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val singer = singerList[position]
        holder.singerName.text = singer.name
        holder.singerImage.setImageResource(singer.imageResource)

        holder.playButton.setOnClickListener {
            mediaPlayer?.release()
            mediaPlayer = MediaPlayer.create(context, singer.musicResource)
            mediaPlayer?.start()
        }

        holder.pauseButton.setOnClickListener {
            mediaPlayer?.pause()
        }

        holder.stopButton.setOnClickListener {
            mediaPlayer?.stop()
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }

    override fun getItemCount(): Int {
        return singerList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val singerName: TextView = itemView.findViewById(R.id.singer_name)
        val singerImage: ImageView = itemView.findViewById(R.id.singer_image)
        val playButton: Button = itemView.findViewById(R.id.play_button)
        val pauseButton: Button = itemView.findViewById(R.id.pause_button)
        val stopButton: Button = itemView.findViewById(R.id.stop_button)
    }
}
