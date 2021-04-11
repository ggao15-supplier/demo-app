package com.ggg.audio

import android.media.*
import java.io.File
import java.io.FileInputStream
import java.io.PipedInputStream
import java.io.PipedOutputStream
import java.util.concurrent.Executors

class AudioPlayer {
    companion object {
        private const val sampleRateInHz = 11025
        private const val bufferSize = 1024 * 10
    }

    private var audioTrack: AudioTrack? = null
    private val buffer: ByteArray = ByteArray(bufferSize)
    private var isPlaying = false
    private val executor = Executors.newSingleThreadScheduledExecutor()
    private var inputStream: FileInputStream? = null

    init {
        val bufferSize = AudioRecord.getMinBufferSize(
            sampleRateInHz,
            AudioFormat.CHANNEL_IN_STEREO,
            AudioFormat.ENCODING_PCM_16BIT
        )
        audioTrack = AudioTrack(
            AudioManager.STREAM_MUSIC,
            sampleRateInHz,
            AudioFormat.CHANNEL_IN_STEREO,
            AudioFormat.ENCODING_PCM_16BIT,
            bufferSize,
            AudioTrack.MODE_STREAM
        )

    }

    fun play(filePath: String): Boolean {
        audioTrack?.apply {
            isPlaying = true
            inputStream = FileInputStream(File(filePath))
            play()
            executor.execute {
                while (isPlaying && inputStream!!.available() > 0) {
                    val size = inputStream!!.read(buffer)
                    write(buffer, 0, size)
                }
            }

        }
        return isPlaying
    }

    fun stop(): Boolean {
        isPlaying = false
        audioTrack?.stop()
        inputStream?.close()
        executor.shutdown()
        return isPlaying
    }
}