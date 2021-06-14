package io.fs.marvel.widget

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import kotlin.math.roundToInt

class AspectImageView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, style: Int = 0): AppCompatImageView(context, attrs, style) {

    companion object {
        private const val RATIO = 0.75f
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthFromSpec = MeasureSpec.getSize(widthMeasureSpec)
        val estimatedHeight = (widthFromSpec * RATIO).roundToInt()

        val newHeightMeasureSpec = MeasureSpec.makeMeasureSpec(estimatedHeight, MeasureSpec.EXACTLY)
        super.onMeasure(widthMeasureSpec, newHeightMeasureSpec)
    }
}