package com.example.apidasar;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Movie {

	@SerializedName("backdrop_path")
	private String backdropPath;

	@SerializedName("overview")
	private String overview;

	@SerializedName("release_date")
	private String releaseDate;

	@SerializedName("genres")
	private List<Genre> genres;

	@SerializedName("vote_average")
	private double voteAverage;

	@SerializedName("id")
	private int id;

	@SerializedName("imdb_id")
	private String imdbId;

	@SerializedName("title")
	private String title;

	@SerializedName("vote_count")
	private int voteCount;

	@SerializedName("poster_path")
	private String posterPath;

	public String getBackdropPath(){
		return backdropPath;
	}

	public String getOverview(){
		return overview;
	}

	public String getReleaseDate(){
		return releaseDate;
	}

	public String getGenres(){
		String result;
		String[] genresArray = new String[genres.size()];
		for (int i= 0; i<genres.size(); i++){
			genresArray[i] = genres.get(i).getName();
		}
		result = String.join(", ", genresArray);
		return result;
	}

	public double getVoteAverage(){
		return voteAverage;
	}

	public double getVoteAverageRound(){
		int scale = (int) Math.pow(10, 1);
		return (double) Math.round(voteAverage * scale) / scale;
	}

	public int getId(){
		return id;
	}

	public String getImdbId() {
		return imdbId;
	}

	public String getTitle(){
		return title;
	}

	public int getVoteCount(){
		return voteCount;
	}

	public String getPosterPath(){
		return posterPath;
	}
}