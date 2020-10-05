package com.rayray.madlevel3task2

import android.app.PendingIntent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_portal_add.*
import kotlinx.android.synthetic.main.fragment_portals.*

const val REQ_PORTAL_KEY = "req_portal"
const val BUNDLE_PORTAL_KEY_NAME = "bundle_portal"
const val BUNDLE_PORTAL_KEY_URL = "bundle_portal2"

/**
 * @author Raymond Chang
 *
 * This class will be used, to catch the data from the user and pass it to PortalsFragment.
 */
class AddPortalFragment : Fragment() {
    private var customTabHelper: CustomTabHelper = CustomTabHelper()
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
            val anotherCustomTab = CustomTabsIntent.Builder().build()
            val requestCode = 100
            val intent = anotherCustomTab.intent
            intent.setData(Uri.parse("http://www.google.nl"))

            val pendingIntent = PendingIntent.getActivity(
                requireContext(),
                requestCode,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )

            Toast.makeText(context, "heee", Toast.LENGTH_LONG).show()
            val builder = CustomTabsIntent.Builder()
// modify toolbar color
            builder.setToolbarColor(ContextCompat.getColor(requireContext(), R.color.colorPrimary))
// add share button to overflow men
            builder.addDefaultShareMenuItem()
// add menu item to oveflow
            builder.addMenuItem("MENU_ITEM_NAME", pendingIntent)
// show website title
            builder.setShowTitle(true)

            val packageName =
                customTabHelper.getPackageNameToUse(requireContext(), "http://www.google.nl")
            val customTabsIntent = builder.build()
            builder.setStartAnimations(
                requireContext(),
                android.R.anim.fade_in,
                android.R.anim.fade_out
            )

            builder.setExitAnimations(
                requireContext(),
                android.R.anim.fade_in,
                android.R.anim.fade_out
            )
            customTabsIntent.intent.setPackage(packageName)
            customTabsIntent.launchUrl(context, Uri.parse("http://www.google.nl"))


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