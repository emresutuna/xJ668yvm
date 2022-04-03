package com.example.xj668yvm.activities.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.xj668yvm.activities.adapters.CharactersAdapter
import com.example.xj668yvm.activities.api.CharacterService
import com.example.xj668yvm.activities.repository.CharacterServiceRepository
import com.example.xj668yvm.activities.util.DetailViewModelFactory
import com.example.xj668yvm.activities.util.MainViewModelFactory
import com.example.xj668yvm.activities.util.PagingLoadStateAdapter
import com.example.xj668yvm.databinding.MainFragmentBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainFragment : Fragment(), CharactersAdapter.CharacterClickListener {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding
    private lateinit var v: View
    private lateinit var characterServiceRepository: CharacterServiceRepository
    private lateinit var charactersAdapter: CharactersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        v = binding.root
        characterServiceRepository = CharacterServiceRepository()
        charactersAdapter = CharactersAdapter(requireContext())
        binding.characterList.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.characterList.setHasFixedSize(true)
        binding.characterList.adapter = charactersAdapter
        charactersAdapter.withLoadStateHeaderAndFooter(
            header = PagingLoadStateAdapter(this.charactersAdapter),
            footer = PagingLoadStateAdapter(this.charactersAdapter)
        )
        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(characterServiceRepository)
        )
            .get(MainViewModel::class.java)
        lifecycleScope.launch {

            viewModel.characters.collectLatest { pagedData ->
                charactersAdapter.submitData(pagedData)

            }

        }

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(characterServiceRepository)
        )
            .get(MainViewModel::class.java)
    }

    override fun onCharacterClicked(id: Int) {
        Toast.makeText(requireContext(), id.toString(), Toast.LENGTH_SHORT).show()
    }

}