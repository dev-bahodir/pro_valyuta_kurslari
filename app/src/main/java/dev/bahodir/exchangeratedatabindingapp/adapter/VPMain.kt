package dev.bahodir.exchangeratedatabindingapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import dev.bahodir.exchangeratedatabindingapp.fragment.main.X1Fragment
import dev.bahodir.exchangeratedatabindingapp.fragment.main.X2Fragment
import dev.bahodir.exchangeratedatabindingapp.fragment.main.X3Fragment

class VPHome(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> X1Fragment()
            1 -> X2Fragment()
            else -> X3Fragment()
        }
    }
}