package com.example.noteappdoc.ui.fragments.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteappdoc.R
import com.example.noteappdoc.databinding.FragmentNoteBinding
import com.example.noteappdoc.ui.adapters.NoteAdapter
import com.example.noteappdoc.ui.fragments.extension.getBackStackData
import com.example.noteappdoc.ui.fragments.models.NoteModel
import com.example.noteappdoc.utils.PreferenceHelper

class NoteFragment : Fragment() {


    private var _binding: FragmentNoteBinding? = null
    private val binding: FragmentNoteBinding get() = _binding!!

    private val noteAdapter = NoteAdapter()
    private val list : ArrayList<NoteModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListeners()
        getData()
    }

    private fun initialize() {
        binding.rvNotes.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = noteAdapter
        }
    }

    private fun setupListeners() = with(binding) {
//        val preferenceHelper = PreferenceHelper()
//        preferenceHelper.unit(requireContext())
//        btnSave.setOnClickListener {
//            val et = edText.text.toString()
//            preferenceHelper.text =et
//            txtSave.text = et
//        }
//        txtSave.text = preferenceHelper.text
        btnAddNote.setOnClickListener {
            findNavController().navigate(R.id.action_noteFragment_to_noteDetailFragment)
        }
    }

    private fun getData() {
        getBackStackData<String>("key"){ data->
            val noteModels = NoteModel(data)
            list.add(noteModels)
            noteAdapter.submitList(list)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}