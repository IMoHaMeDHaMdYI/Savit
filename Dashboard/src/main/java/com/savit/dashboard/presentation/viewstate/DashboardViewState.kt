package com.savit.dashboard.presentation.viewstate

import com.savit.category.model.categories
import com.savit.core.base.viewstate.ViewState
import com.savit.dashboard.presentation.model.AccountUiModel
import com.savit.dashboard.presentation.model.PlanEntityUiModel
import com.savit.dashboard.presentation.model.RecordUiModel
import kotlin.math.sqrt

typealias Vector = DoubleArray
typealias Matrix = Array<Vector>

data class DashboardViewState(
    val accounts: List<AccountUiModel>,
    val records: List<RecordUiModel>,
    val isAccountsEmpty: Boolean,
    val isRecordsEmpty: Boolean
) : ViewState {
    private val accountAmount: Double
        get() = accounts.find { it.isSelected }?.amount?.toDouble()
            ?: 0.0
    private val savingsX: Double get() = accountAmount / 10000
    private val result: Matrix get() = computeResult(savingsX)

    val planOverViewItems: List<PlanEntityUiModel>
        get() = categories.take(5).mapIndexed { index, category ->
            val amounts =
                records.filter { it.category.id.toLong() == category.id && it.amount.toDouble() < 0 }
                    .map { it.copy(amount = (it.amount.toDouble() * -1).toString()) }
                    .sumOf { it.amount.toDouble() }
            val target = result[0][index] * 1000
            val progress = if (amounts > target) {
                100.0
            } else {
                (amounts / target) * 100
            }
            PlanEntityUiModel(progress = progress.toInt(), name = category.name, amount = target)
        }

    fun computeResult(x: Double): Matrix {
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
        return v1 * v2
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