package com.afkoders.statesedittext.view

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.afkoders.statesedittext.R

/**
 * Created by Kalevych Oleksandr on 14.10.2020.
 */

class TypingConstraintSetState(stateId: Int) : BaseConstraintSetState(stateId) {
    override fun setFrom(layout: ConstraintLayout): BaseConstraintSetState {
        return super.setFrom(layout).also {
            setMargin(R.id.tvEditLabel, START, 16f.dp.toInt())
            setMargin(R.id.tvEditLabel, TOP, 12f.dp.toInt())
            setMargin(R.id.tvEditLabel, END, 16f.dp.toInt())
            setMargin(R.id.tvEditLabel, BOTTOM, 0f.dp.toInt())
            clear(R.id.tvEditLabel, BOTTOM)
            connect(R.id.tvEditLabel, TOP, PARENT_ID, TOP)

            setMargin(R.id.etContainerValue, BOTTOM, 12f.dp.toInt())
            setMargin(R.id.etContainerValue, TOP, 0f.dp.toInt())
            clear(R.id.etContainerValue, BOTTOM)
            connect(R.id.etContainerValue, TOP, R.id.tvEditLabel, BOTTOM)

            setMargin(R.id.tvEditError, START, 0f.dp.toInt())
            setMargin(R.id.tvEditError, TOP, 0f.dp.toInt())
            setMargin(R.id.tvEditError, BOTTOM, 0f.dp.toInt())
            clear(R.id.tvEditError, BOTTOM)
            connect(R.id.tvEditError, TOP, R.id.etContainerValue, BOTTOM)
            setVisibility(R.id.tvEditError, View.GONE)
            setVisibility(R.id.vAlertVerticalLine, View.GONE)
        }
    }
}