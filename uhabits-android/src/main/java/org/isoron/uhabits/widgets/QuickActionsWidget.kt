package org.isoron.uhabits.widgets

import android.app.PendingIntent
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RemoteViews
import android.widget.TextView
import org.isoron.uhabits.R
import org.isoron.uhabits.core.models.Entry
import org.isoron.uhabits.core.models.Habit
import org.isoron.uhabits.core.utils.DateUtils
import kotlin.text.format

class QuickActionsWidget(
    context: Context,
    widgetId: Int,
    private val habit: Habit,
    stacked: Boolean = false
) : BaseStackWidget(context, widgetId, stacked) {

    override fun getOnClickPendingIntent(context: Context): PendingIntent? {
        Log.i("CLICK_FLOW", "Creating PendingIntent for habit=" + habit.id)
        return if (habit.isNumerical) {
            pendingIntentFactory.showNumberPicker(habit, DateUtils.getTodayWithOffset())
        } else {
            pendingIntentFactory.toggleCheckmark(habit, DateUtils.getTodayWithOffset().unixTime)
        }
    }

    override fun buildStackRemoteViews(): RemoteViews {
        val rv = RemoteViews(context.packageName, R.layout.widget_quick_actions)

        val today = DateUtils.getTodayWithOffset()
        val entry = habit.computedEntries.get(today)
        val value = entry.value

        rv.setTextViewText(R.id.textHabitName, habit.name)

        if (habit.isNumerical) {
            val displayValue = value / 1000.0
            rv.setTextViewText(R.id.textStatus, String.format("%.0f", displayValue))
            rv.setViewVisibility(R.id.textStatus, View.VISIBLE)
            rv.setViewVisibility(R.id.imageStatus, View.GONE)
        } else {
            val iconRes = when (value) {
                Entry.YES_MANUAL -> R.drawable.ic_check
                Entry.SKIP -> R.drawable.ic_skip
                Entry.NO -> R.drawable.ic_close
                else -> R.drawable.ic_empty
            }

            rv.setImageViewResource(R.id.imageStatus, iconRes)
            rv.setViewVisibility(R.id.imageStatus, View.VISIBLE)
            rv.setViewVisibility(R.id.textStatus, View.GONE)
        }


        if (!stacked) {
            val clickIntent = getOnClickPendingIntent(context)
            if (clickIntent != null) {
                rv.setOnClickPendingIntent(R.id.button, clickIntent)
            }
        }
        return rv
    }
}