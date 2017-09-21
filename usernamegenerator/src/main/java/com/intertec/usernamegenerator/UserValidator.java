package com.intertec.usernamegenerator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.RandomStringGenerator;

public class UserValidator {
	// ***
	// IMPORTANT: To make this app work please update the path
	// ***
	private String path = "/Users/erickarley/Documents/GitRepos/IntertecApp/usernamegenerator";
	private List<String> existingUsrNames = FileUtils.readLines(path + "/usernames.txt");
	private List<String> blacklist = FileUtils.readLines(path + "/restrictedwords.txt");

	public Result checkUserName(String victim) {

		if (StringUtils.isEmpty(victim) || victim.length() < 6) {
			throw new IllegalArgumentException("Username should at least contain 6 characters");
		}

		Result result = new Result();
		//time to check for restricted words
		if (WhichIsTheRestrictedWord(victim) != null) {
			result = prepareSuggestions(victim);
			return result;
		}
		//time to check if username exists
		if (exists(victim)) {
			result = prepareSuggestions(victim);
			return result;
		}

		existingUsrNames.add(victim);
		result.setStatus(true);
		return result;
	}

	//method in charge of creating the suggestions
	private Result prepareSuggestions(String victim) {
		Result result = new Result();
		result.setStatus(false);
		List<String> list = new ArrayList<>();
		generateSuggestions(victim, list);
		// Sorting the list of possible results
		list.sort(null);
		result.setSuggestions(list);
		return result;
	}

	private void generateSuggestions(String victim, List<String> suggestions) {

		RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('a', 'z').build();
		String newusername = victim + "_" + generator.generate(6);
		String restrictedword = null;

		// Verify that suggestion won't include restricted words
		restrictedword = WhichIsTheRestrictedWord(newusername);
		if (restrictedword != null) {
			newusername = newusername.replace(restrictedword, "censored");
		}

		//here we check if the username is new or not and react accordingly
		if (!exists(newusername)) {
			suggestions.add(newusername);
		} else {
			generateSuggestions(victim, suggestions);
		}
		// Do i still need to generate usernames?
		if (suggestions.size() < 14) {
			generateSuggestions(victim, suggestions);
		}
	}

//	//restricted words validator
//	private boolean isInvalid(String victim) {
//		for (String word : blacklist) {
//			if (victim.contains(word)) {
//				return true;
//			}
//		}
//		return false;
//	}

	//restricted word identifier
	private String WhichIsTheRestrictedWord(String victim) {
		for (String word : blacklist) {
			if (victim.contains(word)) {
				return word;
			}
		}
		return null;
	}

	private boolean exists(String victim) {
		if (existingUsrNames.contains(victim)) {
			return true;
		}
		return false;
	}

}
