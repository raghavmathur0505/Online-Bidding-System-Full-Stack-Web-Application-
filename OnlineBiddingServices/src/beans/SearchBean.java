package beans;

import java.util.ArrayList;

public class SearchBean {
	private String search;
	private String search2;
	private String search3;
	private ArrayList<ArrayList<String>> searchResult;
	private boolean isValidSearch;
	private ArrayList<ArrayList<String>> searchResult2;
	
	public SearchBean(){
		search="";
		search2="";
		search3="";
		searchResult = null;
		isValidSearch = false;
	}

	public void setSearch(String search){
		this.search=search;
	}
	public void setSearch2(String search2){
		this.search2=search2;
	}
	
	public String getSearch(){
		return search;
	}
	public String getSearch2(){
		return search2;
	}
	
	public String getSearch3() {
		return search3;
	}

	public void setSearch3(String search3) {
		this.search3 = search3;
	}
	
	public void setsearchResult(ArrayList<ArrayList<String>> searchResult) {
		this.searchResult = searchResult;
	}
	public void setsearchResult2(ArrayList<ArrayList<String>> searchResult2) {
		this.searchResult2 = searchResult2;
	}
	public ArrayList<ArrayList<String>> getsearchResult() {
		return searchResult;
	}
	public ArrayList<ArrayList<String>> getsearchResult2() {
		return searchResult;
	}
	public void setValidation(boolean isValidSearch)
	{
		this.isValidSearch = isValidSearch;
	}
	public boolean isValidSearch()
	{
		return isValidSearch;
	}
}
