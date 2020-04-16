package com.ggg.myapplication.views.home

import android.net.Uri
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.e.myapplication.R
import kotlinx.android.synthetic.main.item_music.view.*

class MusicAdapter : RecyclerView.Adapter<MusicAdapter.MusicHolder>() {
    private val musics = mutableListOf<MusicItem>()

    fun setData(musicItems: List<MusicItem>) {
        musics.addAll(musicItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicHolder =
        MusicHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_music, parent, false))

    override fun getItemCount(): Int = musics.size

    override fun onBindViewHolder(holder: MusicHolder, position: Int) {
        holder.bind(musics[position])
    }

    class MusicHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: MusicItem) {
            view.tvSinger.text = item.singer
            view.tvSongName.text = item.songName
            if (!TextUtils.isEmpty(item.alumPhoto)) {
                view.ivPhoto.setImageURI(Uri.parse(item.alumPhoto))
            }
        }
    }
}