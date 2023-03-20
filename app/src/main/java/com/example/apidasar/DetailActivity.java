package com.example.apidasar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Movie;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.apidasar.databinding.ActivityDetailBinding;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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


    }

    private void setDataUI() {

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