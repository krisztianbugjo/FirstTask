package com.example.task1.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.task1.Adapter.MovieAdapter
import com.example.task1.Model.Movie
import com.example.task1.R

class FirstFragment : Fragment() {
    lateinit var movies: ArrayList<Movie>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_first, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rvContacts = view.findViewById<View>(R.id.rvContacts) as RecyclerView
        movies = createMovieList(20)
        val adapter = MovieAdapter(movies) { movie -> changeFragment(movie) }
        rvContacts.adapter = adapter
        rvContacts.layoutManager = LinearLayoutManager(requireContext())
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