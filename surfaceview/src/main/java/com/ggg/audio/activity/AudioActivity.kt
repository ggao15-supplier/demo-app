package com.ggg.audio.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import com.ggg.audio.AudioPlayer
import com.ggg.audio.AudioRecorder
import com.ggg.surfaceview.databinding.ActivityAudioBinding
import java.io.File

class AudioActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAudioBinding
    private val audioRecorder = AudioRecorder()
    private val audioPlayer = AudioPlayer()
    private var isRecording = false
    private var isPlaying = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAudioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val path =
            "${getExternalFilesDir(Environment.DIRECTORY_MUSIC)?.absolutePath}${File.separator}test.pcm"
        binding.tvShow.text = path
        binding.btnRecord.setOnClickListener {
            if (isRecording) {
                isRecording = audioRecorder.stop()
                binding.btnRecord.text = "record"
            } else {
                isRecording = audioRecorder.record(path)
                binding.btnRecord.text = "stop"
            }
        }
        binding.btnPlay.setOnClickListener {
            if (isPlaying) {
                isPlaying = audioPlayer.stop()
                binding.btnRecord.text = "play"
            } else {
                isPlaying = audioPlayer.play(path)
                binding.btnRecord.text = "stop"
            }
        }
    }
}