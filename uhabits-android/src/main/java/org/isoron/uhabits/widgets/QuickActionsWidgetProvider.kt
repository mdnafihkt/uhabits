package org.isoron.uhabits.widgets

import android.content.Context

class QuickActionsWidgetProvider : BaseWidgetProvider() {
    override fun getWidgetFromId(context: Context, id: Int): BaseWidget {
        val habits = getHabitsFromWidgetId(id)
        return if (habits.size == 1) {
            QuickActionsWidget(context, id, habits[0])
        } else {
            StackWidget(context, id, StackWidgetType.QUICK_ACTIONS, habits)
        }
    }
}