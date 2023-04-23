package com.example.projetofinalpart1.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.projetofinalpart1.NavigationManager
import com.example.projetofinalpart1.R
import com.example.projetofinalpart1.databinding.FragmentDetalhesBinding
import com.example.projetofinalpart1.model.Filme
import com.example.projetofinalpart1.model.ObjetoFilme

private const val ARG_FILME_UUID = "ARG_FILME_UUID"

class DetalhesFragment : Fragment() {

    private lateinit var binding: FragmentDetalhesBinding

    private var filmeUuid: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            filmeUuid = it.getString(ARG_FILME_UUID)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_detalhes, container, false)
        binding = FragmentDetalhesBinding.bind(view)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        filmeUuid?.let { uuid ->
            val operation = ObjetoFilme.getOperationById(uuid)
            operation?.let { placeData(it) }
        }
        (binding.voltarButton).setOnClickListener {
            NavigationManager.goToListFragment(requireActivity().supportFragmentManager)
        }
    }


    private fun placeData(ui: Filme) {
        binding.nomeFilmeTextView.text=ui.nomeFilme
        binding.imagemCartazImageView.setImageResource(ui.imagemCartaz)
        binding.nomeCinemaTextView.text=ui.nomeCinema
    }

    companion object {

        @JvmStatic
        fun newInstance(uuid: String) =
            DetalhesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_FILME_UUID, uuid)
                }
            }
    }


}





