package net.asch.asc.ui.component

import io.wispforest.owo.ui.container.GridLayout
import io.wispforest.owo.ui.core.Size
import io.wispforest.owo.ui.core.Sizing
import io.wispforest.owo.util.Observable
import org.apache.commons.lang3.mutable.MutableInt
import java.util.*
import kotlin.math.max

class GridLayout(hSizing: Sizing, vSizing: Sizing, rows: Int, columns: Int) :
    GridLayout(hSizing, vSizing, rows, columns) {
    private val gap: Observable<Int> = Observable.of(0);

    fun gap(): Int = gap.get()
    fun gap(value: Int) {
        gap.set(value)
    }

    override fun determineHorizontalContentSize(sizing: Sizing): Int {
        return contentSize.width + padding.get().right + (columns - 1) * gap.get()
    }

    override fun determineVerticalContentSize(sizing: Sizing): Int {
        return contentSize.height + padding.get().bottom + (rows - 1) * gap.get()
    }

    override fun layout(space: Size) {
        val columnSizes = IntArray(columns)
        val rowSizes = IntArray(rows)

        val childSpace = calculateChildSpace(space)
        for (child in children) {
            child?.inflate(childSpace)
        }

        determineSizes(columnSizes, false)
        determineSizes(rowSizes, true)

        val mountingOffset = childMountingOffset()
        val layoutX = MutableInt(x + mountingOffset.width)
        val layoutY = MutableInt(y + mountingOffset.height)

        val vGapAccumulator = MutableInt(0)
        for (row in 0 until rows) {
            val rowSize = rowSizes[row]
            layoutX.setValue(x + mountingOffset.width)

            val hGapAccumulator = MutableInt(0)
            for (column in 0 until columns) {
                val columnSize = columnSizes[column]

                mountChild(getChild(row, column)) { child ->
                    child.mount(
                        this,
                        layoutX.toInt() + child.margins()
                            .get().left + hGapAccumulator.toInt() + horizontalAlignment.get()
                            .align(child.fullSize().width, columnSize),
                        layoutY.toInt() + child.margins().get().top + vGapAccumulator.toInt() + verticalAlignment.get()
                            .align(child.fullSize().height, rowSize)
                    )
                }

                layoutX.add(columnSizes[column])
                hGapAccumulator.add(gap.get())
            }

            layoutY.add(rowSizes[row])
            vGapAccumulator.add(gap.get())
        }

        contentSize = Size.of(layoutX.toInt() - x, layoutY.toInt() - y)
    }

    override fun determineSizes(sizes: IntArray, rows: Boolean) {
        val sizing = if (rows) verticalSizing else horizontalSizing
        if (sizing.get().method != Sizing.Method.CONTENT) {
            val size =
                (if (rows) height - padding.get().vertical() - (this.rows - 1) * gap.get() else width - padding.get()
                    .horizontal() - (this.columns - 1) * gap.get()) /
                        (if (rows) this.rows else this.columns)
            Arrays.fill(sizes, size)
        } else {
            for (row in 0 until this.rows) {
                for (column in 0 until this.columns) {
                    val child = getChild(row, column) ?: continue
                    if (rows) {
                        sizes[row] = max(sizes[row], child.fullSize().height + gap.get())
                    } else {
                        sizes[column] = max(sizes[column], child.fullSize().width + gap.get())
                    }
                }
            }
        }
    }

    override fun calculateChildSpace(thisSpace: Size): Size {
        val padding = this.padding.get()
        val gap = this.gap.get()

        val width = if (this.horizontalSizing.get().isContent) thisSpace.width else this.width
        val height = if (this.verticalSizing.get().isContent) thisSpace.height else this.height

        return Size.of(
            width - padding.horizontal() - (columns - 1) * gap,
            height - padding.vertical() - (rows - 1) * gap
        )
    }
}