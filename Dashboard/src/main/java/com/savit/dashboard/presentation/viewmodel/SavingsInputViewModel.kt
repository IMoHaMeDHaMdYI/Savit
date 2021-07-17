package com.savit.dashboard.presentation.viewmodel

import com.savit.core.base.viewmodel.BaseViewModel
import com.savit.dashboard.presentation.viewstate.SavingsInputViewAction
import com.savit.dashboard.presentation.viewstate.SavingsInputViewEvent
import com.savit.dashboard.presentation.viewstate.SavingsInputViewState
import kotlin.math.sqrt

typealias Vector = DoubleArray
typealias Matrix = Array<Vector>

class SavingsInputViewModel : BaseViewModel<
        SavingsInputViewState,
        SavingsInputViewEvent,
        SavingsInputViewAction
        >() {
    override val initViewState: SavingsInputViewState = SavingsInputViewState()

    override fun postAction(viewAction: SavingsInputViewAction) {

    }

    private val x = 343.0

    fun computeResult() {
        val v1 = arrayOf(
            doubleArrayOf(1.0, x, x * x, x * x * x, sqrt(x))
        )

        val v2 = arrayOf(
            doubleArrayOf(0.270519, 0.305971, 0.208009, 0.278893, 0.202500),
            doubleArrayOf(0.037172, 0.392921, 0.690647, 0.073206, 0.154278),
            doubleArrayOf(0.210456, 0.480898, 0.778026, 0.164913, 0.230041),
            doubleArrayOf(0.569313, 0.520357, 0.570898, 0.425971, 0.3520118),
            doubleArrayOf(0.061106, 0.314322, 0.479326, 0.103655, 0.137505),
        )
        (v1 * v2).forEachIndexed { index, doubles ->
            print("${doubles.map { it }},")
        }
    }


    operator fun Matrix.times(other: Matrix): Matrix {
        val rows1 = this.size
        val cols1 = this[0].size
        val rows2 = other.size
        val cols2 = other[0].size
        require(cols1 == rows2)
        val result = Matrix(rows1) { Vector(cols2) }
        for (i in 0 until rows1) {
            for (j in 0 until cols2) {
                for (k in 0 until rows2) {
                    result[i][j] += this[i][k] * other[k][j]
                }
            }
        }
        return result
    }

}