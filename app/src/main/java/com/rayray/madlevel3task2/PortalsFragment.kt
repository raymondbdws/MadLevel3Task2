package com.rayray.madlevel3task2

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_portals.*

class PortalsFragment : Fragment(){
    private val portals = arrayListOf<Portal>()
    private val portalAdapter = PortalAdapter(portals)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_portals, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        observeAddPortalResult()
    }

    private fun initViews() {

        //Linear? aanpassen?
        rvPortal.layoutManager =
            GridLayoutManager(context, 2)
        rvPortal.adapter = portalAdapter
    }

    private fun observeAddPortalResult() {
        var name = ""
        var url = ""

        setFragmentResultListener(REQ_PORTAL_KEY) { key, bundle ->
            bundle.getString(BUNDLE_PORTAL_KEY_NAME)?.let {
                name = it
            } ?: Log.e("PortalsFragment", "Request triggered, but empty portal text!")

            bundle.getString(BUNDLE_PORTAL_KEY_URL)?.let {
                url = it
            } ?: Log.e("PortalsFragment", "Request triggered, but empty portal text!")

            val portal = Portal(name, url)
            portals.add(portal)
            portalAdapter.notifyDataSetChanged()
        }


    }
}