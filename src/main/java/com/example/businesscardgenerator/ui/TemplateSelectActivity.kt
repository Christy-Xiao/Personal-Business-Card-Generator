package com.example.businesscardgenerator.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.businesscardgenerator.R
import com.example.businesscardgenerator.data.CardDataStore
import com.example.businesscardgenerator.ui.adapter.TemplateAdapter
import com.example.businesscardgenerator.ui.template.TemplateFactory
import kotlinx.coroutines.launch

class TemplateSelectActivity : AppCompatActivity() {

    private lateinit var dataStore: CardDataStore
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TemplateAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_template_select)

        dataStore = CardDataStore(this)
        recyclerView = findViewById(R.id.rv_templates)

        setupRecyclerView()
        loadTemplates()
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        adapter = TemplateAdapter { templateId ->
            selectTemplate(templateId)
        }
        recyclerView.adapter = adapter
    }

    private fun loadTemplates() {
        val templates = TemplateFactory.getAllTemplates()
        adapter.submitList(templates)
    }

    private fun selectTemplate(templateId: String) {
        lifecycleScope.launch {
            dataStore.getCardData().collect { cardData ->
                val updatedData = cardData.copy(templateId = templateId)
                dataStore.saveCardData(updatedData)
                finish()
            }
        }
    }
}
