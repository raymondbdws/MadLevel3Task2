package com.rayray.madlevel3task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_portal_add.*

const val REQ_PORTAL_KEY = "req_portal"
const val BUNDLE_PORTAL_KEY_NAME = "bundle_portal"
const val BUNDLE_PORTAL_KEY_URL = "bundle_portal2"

/**
 * @author Raymond Chang
 *
 * This class will be used, to catch the data from the user and pass it to PortalsFragment.
 */
class AddPortalFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //xml, view, viewgroup objects
        return inflater.inflate(R.layout.fragment_portal_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnAddPortal.setOnClickListener {
            onAddPortal();
        }
    }

    /**
     * When user pressed on Submit button, then it will send the users input data to Portals fragment.
     */
    private fun onAddPortal() {
        val portalName = etPortalName.text.toString()
        val portalUrl = etPortalUrl.text.toString()

        if (portalName.isNotBlank() && portalUrl.isNotBlank()) {
            setFragmentResult(
                REQ_PORTAL_KEY, bundleOf(
                    Pair(BUNDLE_PORTAL_KEY_NAME, portalName),
                    Pair(BUNDLE_PORTAL_KEY_URL, portalUrl)
                )
            )
            findNavController().popBackStack()
        } else {
            Toast.makeText(activity, R.string.not_valid_portal, Toast.LENGTH_SHORT).show()
        }
    }
}