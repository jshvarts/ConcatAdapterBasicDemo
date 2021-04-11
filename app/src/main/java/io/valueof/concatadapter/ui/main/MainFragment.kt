package io.valueof.concatadapter.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ConcatAdapter
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import io.valueof.concatadapter.R
import io.valueof.concatadapter.databinding.MainFragmentBinding
import io.valueof.concatadapter.ui.main.model.Featured
import io.valueof.concatadapter.ui.main.model.Regular
import kotlinx.coroutines.flow.collect
import timber.log.Timber

class MainFragment : Fragment(R.layout.main_fragment) {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val binding by viewBinding(MainFragmentBinding::bind)

    private lateinit var viewModel: MainViewModel

    private val featuredItemAdapter = FeaturedItemAdapter(this::onItemSelected)
    private val regularItemAdapter = RegularItemAdapter(this::onItemSelected)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val concatAdapter = ConcatAdapter(
            featuredItemAdapter,
            regularItemAdapter
        )
        binding.recyclerView.adapter = concatAdapter

        lifecycleScope.launchWhenResumed {
            viewModel.itemList.collect { itemList ->
                itemList.filterIsInstance<Featured>().takeIf { it.isNotEmpty() }?.let {
                    Timber.d("featured item list $it")
                    featuredItemAdapter.submitList(it)
                }
            }
        }

        lifecycleScope.launchWhenResumed {
            viewModel.itemList.collect { itemList ->
                itemList.filterIsInstance<Regular>().takeIf { it.isNotEmpty() }?.let {
                    Timber.d("regular item list $it")
                    regularItemAdapter.submitList(it)
                }
            }
        }
    }

    private fun onItemSelected(id: String) {
        viewModel.onItemSelected(id)
    }
}