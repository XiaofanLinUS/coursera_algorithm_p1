
import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
	LineSegment[] lines;
	int size;

	public FastCollinearPoints(Point[] points) {
		ArrayList<LineSegment> lines_arr_list = new ArrayList<>();

		for (int i = 0; i < points.length; i++) {
			Arrays.sort(points); // Sort the points in natural order
			Point p = points[i];
			Arrays.sort(points, p.slopeOrder());
			int connect_counts = 1;
			for (int j = 1; j < points.length; j++) {
				if (p.slopeTo(points[j - 1]) == p.slopeTo(points[j])) { // Check if two consecutive points are at the
					// same line
					connect_counts++; // If so, the total number of points at that line increase
				} else {

					if ((connect_counts >= 3) && (p.compareTo(points[j - connect_counts + 1]) < 0))
						/**
						 * If there are four points at the line and the current point is the smallest
						 * point that add this line segement
						 **/
						lines_arr_list.add(new LineSegment(points[j], p));
					connect_counts = 1; // reset

				}

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
		return lines;
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
