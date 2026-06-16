package com.example.businesscardgenerator.ui.template

import android.graphics.Color
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.businesscardgenerator.data.BusinessCardData

interface CardTemplate {
    fun createView(parent: ViewGroup, cardData: BusinessCardData): ConstraintLayout
    fun getTemplateName(): String
    fun getTemplateId(): String
}

class MinimalWhiteTemplate : CardTemplate {
    override fun createView(parent: ViewGroup, cardData: BusinessCardData): ConstraintLayout {
        val layout = ConstraintLayout(parent.context).apply {
            setBackgroundColor(Color.WHITE)
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                dpToPx(540)
            )
        }

        val padding = dpToPx(20)
        layout.setPadding(padding, padding, padding, padding)

        // Name
        val nameView = TextView(parent.context).apply {
            id = android.view.View.generateViewId()
            text = cardData.fullName
            textSize = 28f
            setTypeface(null, android.graphics.Typeface.BOLD)
            setTextColor(Color.BLACK)
        }
        layout.addView(nameView)
        val nameParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            topToTop = ConstraintLayout.LayoutParams.PARENT_ID
            startToStart = ConstraintLayout.LayoutParams.PARENT_ID
            endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
            marginStart = padding
            marginEnd = padding
            marginTop = padding
        }
        nameView.layoutParams = nameParams

        // Position
        var previousViewId = nameView.id
        if (cardData.position.isNotEmpty()) {
            val positionView = TextView(parent.context).apply {
                id = android.view.View.generateViewId()
                text = cardData.position
                textSize = 14f
                setTextColor(Color.GRAY)
            }
            layout.addView(positionView)
            val positionParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                topToBottom = previousViewId
                startToStart = ConstraintLayout.LayoutParams.PARENT_ID
                endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
                marginStart = padding
                marginEnd = padding
                marginTop = dpToPx(8)
            }
            positionView.layoutParams = positionParams
            previousViewId = positionView.id
        }

        // Company
        if (cardData.company.isNotEmpty()) {
            val companyView = TextView(parent.context).apply {
                id = android.view.View.generateViewId()
                text = cardData.company
                textSize = 12f
                setTextColor(Color.parseColor("#666666"))
            }
            layout.addView(companyView)
            val companyParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                topToBottom = previousViewId
                startToStart = ConstraintLayout.LayoutParams.PARENT_ID
                endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
                marginStart = padding
                marginEnd = padding
                marginTop = dpToPx(4)
            }
            companyView.layoutParams = companyParams
            previousViewId = companyView.id
        }

        // Divider
        val divider = android.view.View(parent.context).apply {
            id = android.view.View.generateViewId()
            setBackgroundColor(Color.parseColor("#E0E0E0"))
        }
        layout.addView(divider)
        val dividerParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,
            dpToPx(1)
        ).apply {
            topToBottom = previousViewId
            startToStart = ConstraintLayout.LayoutParams.PARENT_ID
            endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
            marginStart = padding
            marginEnd = padding
            marginTop = dpToPx(12)
        }
        divider.layoutParams = dividerParams
        previousViewId = divider.id

        // Contact info
        if (cardData.phone.isNotEmpty()) {
            val phoneView = TextView(parent.context).apply {
                id = android.view.View.generateViewId()
                text = "☎ ${cardData.phone}"
                textSize = 12f
                setTextColor(Color.parseColor("#333333"))
            }
            layout.addView(phoneView)
            val phoneParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                topToBottom = previousViewId
                startToStart = ConstraintLayout.LayoutParams.PARENT_ID
                endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
                marginStart = padding
                marginEnd = padding
                marginTop = dpToPx(12)
            }
            phoneView.layoutParams = phoneParams
            previousViewId = phoneView.id
        }

        if (cardData.email.isNotEmpty()) {
            val emailView = TextView(parent.context).apply {
                id = android.view.View.generateViewId()
                text = "✉ ${cardData.email}"
                textSize = 12f
                setTextColor(Color.parseColor("#333333"))
            }
            layout.addView(emailView)
            val emailParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                topToBottom = previousViewId
                startToStart = ConstraintLayout.LayoutParams.PARENT_ID
                endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
                marginStart = padding
                marginEnd = padding
                marginTop = dpToPx(4)
            }
            emailView.layoutParams = emailParams
        }

        return layout
    }

    override fun getTemplateName(): String = "简约白"
    override fun getTemplateId(): String = "minimal_white"

    private fun dpToPx(dp: Int): Int {
        return (dp * android.content.res.Resources.getSystem().displayMetrics.density).toInt()
    }
}

