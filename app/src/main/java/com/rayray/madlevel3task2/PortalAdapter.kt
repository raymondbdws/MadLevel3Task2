package com.rayray.madlevel3task2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_portal.view.*

/**
 * @author Raymond Chang
 *
 * This class is an adapter. By using this adapter, we are able to change editTextbox or textview
 * values in de front end. We can also retrieve data from editTextbox
 */
class PortalAdapter(private val PORTALS: List<Portal>) :
    RecyclerView.Adapter<PortalAdapter.ViewHolder>() {

    /**
     * Its an inner class :)
     */
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun databind(portal: Portal) {
            itemView.tvPortalName.text = portal.name
            itemView.tvPortalLink.text = portal.url
        }
    }

    /**
     * Runs when viewholder is being created.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_portal,
                parent, false
            )
        )
    }

    /**
     * bind something
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(PORTALS[position])
    }

    /**
     * Returned total count items
     */
    override fun getItemCount(): Int {
        return PORTALS.size
    }
}