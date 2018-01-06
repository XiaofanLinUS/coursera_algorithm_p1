
import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
	private LineSegment[] lines;
	private int size;

	private void exceptionCheck(Point[] points) {

		if (points == null)
			throw new java.lang.IllegalArgumentException("NULL argument");

		for (Point p : points) {
			if (p == null)
				throw new java.lang.IllegalArgumentException("NULL Point");
		}

		for (int i = 0; i < points.length - 1; i++) {
			for (int j = i + 1; j < points.length; j++) {
				if (points[i].compareTo(points[j]) == 0)
					throw new java.lang.IllegalArgumentException("Repeated Point");
			}
		}

	}

	public FastCollinearPoints(Point[] points) {
		exceptionCheck(points);
		if (points.length < 4) {
			size = 0;
			lines = new LineSegment[0];
			return;
		}
		ArrayList<LineSegment> lines_arr_list = new ArrayList<>();

		for (int i = 0; i < points.length; i++) {

			int connect_counts = 1;
			int j;

			Arrays.sort(points); // Sort the points in natural order

			Point p = points[i];

			Arrays.sort(points, p.slopeOrder());

			for (j = 1; j < points.length; j++) {

				// StdOut.println("i: " + i + " " + p);
				// StdOut.println("j: " + j + " " + points[j]);
				// StdOut.println("Counts: " + connect_counts);
				// StdOut.println("Current Slope: " + p.slopeTo(points[j]));
				// StdOut.println("Current Comp : " + p.compareTo(points[j]));
				// StdOut.println("Comp : " + p.compareTo(points[j - connect_counts + 1]));
				if (p.slopeTo(points[j - 1]) != p.slopeTo(points[j])) {

					if ((connect_counts >= 3) && (p.compareTo(points[j - connect_counts]) < 0)) {
						/**
						 * If there are four points at the line and the current point is the smallest
						 * point that add this line segement
						 **/
						lines_arr_list.add(new LineSegment(points[j - 1], p));
						// StdOut.println("ADDED");
					}
					connect_counts = 1; // reset
				} else {
					connect_counts++;
				}

			}

			if ((connect_counts >= 3) && (p.compareTo(points[j - connect_counts]) < 0)) {
				// Don't forget to check when inner loop is over
				lines_arr_list.add(new LineSegment(points[j - 1], p));
				// StdOut.println("ADDED");
			}

		}

		size = lines_arr_list.size();
		lines = new LineSegment[size];
		for (int i = 0; i < size; i++)
			lines[i] = lines_arr_list.get(i);

		lines_arr_list = null;
	}

	public int numberOfSegments() {
		return size;
	}

	public LineSegment[] segments() {
		return lines.clone();
	}

	public static void main(String[] args) {

		// read the n points from a file
		In in = new In(args[0]);
		int n = in.readInt();
		Point[] points = new Point[n];
		for (int i = 0; i < n; i++) {
			int x = in.readInt();
			int y = in.readInt();
			points[i] = new Point(x, y);
		}

		// draw the points
		StdDraw.enableDoubleBuffering();
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		for (Point p : points) {
			p.draw();
		}
		StdDraw.show();

		// print and draw the line segments
		FastCollinearPoints collinear = new FastCollinearPoints(points);
		for (LineSegment segment : collinear.segments()) {
			StdOut.println(segment);
			segment.draw();
		}
		StdDraw.show();
	}
}
