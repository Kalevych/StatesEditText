package com.afkoders.statesedittext.view

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.afkoders.statesedittext.R

/**
 * Created by Kalevych Oleksandr on 14.10.2020.
 */

class ErrorConstraintSetState(stateId: Int) : BaseConstraintSetState(stateId) {
    override fun setFrom(layout: ConstraintLayout): BaseConstraintSetState {
        return super.setFrom(layout).also {
            setMargin(R.id.tvEditLabel, START, 15f.dp.toInt())
            setMargin(R.id.tvEditLabel, TOP, 12f.dp.toInt())
            setMargin(R.id.tvEditLabel, END, 16f.dp.toInt())
            clear(R.id.tvEditLabel, BOTTOM)
            connect(R.id.tvEditLabel, TOP, PARENT_ID, TOP)

            setMargin(R.id.etContainerValue, BOTTOM, 0f.dp.toInt())
            setMargin(R.id.etContainerValue, TOP, 0f.dp.toInt())
            clear(R.id.etContainerValue, BOTTOM)
            connect(R.id.etContainerValue, TOP, R.id.tvEditLabel, BOTTOM)

            setMargin(R.id.tvEditError, START, 15f.dp.toInt())
            setMargin(R.id.tvEditError, TOP, 4f.dp.toInt())
            setMargin(R.id.tvEditError, END, 16f.dp.toInt())
            connect(R.id.tvEditError, BOTTOM, PARENT_ID, BOTTOM)
            connect(R.id.tvEditError, TOP, R.id.etContainerValue, BOTTOM)
            setVisibility(R.id.tvEditError, View.VISIBLE)
            setVisibility(R.id.vAlertVerticalLine, View.VISIBLE)
        }
    }
}