package com.example.apidasar;

import com.google.gson.annotations.SerializedName;

public class Movie {

	@SerializedName("release_date")
	private String releaseDate;

	@SerializedName("vote_average")
	private Object voteAverage;

	@SerializedName("id")
	private int id;

	@SerializedName("title")
	private String title;

	@SerializedName("poster_path")
	private String posterPath;

	public String getReleaseDate(){
		return releaseDate;
	}

	public Object getVoteAverage(){
		return voteAverage;
	}

	public int getId(){
		return id;
	}

	public String getTitle(){
		return title;
	}

	public String getPosterPath(){
		return posterPath;
	}
}