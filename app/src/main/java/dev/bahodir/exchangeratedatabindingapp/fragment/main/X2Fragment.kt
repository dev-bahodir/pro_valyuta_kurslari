package dev.bahodir.exchangeratedatabindingapp.fragment.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dev.bahodir.exchangeratedatabindingapp.R
import dev.bahodir.exchangeratedatabindingapp.adapter.RVX2
import dev.bahodir.exchangeratedatabindingapp.databinding.FragmentMainBinding
import dev.bahodir.exchangeratedatabindingapp.databinding.FragmentX2Binding
import dev.bahodir.exchangeratedatabindingapp.fragment.MainFragment
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
 * Use the [X2Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class X2Fragment : Fragment() {
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
    private var _binding: FragmentX2Binding? = null
    private val  binding get() = _binding!!
    private lateinit var rvX2: RVX2
    private lateinit var apiService: ApiService
    private var list: ArrayList<Users>? = null
    private val TAG = "X2Fragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater,R.layout.fragment_x2, container, false)

        apiService = ApiClient.apiService
        rvX2 = RVX2(requireContext())

        binding.rec.adapter = rvX2

        apiService.getUsers().enqueue(object : Callback<List<Users>> {
            override fun onResponse(call: Call<List<Users>>, response: Response<List<Users>>) {
                if (response.isSuccessful) {
                    list = response.body() as ArrayList<Users>?

                    list?.forEach {
                        it.image = "https://nbu.uz/local/templates/nbu/images/flags/"+"${it.code}"+".png"
                    }
                    rvX2.submitList(list)
                    Log.d(TAG, "onResponse: ${list}")
                }
                else {
                    Log.d(TAG, "onResponse: Error")

                }
            }

            override fun onFailure(call: Call<List<Users>>, t: Throwable) {

            }

        })
        /*binding.lupa.setOnClickListener {
            binding.searchBar.visibility = View.VISIBLE
            binding.xRight.visibility = View.VISIBLE

            binding.menu.visibility = View.INVISIBLE
            binding.name.visibility = View.INVISIBLE
            binding.lupa.visibility = View.INVISIBLE
        }

        binding.xRight.setOnClickListener {
            binding.searchBar.visibility = View.GONE
            binding.xRight.visibility = View.GONE

            binding.menu.visibility = View.VISIBLE
            binding.name.visibility = View.VISIBLE
            binding.lupa.visibility = View.VISIBLE
        }

        binding.searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            @SuppressLint("NotifyDataSetChanged")
            override fun afterTextChanged(s: Editable?) {

            }

        })*/

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
         * @return A new instance of fragment X2Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            X2Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}