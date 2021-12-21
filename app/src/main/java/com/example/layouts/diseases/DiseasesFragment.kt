package com.example.layouts.diseases

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.layouts.R
import com.example.layouts.databinding.DiseasesFragmentBinding

class DiseasesFragment : Fragment() {

    private lateinit var binding: DiseasesFragmentBinding
    private lateinit var adapter: DiseaseItemsAdapter

    private val viewModel: DiseasesViewModel by viewModels {
        DiseasesViewModel.FACTORY(
            DiseasesViewModel.Companion.Params(
                listOf(
                    DiseaseItem("absent", name = "Absent"),
                    DiseaseItem("bradycardia", name = "Bradycardia"),
                    DiseaseItem("tachycardia", name = "Tachycardia"),
                    DiseaseItem("arrhythmia", name = "Arrhythmia"),
                )
            )
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.apply {
            itemsLiveData.observe(viewLifecycleOwner) { data ->
                adapter.setData(data, data.first()) {
                    binding.recycler.scrollToPosition(0)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = DiseasesFragmentBinding
        .inflate(inflater, container, false).run {
            binding = this
            toolbar.setNavigationOnClickListener {
                parentFragmentManager.popBackStack()
            }

            adapter = DiseaseItemsAdapter { view ->
                val selectedItem = (view.tag as DiseaseItem)
                viewModel.onItemClick(selectedItem.id)
                adapter.select(selectedItem)
            }
            binding.recycler.adapter = adapter

            val dividerItemDecoration =
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
//            dividerItemDecoration.setDrawable(ColorDrawable(resources.getColor(R.color.list_item_divider)))
            dividerItemDecoration.setDrawable(AppCompatResources.getDrawable(requireContext(), R.drawable.list_item_divider)!!)
            binding.recycler.addItemDecoration(dividerItemDecoration)

            root
        }
}
