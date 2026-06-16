package com.example.businesscardgenerator.ui

import android.graphics.Bitmap
import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.businesscardgenerator.R
import com.example.businesscardgenerator.data.CardDataStore
import com.example.businesscardgenerator.ui.template.TemplateFactory
import com.example.businesscardgenerator.utils.QRCodeGenerator
import com.example.businesscardgenerator.utils.ScreenshotHelper
import kotlinx.coroutines.launch

class CardDetailActivity : AppCompatActivity() {

    private lateinit var dataStore: CardDataStore
    private lateinit var cardContainer: FrameLayout
    private lateinit var ivQRCode: ImageView
    private lateinit var btnSaveScreenshot: Button
    private lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_detail)

        dataStore = CardDataStore(this)

        initViews()
        loadCardData()
        setupListeners()
    }

    private fun initViews() {
        cardContainer = findViewById(R.id.card_container)
        ivQRCode = findViewById(R.id.iv_qr_code)
        btnSaveScreenshot = findViewById(R.id.btn_save_screenshot)
        btnBack = findViewById(R.id.btn_back)
    }

    private fun loadCardData() {
        lifecycleScope.launch {
            dataStore.getCardData().collect { cardData ->
                // Create card view based on template
                val template = TemplateFactory.getTemplate(cardData.templateId)
                val cardView = template.createView(cardContainer, cardData)
                cardContainer.removeAllViews()
                cardContainer.addView(cardView)

                // Generate QR code
                val vCard = QRCodeGenerator.generateVCard(cardData)
                val qrBitmap = QRCodeGenerator.generateQRCode(vCard, 512)
                if (qrBitmap != null) {
                    ivQRCode.setImageBitmap(qrBitmap)
                }
            }
        }
    }

    private fun setupListeners() {
        btnSaveScreenshot.setOnClickListener {
            saveCardAsScreenshot()
        }

        btnBack.setOnClickListener {
            finish()
        }
    }

    private fun saveCardAsScreenshot() {
        try {
            val bitmap = ScreenshotHelper.captureViewAsBitmap(cardContainer)
            val fileName = ScreenshotHelper.generateFileName("BusinessCard")
            val success = ScreenshotHelper.saveBitmapToGallery(this, bitmap, fileName)

            if (success) {
                Toast.makeText(this, "名片已保存到相册", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "保存失败", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "保存出错: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
}
