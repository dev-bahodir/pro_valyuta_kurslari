package dev.bahodir.exchangeratedatabindingapp.fragment

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import dev.bahodir.exchangeratedatabindingapp.R
import dev.bahodir.exchangeratedatabindingapp.adapter.VPHome
import dev.bahodir.exchangeratedatabindingapp.databinding.ActivityMainBinding
import dev.bahodir.exchangeratedatabindingapp.databinding.FragmentMainBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    private var _binding: FragmentMainBinding? = null
    private val  binding get() = _binding!!
    private lateinit var vpHome: VPHome

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)

        vpHome = VPHome(requireActivity())
        binding.view.adapter = vpHome

        binding.view.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when(position) {
                    0 -> binding.navView.selectedItemId = R.id.main
                    1 -> binding.navView.selectedItemId = R.id.all
                    2 -> binding.navView.selectedItemId = R.id.calc
                }
            }
        })
        binding.navView.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.main -> binding.view.currentItem = 0
                R.id.all -> binding.view.currentItem = 1
                R.id.calc -> binding.view.currentItem = 2
            }
            true
        }

        binding.menu.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        binding.view.isUserInputEnabled = false

        binding.navigationView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_ulashish -> {
                    binding.drawerLayout.closeDrawers()
                    val appPackageName: String = requireContext().packageName
                    val sendIntent = Intent()
                    sendIntent.action = Intent.ACTION_SEND
                    sendIntent.putExtra(Intent.EXTRA_TEXT,
                        "Check out the App at: https://play.google.com/store/apps/details?id=$appPackageName")
                    sendIntent.type = "text/plain"
                    this.startActivity(sendIntent)
                }
                R.id.nav_haqida -> {
                    binding.drawerLayout.closeDrawers()
                    val builder = AlertDialog.Builder(requireContext())
                    builder.setTitle("Information about EXCHANGE RATE program")
                    builder.setMessage("This program was created to convert exchange rates")
                    builder.show()
                    binding.drawerLayout.closeDrawers()
                }
            }
            true
        }

        return binding.root
    }
    fun show() {
        binding.lupa.visibility = View.VISIBLE
    }

    fun hide() {
        binding.lupa.visibility = View.INVISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}