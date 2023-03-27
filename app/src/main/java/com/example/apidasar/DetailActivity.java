package com.example.apidasar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apidasar.databinding.ActivityDetailBinding;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    private ActivityDetailBinding binding;
    private ProgressDialog progressDialog;
    boolean isFavorite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent intent = getIntent();
        String movieId = intent.getStringExtra("movie_id");

        getDetailMovie(movieId);
    }

    private void getDetailMovie(String movieId){
        progressDialog = new ProgressDialog(DetailActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        /*Create handle for the RetrofitInstance interface*/
        ApiService service = ApiClient.getRetrofitInstance().create(ApiService.class);
        String apiKey = getResources().getString(R.string.api_key);
        Call<Movie> call = service.getDetailMovie(movieId,apiKey);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                progressDialog.dismiss();
                Movie movie = response.body();
                setDataUI(movie);
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(DetailActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setDataUI(Movie movie) {
        binding.movieName.setText(movie.getTitle());
        binding.movieRating.setText(String.valueOf(movie.getVoteAverageRound()));
        binding.movieDescription.setText(movie.getOverview());
        binding.movieGenre.setText(movie.getGenres());
        binding.movieRatingCount.setText(getString(R.string.rating_count, movie.getVoteCount()));

        String releaseDate = movie.getReleaseDate();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyy-mm-dd");
        Date date;
        try {
            date = simpleDateFormat.parse(releaseDate);
            simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");
            releaseDate = simpleDateFormat.format(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        binding.movieReleaseDate.setText(getString(R.string.release, releaseDate));

        String baseUrlImage = getString(R.string.base_url_image_w500);
        String urlPoster = baseUrlImage + movie.getBackdropPath();
        Picasso.get()
                .load(urlPoster)
                .placeholder(R.drawable.image_placeholder)
                .error(R.drawable.image_placeholder_error)
                .into(binding.moviePoster);

        setFabFavorite();
        setBtnShare(getString(R.string.url_imbd, movie.getImdbId()));
        setBtnBrowser(getString(R.string.url_imbd, movie.getImdbId()));
    }

    private void setFabFavorite(){
        binding.btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFavorite)
                    binding.btnFavorite.setImageResource(R.drawable.ic_unfavorite);
                else
                    binding.btnFavorite.setImageResource(R.drawable.ic_favorite);
                isFavorite = !isFavorite;
            }
        });
    }

    private void setBtnShare(String value){
        binding.btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent2 = new Intent(); intent2.setAction(Intent.ACTION_SEND);
                intent2.setType("text/plain");
                intent2.putExtra(Intent.EXTRA_TEXT, value );
                startActivity(Intent.createChooser(intent2, "Share via"));
            }
        });
    }

    private void setBtnBrowser(String url){
        binding.btnBrowser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }


}