package com.hienthai.vohoctrunghoa.presentation

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry

open class HideAbleFragment : Fragment() {
    /**
     * Lifecycle that based on [onFragmentDisplayed] and [onFragmentHidden] callbacks
     */
//    private val visibleViewLifecycle = SimpleLifecycleOwner()

    private var activityStarted : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        visibleViewLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
    }

    override fun onStart() {
        super.onStart()

        activityStarted = true

        if (!isHidden) {
            onFragmentDisplayed()
        }
    }

    override fun onStop() {
        activityStarted = false
        if (!isHidden) {
            onFragmentHidden()
        }
        super.onStop()
    }

    override fun onDestroy() {
//        visibleViewLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)

        super.onDestroy()
    }

    /**
     * Callback that gets called whenever fragment is no longer visible to the user,
     * either due to whole app not being visible or due to just fragment being invisible
     */
    @CallSuper
    open fun onFragmentHidden() {
//        visibleViewLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
    }

    /**
     * Callback that gets called whenever fragment becomes longer visible to the user,
     * either due to whole app becoming visible or due to just fragment being un-hidden
     */
    @CallSuper
    open fun onFragmentDisplayed() {
//        visibleViewLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_START)
    }

    override fun onHiddenChanged(hidden: Boolean) {
        if (hidden) {
            if (activityStarted) {
                onFragmentHidden()
            }
        } else if (activityStarted) {
            onFragmentDisplayed()
        }
    }

//    protected class SimpleLifecycleOwner : LifecycleOwner {
//        private val lifecycle = LifecycleRegistry(this)
//
//        override fun getLifecycle(): Lifecycle {
//            return lifecycle
//        }
//
//        internal fun handleLifecycleEvent(event: Lifecycle.Event) {
//            lifecycle.handleLifecycleEvent(event)
//        }
//    }
}