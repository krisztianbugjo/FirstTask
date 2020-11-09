package com.example.task1.UI

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.task1.Adapter.MovieAdapter
import com.example.task1.R
import com.example.task1.controller.MovieController.searchMovies
import com.example.task1.controller.MovieController.searchPopularMovies
import com.example.task1.network.Model.Movie
import com.example.task1.network.ServerResponseListener
import kotlinx.android.synthetic.main.fragment_search.search_text
import java.util.Timer
import java.util.TimerTask

class FirstFragment : Fragment() {
    lateinit var movies: List<Movie>
    lateinit var adapter: MovieAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_search, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rvContacts = view.findViewById<View>(R.id.rvContacts) as RecyclerView
        val handler = Handler(Looper.getMainLooper())
        Thread {
            movies = emptyList()
            getPopularMovieList()
            handler.post {
                adapter = MovieAdapter(movies) { movie -> changeFragment(movie) }
                rvContacts.adapter = adapter
                rvContacts.layoutManager = LinearLayoutManager(requireContext())
            }
        }.start()
        initSearchText()
    }

    fun changeFragment(movie: Movie) {
        val fragment = SecondFragment()
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        //Log.i("movieComponent1:", movie.component1().toString())
        val bundle = bundleOf("movieID" to movie.id)
        fragment.arguments = bundle
        transaction.commit()
    }

    fun initSearchText() {
        search_text.addTextChangedListener(
            object : TextWatcher {
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    //no-op
                }

                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
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
                                    if (s.isNotEmpty()) {
                                        getMovieList(s)
                                    } else {
                                        //adapter.clearMovieList()
                                        getPopularMovieList()
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

    fun getMovieList(s: Editable) {
        searchMovies(s, object : ServerResponseListener {
            override fun getResult(results: List<Movie>) {
                adapter.changeMovieList(results)
            }
        })
    }

    fun getPopularMovieList() {
        searchPopularMovies(object : ServerResponseListener {
            override fun getResult(results: List<Movie>) {
                adapter.changeMovieList(results)
            }
        })
    }
}