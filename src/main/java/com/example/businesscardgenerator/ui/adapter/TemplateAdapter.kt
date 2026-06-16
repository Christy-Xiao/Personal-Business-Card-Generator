package com.example.businesscardgenerator.ui.adapter

import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.businesscardgenerator.data.BusinessCardData
import com.example.businesscardgenerator.ui.template.CardTemplate

class TemplateAdapter(
    private val onTemplateSelected: (String) -> Unit
) : ListAdapter<CardTemplate, TemplateAdapter.TemplateViewHolder>(TemplateDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TemplateViewHolder {
        val container = FrameLayout(parent.context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                dpToPx(300)
            )
        }
        return TemplateViewHolder(container)
    }

    override fun onBindViewHolder(holder: TemplateViewHolder, position: Int) {
        val template = getItem(position)
        holder.bind(template, onTemplateSelected)
    }

    class TemplateViewHolder(
        private val container: FrameLayout
    ) : RecyclerView.ViewHolder(container) {

        fun bind(template: CardTemplate, onTemplateSelected: (String) -> Unit) {
            container.removeAllViews()

            // Create preview with sample data
            val sampleData = BusinessCardData(
                fullName = "预览",
                position = "${template.getTemplateName()}",
                phone = "",
                email = "",
                company = "",
                introduction = "",
                avatarPath = "",
                templateId = template.getTemplateId()
            )

            val cardView = template.createView(container, sampleData)
            container.addView(cardView)

            container.setOnClickListener {
                onTemplateSelected(template.getTemplateId())
            }
        }
    }

    private class TemplateDiffCallback : DiffUtil.ItemCallback<CardTemplate>() {
        override fun areItemsTheSame(oldItem: CardTemplate, newItem: CardTemplate): Boolean {
            return oldItem.getTemplateId() == newItem.getTemplateId()
        }

        override fun areContentsTheSame(oldItem: CardTemplate, newItem: CardTemplate): Boolean {
            return oldItem.getTemplateId() == newItem.getTemplateId() &&
                    oldItem.getTemplateName() == newItem.getTemplateName()
        }
    }

    private fun dpToPx(dp: Int): Int {
        return (dp * android.content.res.Resources.getSystem().displayMetrics.density).toInt()
    }
}
