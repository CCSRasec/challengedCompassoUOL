package com.example.challengedcompassouol.presentation.events_details

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.challengedcompassouol.R
import com.example.challengedcompassouol.presentation.base.BaseActivity
import kotlinx.android.synthetic.main.activity_event_details.*
import kotlinx.android.synthetic.main.include_toolbar.*

class EventDetailsActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_details)

        setupToolbar(toolbarMain, R.string.event_details_title, true)

        eventDetailsTitle.text = intent.getStringExtra(EXTRA_TITLE)
        eventDetailsDescription.text = intent.getStringExtra(EXTRA_DESCRIPTION)
        eventDetailsPrice.text = "R$ "+intent.getStringExtra(EXTRA_PRICE)
    }

    companion object {
        private const val EXTRA_TITLE = "EXTRA_TITLE"
        private const val EXTRA_PRICE = "EXTRA_PRICE"
        private const val EXTRA_DESCRIPTION = "EXTRA_DESCRIPTION"

        fun getStartIntent(context: Context, title: String, description: String, price: String): Intent {
            return Intent(context, EventDetailsActivity::class.java).apply {
                putExtra(EXTRA_TITLE, title)
                putExtra(EXTRA_PRICE, price)
                putExtra(EXTRA_DESCRIPTION, description)
            }
        }
    }
}