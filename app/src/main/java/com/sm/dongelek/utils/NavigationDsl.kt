package com.sm.dongelek.utils

import androidx.navigation.*
import androidx.navigation.fragment.fragment
import com.sm.dongelek.R
import com.sm.dongelek.ui.main.MainFragment
import com.sm.dongelek.ui.pages.PageFragment
import com.sm.dongelek.ui.main.PostDetailsFragment

object NavigationDsl {

    private fun NavGraphBuilder.globalFragments() = listOf(
        fragment<MainFragment>(R.id.fragment_main) {},
        fragment<PageFragment>(R.id.fragment_profile) {},
        fragment<PostDetailsFragment>(R.id.fragment_post_details) {},
        action(R.id.to_main) { destinationId = R.id.fragment_main },
        action(R.id.to_profile) { destinationId = R.id.fragment_profile },
        action(R.id.to_post) { destinationId = R.id.fragment_post_details }
    )

    fun getNavGraph(navController: NavController, navGraphId: Int): NavGraph {
        return when(navGraphId) {
            R.id.nav_home -> navController.createGraph(navGraphId, R.id.fragment_main) {
                globalFragments()
            }
            else -> navController.createGraph(navGraphId, R.id.fragment_profile) {
                globalFragments()
            }
        }
    }

}