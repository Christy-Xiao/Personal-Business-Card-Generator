package com.example.businesscardgenerator.utils

import android.graphics.Bitmap
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import com.example.businesscardgenerator.data.BusinessCardData

object QRCodeGenerator {

    /**
     * Generate vCard format string from business card data
     */
    fun generateVCard(cardData: BusinessCardData): String {
        return buildString {
            appendLine("BEGIN:VCARD")
            appendLine("VERSION:3.0")
            appendLine("FN:${cardData.fullName}")
            
            if (cardData.position.isNotEmpty()) {
                appendLine("TITLE:${cardData.position}")
            }
            if (cardData.company.isNotEmpty()) {
                appendLine("ORG:${cardData.company}")
            }
            if (cardData.phone.isNotEmpty()) {
                appendLine("TEL:${cardData.phone}")
            }
            if (cardData.email.isNotEmpty()) {
                appendLine("EMAIL:${cardData.email}")
            }
            if (cardData.introduction.isNotEmpty()) {
                appendLine("NOTE:${cardData.introduction}")
            }
            
            appendLine("END:VCARD")
        }
    }

    /**
     * Generate QR code bitmap from vCard data
     */
    fun generateQRCode(vCardData: String, size: Int = 512): Bitmap? {
        return try {
            val bitMatrix = MultiFormatWriter().encode(
                vCardData,
                BarcodeFormat.QR_CODE,
                size,
                size
            )
            createBitmapFromBitMatrix(bitMatrix)
        } catch (e: WriterException) {
            e.printStackTrace()
            null
        }
    }

    /**
     * Create bitmap from BitMatrix
     */
    private fun createBitmapFromBitMatrix(bitMatrix: BitMatrix): Bitmap {
        val width = bitMatrix.width
        val height = bitMatrix.height
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)

        for (x in 0 until width) {
            for (y in 0 until height) {
                bitmap.setPixel(x, y, if (bitMatrix[x, y]) android.graphics.Color.BLACK else android.graphics.Color.WHITE)
            }
        }

        return bitmap
    }

    /**
     * Generate simple contact sharing text
     */
    fun generateShareText(cardData: BusinessCardData): String {
        return buildString {
            append("姓名: ${cardData.fullName}\n")
            if (cardData.position.isNotEmpty()) {
                append("职位: ${cardData.position}\n")
            }
            if (cardData.company.isNotEmpty()) {
                append("公司/学校: ${cardData.company}\n")
            }
            if (cardData.phone.isNotEmpty()) {
                append("电话: ${cardData.phone}\n")
            }
            if (cardData.email.isNotEmpty()) {
                append("邮箱: ${cardData.email}\n")
            }
            if (cardData.introduction.isNotEmpty()) {
                append("简介: ${cardData.introduction}\n")
            }
        }
    }
}
