package com.ggg.myapplication.views.home

import android.net.Uri
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.e.myapplication.R
import com.e.myapplication.databinding.ItemMusicBinding

class MusicAdapter : RecyclerView.Adapter<MusicAdapter.MusicHolder>() {
    private val musics = mutableListOf<MusicItem>()

    fun setData(musicItems: List<MusicItem>) {
        musics.addAll(musicItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicHolder {
        val binding = ItemMusicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MusicHolder(binding)
    }

    override fun getItemCount(): Int = musics.size

    override fun onBindViewHolder(holder: MusicHolder, position: Int) {
        holder.bind(musics[position])
    }

    class MusicHolder(private val itemMusicBinding: ItemMusicBinding) :
        RecyclerView.ViewHolder(itemMusicBinding.root) {
        fun bind(item: MusicItem) {

            itemMusicBinding.tvSinger.text = item.singer
            itemMusicBinding.tvSongName.text = item.songName
            if (!TextUtils.isEmpty(item.alumPhoto)) {
                itemMusicBinding.ivPhoto.setImageURI(Uri.parse(item.alumPhoto))
            }

            itemMusicBinding.root.setOnClickListener {
                item.module?.openSelfModule(it.context)
            }
        }
    }
}