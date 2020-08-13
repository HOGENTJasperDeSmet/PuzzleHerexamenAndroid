package com.example.puzzleherexamenandroid.title

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.puzzleherexamenandroid.R
import com.example.puzzleherexamenandroid.databinding.FragmentTitleBinding

/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class TitleFragment : Fragment() {

    private lateinit var binding : FragmentTitleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_title,
            container,
            false
        )
        binding.continueGame.setOnClickListener { view: View ->
            Navigation.findNavController(view).navigate(R.id.action_titleFragment_to_puzzleSelectFragment)
        }



        return binding.root
    }

}