package org.isoron.uhabits.widgets.activities

class QuickActionsHabitPickerDialog : HabitPickerDialog() {

    override fun onResume() {
        super.onResume()

        window.decorView.post {
            confirm(habitIds)
        }
    }
}