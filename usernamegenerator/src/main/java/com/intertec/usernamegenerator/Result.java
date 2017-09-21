package com.intertec.usernamegenerator;

import java.util.List;

public class Result {

	private boolean status;
	private List<String> suggestions;
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public List<String> getSuggestions() {
		return suggestions;
	}
	public void setSuggestions(List<String> suggestions) {
		this.suggestions = suggestions;
	}
	@Override
	public String toString() {
		return "Result [status=" + status + ", suggestions=" + suggestions + "]";
	}
	
}
