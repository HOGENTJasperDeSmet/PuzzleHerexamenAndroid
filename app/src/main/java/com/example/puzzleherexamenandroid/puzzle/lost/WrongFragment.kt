package com.example.puzzleherexamenandroid.puzzle.lost

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.puzzleherexamenandroid.R
import com.example.puzzleherexamenandroid.databinding.FragmentSolvedBinding
import com.example.puzzleherexamenandroid.databinding.FragmentWrongBinding
import com.example.puzzleherexamenandroid.dialogue.DialogueFragmentArgs
import com.example.puzzleherexamenandroid.puzzle.PuzzleFragmentDirections


/**
 * A simple [Fragment] subclass.
 */
class WrongFragment : Fragment() {

    private lateinit var binding : FragmentWrongBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_wrong,
            container,
            false
        )
        val puzzle = DialogueFragmentArgs.fromBundle(arguments!!).selectedPuzzle
        binding.button.setOnClickListener{view: View ->
            this.findNavController().navigate(
                WrongFragmentDirections.actionWrongFragmentToPuzzleFragment(
                    puzzle
                ))
        }
        return binding.root
    }
}