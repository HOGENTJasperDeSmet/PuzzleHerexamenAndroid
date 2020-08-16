package com.example.puzzleherexamenandroid.puzzle.select

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.puzzleherexamenandroid.R
import com.example.puzzleherexamenandroid.databinding.FragmentPuzzleSelectBinding
import com.example.puzzleherexamenandroid.puzzle.select.adapter.PuzzleAdapter


/**
 * A simple [Fragment] subclass.
 */
class PuzzleSelectFragment : Fragment() {

    private lateinit var binding : FragmentPuzzleSelectBinding
    private val viewModel: PuzzleSelectViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProviders.of(this, PuzzleSelectViewModel.Factory(activity.application))
            .get(PuzzleSelectViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_puzzle_select,
            container,
            false
        )
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        val adapter = PuzzleAdapter(PuzzleAdapter.OnClickListener {
            viewModel.displayDialogue(it)
        })
        viewModel.navigateToSelectedPuzzle.observe(viewLifecycleOwner, Observer{
            if(null != it){
                this.findNavController().navigate(PuzzleSelectFragmentDirections.actionPuzzleSelectFragmentToDialogueFragment(it))
                viewModel.displayDialogueComplete()
            }
        })
        binding.puzzleList.adapter = adapter
        viewModel.puzzles.observe(viewLifecycleOwner, Observer {
            it?.let{
                adapter.data = it
            }
        })

        return binding.root
    }
}