class TechBlueTemplate : CardTemplate {
    override fun createView(parent: ViewGroup, cardData: BusinessCardData): ConstraintLayout {
        val layout = ConstraintLayout(parent.context).apply {
            setBackgroundColor(Color.parseColor("#003366"))
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                dpToPx(540)
            )
        }

        val padding = dpToPx(20)
        layout.setPadding(padding, padding, padding, padding)

        // Name
        val nameView = TextView(parent.context).apply {
            id = android.view.View.generateViewId()
            text = cardData.fullName
            textSize = 28f
            setTypeface(null, android.graphics.Typeface.BOLD)
            setTextColor(Color.WHITE)
        }
        layout.addView(nameView)
        val nameParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            topToTop = ConstraintLayout.LayoutParams.PARENT_ID
            startToStart = ConstraintLayout.LayoutParams.PARENT_ID
            endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
            marginStart = padding
            marginEnd = padding
            marginTop = padding
        }
        nameView.layoutParams = nameParams

        // Position
        var previousViewId = nameView.id
        if (cardData.position.isNotEmpty()) {
            val positionView = TextView(parent.context).apply {
                id = android.view.View.generateViewId()
                text = cardData.position
                textSize = 14f
                setTextColor(Color.parseColor("#66CCFF"))
            }
            layout.addView(positionView)
            val positionParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                topToBottom = previousViewId
                startToStart = ConstraintLayout.LayoutParams.PARENT_ID
                endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
                marginStart = padding
                marginEnd = padding
                marginTop = dpToPx(8)
            }
            positionView.layoutParams = positionParams
            previousViewId = positionView.id
        }

        // Company
        if (cardData.company.isNotEmpty()) {
            val companyView = TextView(parent.context).apply {
                id = android.view.View.generateViewId()
                text = cardData.company
                textSize = 12f
                setTextColor(Color.parseColor("#99CCFF"))
            }
            layout.addView(companyView)
            val companyParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                topToBottom = previousViewId
                startToStart = ConstraintLayout.LayoutParams.PARENT_ID
                endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
                marginStart = padding
                marginEnd = padding
                marginTop = dpToPx(4)
            }
            companyView.layoutParams = companyParams
            previousViewId = companyView.id
        }

        // Divider
        val divider = android.view.View(parent.context).apply {
            id = android.view.View.generateViewId()
            setBackgroundColor(Color.parseColor("#004488"))
        }
        layout.addView(divider)
        val dividerParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,
            dpToPx(1)
        ).apply {
            topToBottom = previousViewId
            startToStart = ConstraintLayout.LayoutParams.PARENT_ID
            endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
            marginStart = padding
            marginEnd = padding
            marginTop = dpToPx(12)
        }
        divider.layoutParams = dividerParams
        previousViewId = divider.id

        // Contact info
        if (cardData.phone.isNotEmpty()) {
            val phoneView = TextView(parent.context).apply {
                id = android.view.View.generateViewId()
                text = "☎ ${cardData.phone}"
                textSize = 12f
                setTextColor(Color.WHITE)
            }
            layout.addView(phoneView)
            val phoneParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                topToBottom = previousViewId
                startToStart = ConstraintLayout.LayoutParams.PARENT_ID
                endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
                marginStart = padding
                marginEnd = padding
                marginTop = dpToPx(12)
            }
            phoneView.layoutParams = phoneParams
            previousViewId = phoneView.id
        }

        if (cardData.email.isNotEmpty()) {
            val emailView = TextView(parent.context).apply {
                id = android.view.View.generateViewId()
                text = "✉ ${cardData.email}"
                textSize = 12f
                setTextColor(Color.WHITE)
            }
            layout.addView(emailView)
            val emailParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                topToBottom = previousViewId
                startToStart = ConstraintLayout.LayoutParams.PARENT_ID
                endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
                marginStart = padding
                marginEnd = padding
                marginTop = dpToPx(4)
            }
            emailView.layoutParams = emailParams
        }

        return layout
    }

    override fun getTemplateName(): String = "科技蓝"
    override fun getTemplateId(): String = "tech_blue"

    private fun dpToPx(dp: Int): Int {
        return (dp * android.content.res.Resources.getSystem().displayMetrics.density).toInt()
    }
}

class AcademicGrayTemplate : CardTemplate {
    override fun createView(parent: ViewGroup, cardData: BusinessCardData): ConstraintLayout {
        val layout = ConstraintLayout(parent.context).apply {
            setBackgroundColor(Color.parseColor("#F5F5F5"))
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                dpToPx(540)
            )
        }

