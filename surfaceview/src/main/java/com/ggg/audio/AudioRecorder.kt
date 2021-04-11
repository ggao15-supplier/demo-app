package com.ggg.audio

import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import java.io.File
import java.io.FileOutputStream
import java.util.concurrent.Executors

class AudioRecorder() {
    companion object {
        private const val sampleRateInHz = 11025
        private const val bufferSize = 1024 * 10
    }

    private var audioRecord: AudioRecord? = null
    private val buffer: ByteArray = ByteArray(bufferSize)
    private var isRecording = false
    private var outStream: FileOutputStream? = null
    private val executor = Executors.newSingleThreadScheduledExecutor()

    init {
        val bufferSize = AudioRecord.getMinBufferSize(
            sampleRateInHz,
            AudioFormat.CHANNEL_IN_STEREO,
            AudioFormat.ENCODING_PCM_16BIT
        )
        audioRecord = AudioRecord(
            MediaRecorder.AudioSource.MIC, sampleRateInHz, AudioFormat.CHANNEL_IN_STEREO,
            AudioFormat.ENCODING_PCM_16BIT, bufferSize
        )

    }


    fun record(filePath: String): Boolean {
        audioRecord?.apply {
            startRecording()
            isRecording = true
            val file = File(filePath)
            if (!file.exists()) {
                file.createNewFile()
            }
            outStream = FileOutputStream(file)
            executor.execute {
                while (isRecording) {
                    val size = read(buffer, 0, bufferSize)
                    outStream?.write(buffer, 0, size)
                }
            }

        }
        return isRecording
    }

    fun stop() :Boolean{
        isRecording = false
        audioRecord?.stop()
        outStream?.close()
        executor.shutdown()

        return isRecording
    }
}