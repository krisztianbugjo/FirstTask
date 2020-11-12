package com.example.task1.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.task1.R
import com.example.task1.controller.MovieController
import com.example.task1.network.Model.Movie
import com.example.task1.network.ServerResponseListenerForMovie
import kotlinx.android.synthetic.main.fragment_second.second_release_date
import kotlinx.android.synthetic.main.fragment_second.second_title
import kotlinx.android.synthetic.main.fragment_second.second_vote_average

class SecondFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_second, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt("movieID")?.let { movieId ->
            getMoviesById(movieId)
        }
    }

    fun getMoviesById(s: Int) {
        MovieController.searchMoviesById(s, object : ServerResponseListenerForMovie {
            override fun getResult(result: Movie) {
                second_title.text = result.title
                second_release_date.text = result.releaseDate
                second_vote_average.text = result.voteAverage.toString()
            }
        })
    }
}