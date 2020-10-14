package com.afkoders.statesedittext.view

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.afkoders.statesedittext.R

/**
 * Created by Kalevych Oleksandr on 14.10.2020.
 */

open class BaseConstraintSetState(val stateId: Int = 0) : ConstraintSet() {
    open fun setFrom(layout: ConstraintLayout): BaseConstraintSetState {
        this.clone(layout)
        this.apply {
            connect(R.id.tvEditLabel, END, PARENT_ID, END)
            connect(R.id.tvEditLabel, START, R.id.vAlertVerticalLine, END)

            connect(R.id.etContainerValue, END, PARENT_ID, END)
            connect(R.id.etContainerValue, START, R.id.vAlertVerticalLine, END)

            connect(R.id.tvEditError, END, PARENT_ID, END)
            connect(R.id.tvEditError, START, R.id.vAlertVerticalLine, END)

            setVisibility(R.id.etContainerValue, View.VISIBLE)
            setVisibility(R.id.tvEditLabel, View.VISIBLE)
        }
        return this
    }
}