package io.circlecoin.view

import android.app.Activity
import android.app.Fragment
import android.app.FragmentTransaction
import android.os.Bundle
import io.circlecoin.AndroidApplication
import io.circlecoin.internal.di.components.ApplicationComponent
import io.circlecoin.internal.di.modules.ActivityModule
import io.circlecoin.navigation.Navigator
import javax.inject.Inject

/**
 * Base [Activity] class for every Activity in this application.
 */
abstract class BaseActivity : Activity() {

  @Inject internal var navigator: Navigator? = null

  /**
   * Get the Main Application component for dependency injection.
   *
   * @return [ApplicationComponent]
   */
  protected val applicationComponent: ApplicationComponent?
    get() = (application as AndroidApplication).applicationComponent

  /**
   * Get an Activity module for dependency injection.
   *
   * @return [ActivityModule]
   */
  protected val activityModule: ActivityModule
    get() = ActivityModule(this)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    this.applicationComponent!!.inject(this)
  }

  /**
   * Adds a [Fragment] to this activity's layout.
   *
   * @param containerViewId The container view to where add the fragment.
   * @param fragment The fragment to be added.
   */
  protected fun addFragment(containerViewId: Int, fragment: Fragment) {
    val fragmentTransaction = this.fragmentManager.beginTransaction()
    fragmentTransaction.add(containerViewId, fragment)
    fragmentTransaction.commit()
  }
}
