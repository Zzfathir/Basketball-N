package com.fathirdev.basketball_n

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fathirdev.basketball_n.adapter.NewsAdapter
import com.fathirdev.basketball_n.databinding.FragmentBasketballBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BasketballFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BasketballFragment : Fragment() {
    private var _binding : FragmentBasketballBinding? = null
    private val binding get() = _binding as FragmentBasketballBinding

    private var _viewModel: NewsViewModel? = null
    private val viewModel get() = _viewModel as NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentBasketballBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loadingView.root.visibility = View.VISIBLE
        _viewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        viewModel.getBasketballNews()
        viewModel.basketballNews.observe(viewLifecycleOwner) {
            val data = it.articles

            binding.rvBasketball.apply {
                val mAdapter = NewsAdapter()
                mAdapter.setData(data)
                adapter = mAdapter
                layoutManager = LinearLayoutManager(context)
            }
        }
        binding.loadingView.root.visibility = View.GONE

    }
}