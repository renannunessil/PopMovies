package br.com.renannunessil.popmovies.data

import android.content.Context
import java.io.IOException

class JsonUtils {
    companion object {
        fun getAssetJsonData(context: Context, file: String): String? {
            val json: String
            try {
                val inputStream = context.assets.open(file)
                val size = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.use { it.read(buffer) }
                json = String(buffer)
            } catch (ioException: IOException) {
                ioException.printStackTrace()
                return null
            }

            return json
        }
    }
}