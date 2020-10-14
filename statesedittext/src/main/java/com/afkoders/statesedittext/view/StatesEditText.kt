package com.afkoders.statesedittext.view

import android.content.Context
import android.content.res.Resources
import android.content.res.TypedArray
import android.text.InputFilter
import android.text.InputType
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.afkoders.statesedittext.R
import kotlinx.android.synthetic.main.view_compound_edittext_empty.view.*


/**
 * Created by Kalevych Oleksandr on 06.10.2020.
 */

class StatesEditText @JvmOverloads constructor(context: Context,
                                               attrs: AttributeSet? = null,
                                               defStyleAttr: Int = 0,
                                               defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

  private val emptyState: BaseConstraintSetState
  private val typingState: BaseConstraintSetState
  private val errorState: BaseConstraintSetState
  private var currentState: Int

  var hint
    get() = tvEditLabel.text
    set(hint) {
      tvEditLabel.text = hint
    }

  init {
    inflate(context, R.layout.view_compound_edittext_empty, this)
    val typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.StatesEditText)
    with(typedArray) {
      hint = getString(R.styleable.StatesEditText_etc_hint)
      etContainerValue.setRawInputType(getInt(R.styleable.StatesEditText_etc_input_type, InputType.TYPE_CLASS_TEXT))
      etContainerValue.imeOptions = getInt(R.styleable.StatesEditText_etc_ime_option, EditorInfo.IME_ACTION_NEXT)
      if (hasValue(R.styleable.StatesEditText_etc_focus_next)) {
        etContainerValue.nextFocusForwardId = getInt(R.styleable.StatesEditText_etc_focus_next, 0)
      }
    }

    emptyState = EmptyConstraintSetState(STATE_EMPTY).setFrom(clCompoundRoot)
    typingState = TypingConstraintSetState(STATE_TYPING).setFrom(clCompoundRoot)
    errorState = ErrorConstraintSetState(STATE_ERROR).setFrom(clCompoundRoot)

    currentState = STATE_EMPTY

    this.setOnFocusChangeListener { v, hasFocus -> setFocus(hasFocus) }
  }

  var text
    get() = etContainerValue.text.toString()
    set(value) {
      etContainerValue.setText(value)
      if (value.isNotBlank()) {
        applyState(typingState)
        tvEditLabel.setTextColor(ContextCompat.getColor(tvEditLabel.context, R.color.colorStateEditTextDark))
      }
    }

  val editText
    get() = etContainerValue

  fun setError(errorString: String? = null) {
    if (errorString.isNullOrBlank()) {
      disableError()
    } else {
      if (etContainerValue.text.isNullOrBlank()) {
        applyState(emptyState)
      } else {
        tvEditError.text = errorString
        applyState(errorState)
      }
    }
  }


  override fun setOnFocusChangeListener(l: OnFocusChangeListener?) {
    super.setOnFocusChangeListener(l)
    etContainerValue.onFocusChangeListener = object : OnFocusChangeListener {
      override fun onFocusChange(v: View?, hasFocus: Boolean) {
        l?.onFocusChange(v, hasFocus)
        setFocus(hasFocus)
      }
    }
  }

  private fun disableError() {
    tvEditError.text = ""
    if (etContainerValue.text.isNullOrBlank()) {
      applyState(emptyState)
    } else {
      applyState(typingState)
    }
  }

  fun setFocus(focus: Boolean) {
    if (focus) {
      applyState(typingState)
      tvEditLabel.setTextColor(ContextCompat.getColor(context, R.color.colorStateEditTextGreen))
    } else {
      tvEditLabel.setTextColor(ContextCompat.getColor(context, if (text.isBlank()) R.color.colorStateEditTextDark70 else R.color.colorStateEditTextDark))
    }
  }

  fun applyFilters(filters: Array<InputFilter>) {
    etContainerValue.filters = filters
  }

  private fun applyState(constraintSet: BaseConstraintSetState) {
    if (currentState != constraintSet.stateId) {
      currentState = constraintSet.stateId
      constraintSet.applyTo(clCompoundRoot)
    }
  }

  companion object {
    const val STATE_EMPTY = 0
    const val STATE_TYPING = 1
    const val STATE_ERROR = 2
  }
}

internal val Float.dp: Float
  get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, Resources.getSystem().displayMetrics)

internal val Float.sp: Float
  get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, this, Resources.getSystem().displayMetrics)
