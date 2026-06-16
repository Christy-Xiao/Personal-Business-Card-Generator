package com.example.businesscardgenerator.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.businesscardgenerator.R
import com.example.businesscardgenerator.data.BusinessCardData
import com.example.businesscardgenerator.data.CardDataStore
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var dataStore: CardDataStore
    private lateinit var etFullName: EditText
    private lateinit var etPosition: EditText
    private lateinit var etPhone: EditText
    private lateinit var etEmail: EditText
    private lateinit var etCompany: EditText
    private lateinit var etIntroduction: EditText
    private lateinit var btnSelectTemplate: Button
    private lateinit var btnPreview: Button
    private lateinit var btnShare: Button
    private lateinit var ivAvatar: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dataStore = CardDataStore(this)

        initViews()
        loadSavedData()
        setupListeners()
    }

    private fun initViews() {
        etFullName = findViewById(R.id.et_full_name)
        etPosition = findViewById(R.id.et_position)
        etPhone = findViewById(R.id.et_phone)
        etEmail = findViewById(R.id.et_email)
        etCompany = findViewById(R.id.et_company)
        etIntroduction = findViewById(R.id.et_introduction)
        btnSelectTemplate = findViewById(R.id.btn_select_template)
        btnPreview = findViewById(R.id.btn_preview)
        btnShare = findViewById(R.id.btn_share)
        ivAvatar = findViewById(R.id.iv_avatar)
    }

    private fun loadSavedData() {
        lifecycleScope.launch {
            dataStore.getCardData().collect { cardData ->
                etFullName.setText(cardData.fullName)
                etPosition.setText(cardData.position)
                etPhone.setText(cardData.phone)
                etEmail.setText(cardData.email)
                etCompany.setText(cardData.company)
                etIntroduction.setText(cardData.introduction)
            }
        }
    }

    private fun setupListeners() {
        btnSelectTemplate.setOnClickListener {
            startActivity(Intent(this, TemplateSelectActivity::class.java))
        }

        btnPreview.setOnClickListener {
            saveData()
            startActivity(Intent(this, CardDetailActivity::class.java))
        }

        btnShare.setOnClickListener {
            saveData()
            shareCard()
        }

        ivAvatar.setOnClickListener {
            // TODO: Implement avatar selection
            Toast.makeText(this, "Avatar selection coming soon", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveData() {
        val cardData = BusinessCardData(
            fullName = etFullName.text.toString(),
            position = etPosition.text.toString(),
            phone = etPhone.text.toString(),
            email = etEmail.text.toString(),
            company = etCompany.text.toString(),
            introduction = etIntroduction.text.toString(),
            avatarPath = "",
            templateId = ""
        )

        lifecycleScope.launch {
            dataStore.saveCardData(cardData)
            Toast.makeText(this@MainActivity, "Data saved", Toast.LENGTH_SHORT).show()
        }
    }

    private fun shareCard() {
        val shareText = buildString {
            append("姓名: ${etFullName.text}\n")
            if (etPosition.text.isNotEmpty()) {
                append("职位: ${etPosition.text}\n")
            }
            if (etCompany.text.isNotEmpty()) {
                append("公司/学校: ${etCompany.text}\n")
            }
            if (etPhone.text.isNotEmpty()) {
                append("电话: ${etPhone.text}\n")
            }
            if (etEmail.text.isNotEmpty()) {
                append("邮箱: ${etEmail.text}\n")
            }
            if (etIntroduction.text.isNotEmpty()) {
                append("简介: ${etIntroduction.text}\n")
            }
        }

        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareText)
            type = "text/plain"
        }
        startActivity(Intent.createChooser(shareIntent, "分享名片"))
    }
}
