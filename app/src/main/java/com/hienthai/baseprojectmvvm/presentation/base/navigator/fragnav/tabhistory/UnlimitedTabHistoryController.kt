package com.hienthai.baseprojectmvvm.presentation.base.navigator.fragnav.tabhistory
import com.hienthai.baseprojectmvvm.presentation.base.navigator.fragnav.FragNavPopController
import com.hienthai.baseprojectmvvm.presentation.base.navigator.fragnav.FragNavSwitchController
import java.util.*

class UnlimitedTabHistoryController(fragNavPopController: FragNavPopController,
                                    fragNavSwitchController: FragNavSwitchController
) : CollectionFragNavTabHistoryController(fragNavPopController, fragNavSwitchController) {
    private val tabHistory = Stack<Int>()

    override val collectionSize: Int
        get() = tabHistory.size

    override val andRemoveIndex: Int
        get() {
            tabHistory.pop()
            return tabHistory.pop()
        }

    override var history: ArrayList<Int>
        get() = ArrayList(tabHistory)
        set(history) {
            tabHistory.clear()
            tabHistory.addAll(history)
        }

    override fun switchTab(index: Int) {
        tabHistory.push(index)
    }
}
