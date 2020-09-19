package com.rayray.madlevel3task2

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_portals.*

/**
 * @author Raymond Chang
 *
 * We use this class to handle data from AddPortalFragment and display its values on PortalsFragment.
 */
class PortalsFragment : Fragment() {
    private val portals = arrayListOf<Portal>()
    private val portalAdapter = PortalAdapter(portals)

    /**
     * runs when PortalsFragment view is being created.
     */
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

    /**
     * Initialize view
     */
    private fun initViews() {

        rvPortal.layoutManager =
            GridLayoutManager(context, 2)
        rvPortal.adapter = portalAdapter
    }

    /**
     * Observe Results. When data has been received, it will put the value in local variables.
     *
     * There are 2 difference variables: name and url. At the end, we will create a portalobject
     * and put it in the portals list.
     */
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