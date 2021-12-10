package dev.bahodir.exchangeratedatabindingapp.fragment.main

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dev.bahodir.exchangeratedatabindingapp.R
import dev.bahodir.exchangeratedatabindingapp.adapter.RVX1
import dev.bahodir.exchangeratedatabindingapp.databinding.FragmentX1Binding
import dev.bahodir.exchangeratedatabindingapp.databinding.X1BackBinding
import dev.bahodir.exchangeratedatabindingapp.retrofit.ApiClient
import dev.bahodir.exchangeratedatabindingapp.retrofit.ApiService
import dev.bahodir.exchangeratedatabindingapp.users.Users
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [X1Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class X1Fragment : Fragment() {
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

    private var _binding: FragmentX1Binding? = null
    private val binding get() = _binding!!
    private lateinit var apiService: ApiService
    private lateinit var rvX1: RVX1
    private lateinit var list: List<Users>
    private val TAG = "X1Fragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_x1, container, false)

        apiService = ApiClient.apiService
        apiService.getUsers().enqueue(object : Callback<List<Users>> {
            override fun onResponse(call: Call<List<Users>>, response: Response<List<Users>>) {
                if (response.isSuccessful) {
                    list = response.body()!!

                    list.forEach {
                        it.image =
                            "https://nbu.uz/local/templates/nbu/images/flags/" + "${it.code}" + ".png"
                    }
                    rvX1 = RVX1(requireContext(), list)
                    binding.vpMain.adapter = rvX1

                    TabLayoutMediator(binding.tabMode, binding.vpMain) {tab, position ->
                        var bind = X1BackBinding.inflate(layoutInflater)
                        tab.customView = bind.root
                        bind.names.text = list!![position].code

                        if (position == 0) {
                            bind.backs.visibility = View.VISIBLE
                            bind.names.setTextColor(Color.WHITE)
                        }
                        else {
                            bind.backs.visibility = View.INVISIBLE
                            bind.names.setTextColor(Color.BLACK)
                        }
                    }.attach()

                    binding.tabMode.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                        override fun onTabSelected(tab: TabLayout.Tab?) {
                            var bind = X1BackBinding.bind(tab?.customView!!)
                            bind.backs.visibility = View.VISIBLE
                            bind.names.setTextColor(Color.WHITE)
                        }

                        override fun onTabUnselected(tab: TabLayout.Tab?) {
                            var bind = X1BackBinding.bind(tab?.customView!!)
                            bind.backs.visibility = View.INVISIBLE
                            bind.names.setTextColor(Color.BLACK)
                        }

                        override fun onTabReselected(tab: TabLayout.Tab?) {

                        }

                    })

                    binding.springDotsIndicator.setViewPager2(binding.vpMain)
                    Log.d(TAG, "onResponse: ${list}")
                } else {
                    Log.d(TAG, "onResponse: Error")
                    list = ArrayList<Users>()
                }
            }

            override fun onFailure(call: Call<List<Users>>, t: Throwable) {
                list = ArrayList<Users>()
            }

        })




        return binding.root
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
         * @return A new instance of fragment X1Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            X1Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}