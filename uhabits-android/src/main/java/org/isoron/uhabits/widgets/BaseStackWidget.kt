package org.isoron.uhabits.widgets

import android.app.PendingIntent
import android.content.Context
import android.view.View
import android.widget.RemoteViews

abstract class BaseStackWidget(
    context: Context,
    widgetId: Int,
    stacked: Boolean = true
) : BaseWidget(context, widgetId, stacked) {

    // We don't use bitmap rendering for stack widgets
    override fun buildView(): View? = null
    override fun refreshData(widgetView: View) {}
    override fun getOnClickPendingIntent(context: Context): PendingIntent? = null
    override val defaultHeight: Int = 0
    override val defaultWidth: Int = 0

    // IMPORTANT: override this to bypass bitmap rendering
    override fun getRemoteViews(width: Int, height: Int): RemoteViews {
        return buildStackRemoteViews()
    }

    abstract fun buildStackRemoteViews(): RemoteViews

    fun getStackRemoteViews(): RemoteViews {
        return buildStackRemoteViews()
    }
}