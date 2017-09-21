package com.intertec.usernamegenerator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtils {

	public static List<String> readLines(String fileName) {

		List<String> list = new ArrayList<>();
		//read file into stream, try-with-resources
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

			list = stream.collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;

	}

}
