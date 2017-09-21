package com.intertec.usernamegenerator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.RandomStringGenerator;

public class UserValidator {

	private 	List<String> existingUsrNames = FileUtils
			.readLines("/Users/erickarley/Documents/GitRepos/IntertecApp/usernamegenerator/usernames.txt");

	public Result checkUserName(String victim) {

		if (StringUtils.isEmpty(victim) || victim.length() < 6) {
			throw new IllegalArgumentException("Username should at least contain 6 characters");
		}			
		
		Result result = new Result();
		if (isInvalid(victim)) {
			result.setStatus(false);
			return result;
		}
		if (exists(victim)) {
			result.setStatus(false);
			List<String> list = new ArrayList<>();
			generateSuggestions(victim, list);
			result.setSuggestions(list);
			return result;
		}
		
		existingUsrNames.add(victim);
		result.setStatus(true);
		return result;
	}

	private void generateSuggestions(String victim, List<String> suggestions) {
		
		RandomStringGenerator generator = new RandomStringGenerator.Builder()
				.withinRange('a', 'z')
				.build();
		String newusername = victim + "_" + generator.generate(6);

		if (!exists(newusername)) {
			suggestions.add(newusername);
		}
		else {
			generateSuggestions(victim, suggestions);
		}
		// Do i still need to generate usernames?
		if (suggestions.size() <14) {
			generateSuggestions(victim, suggestions);
		}
	}

	private boolean isInvalid(String victim) {
		List<String> blacklist = FileUtils
				.readLines("/Users/erickarley/Documents/GitRepos/IntertecApp/usernamegenerator/restrictedwords.txt");

		for (String word : blacklist) {
			if (victim.contains(word)) {
				return true;
			}
		}
		return false;
	}

	private boolean exists(String victim) {
		
		if (existingUsrNames.contains(victim)) {
			return true;
		}
		return false;
	}

}