        val padding = dpToPx(20)
        layout.setPadding(padding, padding, padding, padding)

        // Name
        val nameView = TextView(parent.context).apply {
            id = android.view.View.generateViewId()
            text = cardData.fullName
            textSize = 26f
            setTypeface(null, android.graphics.Typeface.BOLD)
            setTextColor(Color.parseColor("#2C2C2C"))
        }
        layout.addView(nameView)
        val nameParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            topToTop = ConstraintLayout.LayoutParams.PARENT_ID
            startToStart = ConstraintLayout.LayoutParams.PARENT_ID
            endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
            marginStart = padding
            marginEnd = padding
            marginTop = padding
        }
        nameView.layoutParams = nameParams

        // Position
        var previousViewId = nameView.id
        if (cardData.position.isNotEmpty()) {
            val positionView = TextView(parent.context).apply {
                id = android.view.View.generateViewId()
                text = cardData.position
                textSize = 13f
                setTypeface(null, android.graphics.Typeface.ITALIC)
                setTextColor(Color.parseColor("#555555"))
            }
            layout.addView(positionView)
            val positionParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                topToBottom = previousViewId
                startToStart = ConstraintLayout.LayoutParams.PARENT_ID
                endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
                marginStart = padding
                marginEnd = padding
                marginTop = dpToPx(8)
            }
            positionView.layoutParams = positionParams
            previousViewId = positionView.id
        }

        // Company
        if (cardData.company.isNotEmpty()) {
            val companyView = TextView(parent.context).apply {
                id = android.view.View.generateViewId()
                text = cardData.company
                textSize = 11f
                setTextColor(Color.parseColor("#777777"))
            }
            layout.addView(companyView)
            val companyParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                topToBottom = previousViewId
                startToStart = ConstraintLayout.LayoutParams.PARENT_ID
                endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
                marginStart = padding
                marginEnd = padding
                marginTop = dpToPx(4)
            }
            companyView.layoutParams = companyParams
            previousViewId = companyView.id
        }

        // Divider
        val divider = android.view.View(parent.context).apply {
            id = android.view.View.generateViewId()
            setBackgroundColor(Color.parseColor("#CCCCCC"))
        }
        layout.addView(divider)
        val dividerParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,
            dpToPx(1)
        ).apply {
            topToBottom = previousViewId
            startToStart = ConstraintLayout.LayoutParams.PARENT_ID
            endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
            marginStart = padding
            marginEnd = padding
            marginTop = dpToPx(12)
        }
        divider.layoutParams = dividerParams
        previousViewId = divider.id

        // Contact info
        if (cardData.phone.isNotEmpty()) {
            val phoneView = TextView(parent.context).apply {
                id = android.view.View.generateViewId()
                text = "电话: ${cardData.phone}"
                textSize = 11f
                setTextColor(Color.parseColor("#333333"))
            }
            layout.addView(phoneView)
            val phoneParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                topToBottom = previousViewId
                startToStart = ConstraintLayout.LayoutParams.PARENT_ID
                endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
                marginStart = padding
                marginEnd = padding
                marginTop = dpToPx(10)
            }
            phoneView.layoutParams = phoneParams
            previousViewId = phoneView.id
        }

        if (cardData.email.isNotEmpty()) {
            val emailView = TextView(parent.context).apply {
                id = android.view.View.generateViewId()
                text = "邮箱: ${cardData.email}"
                textSize = 11f
                setTextColor(Color.parseColor("#333333"))
            }
            layout.addView(emailView)
            val emailParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                topToBottom = previousViewId
                startToStart = ConstraintLayout.LayoutParams.PARENT_ID
                endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
                marginStart = padding
                marginEnd = padding
                marginTop = dpToPx(4)
            }
            emailView.layoutParams = emailParams
        }

        return layout
    }

    override fun getTemplateName(): String = "学术灰"
    override fun getTemplateId(): String = "academic_gray"

    private fun dpToPx(dp: Int): Int {
        return (dp * android.content.res.Resources.getSystem().displayMetrics.density).toInt()
    }
}

object TemplateFactory {
    fun getTemplate(templateId: String): CardTemplate {
        return when (templateId) {
            "minimal_white" -> MinimalWhiteTemplate()
            "tech_blue" -> TechBlueTemplate()
            "academic_gray" -> AcademicGrayTemplate()
            else -> MinimalWhiteTemplate()
        }
    }

    fun getAllTemplates(): List<CardTemplate> {
        return listOf(
            MinimalWhiteTemplate(),
            TechBlueTemplate(),
            AcademicGrayTemplate()
        )
    }
}
