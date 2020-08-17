package src.niccolaibalica.Sort;

import src.commons.Movie;

public class Comparators{

  protected class TitleComparator implements Comparator<Movie> {

		@Override
		public int compare(Movie o1, Movie o2) {
			return o1.getTitle().compareToIgnoreCase(o2.getTitle());
		}

	}


	protected class VotedComparator implements Comparator<Movie> {

		@Override
		public int compare(Movie o1, Movie o2) {
			return o1.getVotes() - o2.getVotes();
		}

	}


	protected class RecentComparator implements Comparator<Movie> {
		@Override
		public int compare(Movie o1, Movie o2) {
			return o1.getYear().compareTo(o2.getYear());
		}
	}



	private static final<V> void swap (V array[], int i, int j) {
		V temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

}
