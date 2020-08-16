package com.example.puzzleherexamenandroid.puzzle.lost

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.puzzleherexamenandroid.R
import com.example.puzzleherexamenandroid.databinding.FragmentWrongBinding
import com.example.puzzleherexamenandroid.dialogue.DialogueFragmentArgs


/**
 * A simple [Fragment] subclass.
 */
class WrongFragment : Fragment() {

    private lateinit var binding : FragmentWrongBinding


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
        binding.tryAgainButton.setOnClickListener{view: View ->
            this.findNavController().navigate(
                WrongFragmentDirections.actionWrongFragmentToPuzzleFragment(
                    puzzle
                ))
        }
        binding.selectAnotherButton.setOnClickListener{view: View ->
            Navigation.findNavController(view).navigate(R.id.action_wrongFragment_to_puzzleSelectFragment)
        }
        return binding.root
    }
}

//