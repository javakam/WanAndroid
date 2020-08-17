package com.ando.wo.utils

import android.content.res.Resources
import android.util.TypedValue

/**
 * Title:
 * <p>
 * Description:
 * </p>
 * @author javakam
 * @date 2020/8/17  15:06
 */

val Float.toDP
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )