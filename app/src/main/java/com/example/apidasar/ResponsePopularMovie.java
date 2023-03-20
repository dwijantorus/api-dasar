package com.example.apidasar;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponsePopularMovie{

	@SerializedName("page")
	private int page;

	@SerializedName("results")
	private List<Movie> results;

	public int getPage(){
		return page;
	}

	public List<Movie> getResults(){
		return results;
	}
}