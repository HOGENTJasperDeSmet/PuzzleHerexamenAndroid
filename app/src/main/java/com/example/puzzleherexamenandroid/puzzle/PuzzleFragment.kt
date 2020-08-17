package com.example.puzzleherexamenandroid.puzzle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.puzzleherexamenandroid.R
import com.example.puzzleherexamenandroid.databinding.FragmentPuzzleBinding
import com.example.puzzleherexamenandroid.dialogue.DialogueFragmentArgs

/**
 * A simple [Fragment] subclass.
 */
class PuzzleFragment : Fragment() {

    private lateinit var binding : FragmentPuzzleBinding
    private lateinit var viewModel : PuzzleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_puzzle,
            container,
            false
        )

        val puzzle = DialogueFragmentArgs.fromBundle(arguments!!).selectedPuzzle

        val viewModelFactory = PuzzleViewModelFactory(puzzle)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PuzzleViewModel::class.java)
        binding.viewModel = viewModel
        //initialize the strokemanager (Get the model for ink recognition)
        viewModel.strokeManagerInit()
        //Set the strokemanager to the canvas
        binding.drawingView.setStrokeManager(viewModel.strokeManager)

        //erase the canvas
        binding.erase.setOnClickListener { view: View ->
            binding.drawingView.clear()
            viewModel.resetInput()
        }

        binding.showImage.setOnClickListener {
            if(binding.puzzle.visibility == View.GONE){
                binding.question.visibility = View.GONE
                binding.puzzle.visibility = View.VISIBLE
            } else {
                binding.question.visibility = View.VISIBLE
                binding.puzzle.visibility = View.GONE
            }


        }

        binding.submit.setOnClickListener {
            if(viewModel.correctAnswer()){
                this.findNavController().navigate(
                    PuzzleFragmentDirections.actionPuzzleFragmentToSolvedFragment(
                    binding.viewModel?.selectedPuzzle?.value!!
                ))
            } else {
                this.findNavController().navigate(
                    PuzzleFragmentDirections.actionPuzzleFragmentToWrongFragment(
                        binding.viewModel?.selectedPuzzle?.value!!
                    ))
            }
        }
        return binding.root
    }

}