package com.proyecto.ecotecsp2

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView

class SingerAdapterPremiun(
    private val context: Context,
    private val singerList: List<SingerPremium>
) : RecyclerView.Adapter<SingerAdapterPremiun.ViewHolder>() {

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_singerpremium, parent, false)
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
            holder.videoView.visibility = View.GONE
            holder.videoView.stopPlayback()
        }

        holder.videoButton.setOnClickListener {
            val videoUri: Uri = Uri.parse("android.resource://${context.packageName}/${singer.videoResource}")
            holder.videoView.setVideoURI(videoUri)
            holder.videoView.visibility = View.VISIBLE
            holder.videoView.start()
        }
    }

    override fun getItemCount(): Int {
        return singerList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val singerName: TextView = itemView.findViewById(R.id.singer_name2)
        val singerImage: ImageView = itemView.findViewById(R.id.singer_image2)
        val playButton: Button = itemView.findViewById(R.id.play_button2)
        val pauseButton: Button = itemView.findViewById(R.id.pause_button2)
        val stopButton: Button = itemView.findViewById(R.id.stop_button2)
        val videoButton: Button = itemView.findViewById(R.id.video_button2)
        val videoView: VideoView = itemView.findViewById(R.id.video_view)
    }
}