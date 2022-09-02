package com.example.moviedb.Models;

import java.util.List;

public class SearchResponse {

    List<SearchObject> Search = null;
    String TotalResults ="";
    String Response ="";

    public List<SearchObject> getSearch() {
        return Search;
    }

    public void setSearch(List<SearchObject> search) {
        this.Search = search;
    }

    public String getTotalResults() {
        return TotalResults;
    }

    public void setTotalResults(String totalResults) {
        this.TotalResults = totalResults;
    }

    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        this.Response = response;
    }
}
