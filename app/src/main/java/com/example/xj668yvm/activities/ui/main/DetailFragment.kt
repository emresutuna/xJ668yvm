package com.example.xj668yvm.activities.ui.main

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.xj668yvm.R
import com.example.xj668yvm.activities.models.Characters
import com.example.xj668yvm.activities.util.DetailViewModelFactory
import com.example.xj668yvm.activities.util.ResponseHandler
import com.example.xj668yvm.databinding.DetailFragmentBinding
import com.google.gson.Gson

class DetailFragment : Fragment() {

    companion object {
        fun newInstance(b: Bundle?): DetailFragment {
            val frag = DetailFragment()
            frag.characterId = b?.getInt("id")!!
            frag.arguments = b
            return frag
        }
    }
    private var characterId: Int = 0

    private lateinit var viewModel: DetailViewModel
    private lateinit var binding: DetailFragmentBinding
    private lateinit var v: View
    private var characterData = Characters()
    private var isExpanded = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailFragmentBinding.inflate(inflater, container, false)
        v = binding.root

        viewModel = ViewModelProvider(
            this,
            DetailViewModelFactory(characterId)
        )
            .get(DetailViewModel::class.java)
        viewModel.characterDetail.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is ResponseHandler.Success -> {
                    characterData =
                        Gson().fromJson<Characters>(
                            response.data,
                            Characters::class.java
                        )

                    setData(
                        characterData.image!!,
                        characterData.name!!,
                        "${characterData.status + "," + characterData.species}",
                        characterData.gender!!
                    )
                    setEpisodes(characterData.episode)

                    hideProgress()
                }
                is ResponseHandler.Error -> {
                    hideProgress()
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                }
                is ResponseHandler.Loading -> {
                    showProgress()

                }
            }

        })
        clicks()
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            DetailViewModelFactory(characterId)
        )
            .get(DetailViewModel::class.java)

    }

    private fun hideProgress() {
        binding.loading.visibility = View.GONE
    }

    private fun showProgress() {
        binding.loading.visibility = View.VISIBLE
    }

    private fun setData(
        photo: String,
        characterName: String,
        characterDesc: String,
        gender: String
    ) {
        binding.detailName.text = characterName
        binding.detailDesc.text = characterDesc
        binding.detailGender.text = gender
        Glide.with(requireContext()).load(photo).into(binding.characterPhoto)

    }
    private fun setEpisodes(episodes: ArrayList<String>) {
        if (episodes.size > 0) {
            for (i in episodes.indices) {
                val layoutInflater =
                    requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val v: View = layoutInflater.inflate(R.layout.episodes_item, null, false)
                val detailName: TextView = v.findViewById(R.id.text1)
                detailName.text = episodes[i]
                binding.expandedLayout.addView(v)
            }
        }

    }
    private fun clicks() {
        binding.closeIcon.setOnClickListener {
            requireActivity().finish()
        }
        binding.expandableLayout.setOnClickListener {
            if (isExpanded){
                binding.expandedLayout.visibility=View.VISIBLE
                binding.expandedArea.visibility=View.VISIBLE
                binding.expandIcon.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.ic_close_dropdown))
                isExpanded=false
            }else{
                binding.expandedLayout.visibility=View.GONE
                binding.expandedArea.visibility=View.GONE
                isExpanded=true
                binding.expandIcon.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.ic_resources_nav_bar_back))
            }
        }
    }

}