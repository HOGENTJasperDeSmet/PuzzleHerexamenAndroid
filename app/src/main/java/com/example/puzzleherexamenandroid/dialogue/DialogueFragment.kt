package com.example.puzzleherexamenandroid.dialogue

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.puzzleherexamenandroid.R
import com.example.puzzleherexamenandroid.databinding.FragmentDialogueBinding
import com.example.puzzleherexamenandroid.databinding.FragmentWrongBinding
import com.example.puzzleherexamenandroid.puzzle.PuzzleViewModel
import com.example.puzzleherexamenandroid.puzzle.select.PuzzleSelectFragmentDirections


/**
 * A simple [Fragment] subclass.
 */
class DialogueFragment : Fragment() {

    private lateinit var binding : FragmentDialogueBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_dialogue,
            container,
            false
        )
        binding.button.setOnClickListener{view: View ->
            this.findNavController().navigate(DialogueFragmentDirections.actionDialogueFragmentToPuzzleFragment(
                binding.viewModel?.selectedPuzzle?.value!!
            ))
        }

        val puzzle = DialogueFragmentArgs.fromBundle(arguments!!).selectedPuzzle
        val viewModelFactory = DialogueViewModelFactory(puzzle)
        binding.viewModel = ViewModelProviders.of(this, viewModelFactory).get(DialogueViewModel::class.java)
        return binding.root
    }
}