package oy.interact.tira.student;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Comparator;

import oy.interact.tira.factories.HashTableFactory;
import oy.interact.tira.util.Pair;
import oy.interact.tira.util.TIRAKeyedContainer;
import oy.interact.tira.student.Algorithms;

import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;

public class CodeWordsCounter {

	private TIRAKeyedContainer<String, Integer> codeWords;

	public long cumulativeTimeInMilliseconds;

	private static final int MAX_WORD_SIZE = 4096;

	public CodeWordsCounter() {
		codeWords = HashTableFactory.createHashTable();
	}

	public void countWordsinSourceCodeFiles(File inDirectory) throws IOException {

		if (null == codeWords) {
			System.out.println("No implementation for hashtable, doing nothing.");
			return;
		}
		cumulativeTimeInMilliseconds = 0;
		System.out.println("Started counting, please wait...");

		Files.walkFileTree(inDirectory.toPath(), new SimpleFileVisitor<Path>() {
			PathMatcher matcher = FileSystems.getDefault()
					.getPathMatcher("glob:*.{c,h,cc,cpp,hpp,java,swift,py,html,css,js}");

			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
				if (file != null && matcher.matches(file.getFileName())) {
					try {
						countWordsFrom(file.toFile());
					} catch (OutOfMemoryError | IOException e) {
						e.printStackTrace();
						return FileVisitResult.TERMINATE;
					}
				}
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFileFailed(Path file, IOException e) {
				return FileVisitResult.CONTINUE;
			}

		});
	}

	private void countWordsFrom(File file) throws OutOfMemoryError, IOException {
		String content = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
		// Characters of a single word as Unicode codepoints.
		int[] wordChars = new int[MAX_WORD_SIZE];
		// Index used to fill wordChars array from the string in the file.
		int codeWordIndex = 0;
		System.out.println("File: " + file.getAbsolutePath());
		long start = System.currentTimeMillis();
		for (int index = 0; index < content.length(); index++) {

			int codePoint = content.codePointAt(index);
			if (Character.isLetter(codePoint)) {
				wordChars[codeWordIndex++] = codePoint;
			} else {
				if (codeWordIndex >= 2) {
					String word = new String(wordChars, 0, codeWordIndex);
					word = word.toLowerCase();
					Integer wordCount = codeWords.get(word);
					if (wordCount == null)
						codeWords.add(word, 1);
					else
						codeWords.add(word, wordCount + 1);
				}
				codeWordIndex = 0;
			}
		}
		cumulativeTimeInMilliseconds += System.currentTimeMillis() - start;
	}

	Comparator<Pair<String, Integer>> myPairComparator = new Comparator<>() {
		@Override
		public int compare(Pair<String, Integer> pair1, Pair<String, Integer> pair2){
			return pair2.getValue().compareTo(pair1.getValue());
		}
	};

	@SuppressWarnings("unchecked")
	public Pair<String, Integer>[] topCodeWords(int topCount) throws Exception {
		if (null == codeWords) {
			Pair<String, Integer>[] result = new Pair[1];
			result[0] = new Pair<>("Hashtable not implemented yet", 0);
			return result;
		}
		// STUDENTS: TODO: Implement this pseudocode to get the top words sorted by
		// frequency of use from hash table.
		Pair<String, Integer>[] array = codeWords.toArray();
		// 2. Use your fast sort algorithm to sort the array of pairs by word count,
		// descending (!) order,
		// so that the word that is most frequent, is the first in the array.
		Algorithms.fastSort(array, myPairComparator);
		if(array.length < topCount)
			topCount = array.length;
		Pair<String, Integer>[] resultArray = (Pair<String, Integer>[]) new Pair[topCount];
		for(int i = 0; i < topCount; i++){
			resultArray[i] = array[i];
		}
		return resultArray;
	}

}
