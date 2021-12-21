package com.example.layouts.nearby

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.layouts.R
import com.example.layouts.databinding.NearbyFragmentBinding
import nearbyData

class NearbyFragment : Fragment() {

    private lateinit var binding: NearbyFragmentBinding
    private lateinit var itemsAdapter: NearbyItemsAdapter

    private val viewModel: NearbyViewModel by viewModels {
        NearbyViewModel.FACTORY(
            NearbyViewModel.Companion.Params(
                nearbyData,
                nearbyData.first { it.id == requireArguments().get("id") }
            )
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.apply {
            selectedGroupLiveData.observe(viewLifecycleOwner) { data ->
                itemsAdapter.setData(data.items) {
                    binding.recycler.scrollToPosition(0)
                    binding.title.text = data.name
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = NearbyFragmentBinding
        .inflate(inflater, container, false).run {
            binding = this
            toolbar.setNavigationOnClickListener {
                parentFragmentManager.popBackStack()
            }
            dropDown.setOnClickListener {
                //
            }

            itemsAdapter = NearbyItemsAdapter { view ->
                val selectedItem = (view.tag as NearbyItem)
                viewModel.onItemClick(selectedItem.id)
            }
            binding.recycler.adapter = itemsAdapter

            val dividerItemDecoration =
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
//            dividerItemDecoration.setDrawable(ColorDrawable(resources.getColor(R.color.list_item_divider)))
            dividerItemDecoration.setDrawable(AppCompatResources.getDrawable(requireContext(), R.drawable.list_item_divider)!!)
            binding.recycler.addItemDecoration(dividerItemDecoration)

            root
        }
}
