package org.isoron.uhabits.widgets

import android.content.Context

class QuickActionsWidgetProvider : BaseWidgetProvider() {
    override fun getWidgetFromId(context: Context, id: Int): BaseWidget {
        val application = context.applicationContext as org.isoron.uhabits.HabitsApplication
        val habits = application.component.habitList.filter { !it.isArchived }
        return if (habits.size == 1) {
            QuickActionsWidget(context, id, habits[0])
        } else {
            StackWidget(context, id, StackWidgetType.QUICK_ACTIONS, habits)
        }
    }
}