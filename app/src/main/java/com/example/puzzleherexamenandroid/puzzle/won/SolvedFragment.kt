package com.example.puzzleherexamenandroid.puzzle.won

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
import com.example.puzzleherexamenandroid.databinding.FragmentTitleBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 */
class SolvedFragment : Fragment() {

    private lateinit var binding : FragmentSolvedBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_solved,
            container,
            false
        )
        binding.button.setOnClickListener{view: View ->
            Navigation.findNavController(view).navigate(R.id.action_solvedFragment_to_puzzleSelectFragment)

        }
        return binding.root
    }
}