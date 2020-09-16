package com.example.task1.UI

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.task1.Adapter.MovieAdapter
import com.example.task1.Model.Movie
import com.example.task1.R
import kotlinx.android.synthetic.main.fragment_search.search_text
import java.util.Timer
import java.util.TimerTask

class FirstFragment : Fragment() {
    lateinit var movies: ArrayList<Movie>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_search, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rvContacts = view.findViewById<View>(R.id.rvContacts) as RecyclerView
        movies = createMovieList(20)
        val adapter = MovieAdapter(movies) { movie -> changeFragment(movie) }
        rvContacts.adapter = adapter
        rvContacts.layoutManager = LinearLayoutManager(requireContext())
        initSearchText()
    }

    fun changeFragment(movie: Movie) {
        val fragment = SecondFragment()
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        val bundle = bundleOf("title" to movie.title)
        fragment.arguments = bundle
        transaction.commit()
    }

    fun initSearchText() {
        search_text.addTextChangedListener(
            object : TextWatcher {
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    //no-op
                }
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                    //no-op
                }
                private var timer: Timer = Timer()
                private val DELAY: Long = 500 // milliseconds
                override fun afterTextChanged(s: Editable) {
                    timer.cancel()
                    timer = Timer()
                    timer.schedule(
                        object : TimerTask() {
                            override fun run() {
                                requireActivity().runOnUiThread(java.lang.Runnable {
                                    if(search_text.text.isNotEmpty()) {
                                        Toast.makeText(context, search_text.text, Toast.LENGTH_SHORT)
                                            .show()
                                    }
                                })
                            }
                        },
                        DELAY
                    )
                }
            }
        )
    }

    private fun createMovieList(numMovies: Int): ArrayList<Movie> {
        var lastMovieId = 0
        val movies = ArrayList<Movie>()
        for (i in 1..numMovies) {
            movies.add(
                Movie(
                    "ID:" + (lastMovieId).toString(),
                    "Movie " + ++lastMovieId
                )
            )
        }
        return movies
    }
}