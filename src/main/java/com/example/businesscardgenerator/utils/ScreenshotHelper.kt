package com.example.businesscardgenerator.utils

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object ScreenshotHelper {

    /**
     * Capture view as bitmap
     */
    fun captureViewAsBitmap(view: View): Bitmap {
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }

    /**
     * Save bitmap to gallery
     */
    fun saveBitmapToGallery(context: Context, bitmap: Bitmap, fileName: String): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            saveBitmapToGalleryModern(context, bitmap, fileName)
        } else {
            saveBitmapToGalleryLegacy(context, bitmap, fileName)
        }
    }

    /**
     * Save bitmap to gallery for Android 10+
     */
    private fun saveBitmapToGalleryModern(
        context: Context,
        bitmap: Bitmap,
        fileName: String
    ): Boolean {
        val values = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, fileName)
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
        }

        val uri = context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
            ?: return false

        return try {
            context.contentResolver.openOutputStream(uri)?.use { outputStream ->
                bitmap.compress(Bitmap.CompressFormat.JPEG, 95, outputStream)
                true
            } ?: false
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    /**
     * Save bitmap to gallery for Android 9 and below
     */
    private fun saveBitmapToGalleryLegacy(
        context: Context,
        bitmap: Bitmap,
        fileName: String
    ): Boolean {
        return try {
            val picturesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            if (!picturesDir.exists()) {
                picturesDir.mkdirs()
            }

            val file = File(picturesDir, fileName)
            FileOutputStream(file).use { outputStream ->
                bitmap.compress(Bitmap.CompressFormat.JPEG, 95, outputStream)
                true
            }
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    /**
     * Generate unique filename with timestamp
     */
    fun generateFileName(prefix: String = "BusinessCard"): String {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        return "${prefix}_$timeStamp.jpg"
    }

    /**
     * Get cache file for temporary storage
     */
    fun getCacheBitmapFile(context: Context): File {
        val cacheDir = context.cacheDir
        return File(cacheDir, "business_card_temp.jpg")
    }

    /**
     * Save bitmap to cache
     */
    fun saveBitmapToCache(context: Context, bitmap: Bitmap): Boolean {
        return try {
            val file = getCacheBitmapFile(context)
            FileOutputStream(file).use { outputStream ->
                bitmap.compress(Bitmap.CompressFormat.JPEG, 95, outputStream)
                true
            }
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    /**
     * Get URI from cached bitmap file
     */
    fun getCacheFileUri(context: Context): String {
        val file = getCacheBitmapFile(context)
        return FileProvider.getUriForFile(
            context,
            "${context.packageName}.fileprovider",
            file
        ).toString()
    }
}
