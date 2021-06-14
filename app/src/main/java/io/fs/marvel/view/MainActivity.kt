package io.fs.marvel.view

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import io.fs.marvel.R
import io.fs.marvel.model.state.MainModel
import io.fs.marvel.vm.MainActivityViewModel
import kotlinx.android.synthetic.main.view_main_activity.*
import org.fs.architecture.mvi.core.AbstractActivity

class MainActivity: AbstractActivity<MainModel, MainActivityViewModel>(), MainActivityView {

    override val layoutRes: Int
        get() = R.layout.view_main_activity

    override fun setUp(state: Bundle?) {

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.viewNavHostFragment) as NavHostFragment
        val controller = navHostFragment.navController

        val configuration = AppBarConfiguration(controller.graph)
        viewToolbar.setupWithNavController(controller, configuration)
    }

    override fun render(model: MainModel) = Unit // not used
}