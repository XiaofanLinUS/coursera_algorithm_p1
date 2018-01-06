
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
                  
			int connect_counts = 1;
                        int j;
                     
			Arrays.sort(points); // Sort the points in natural order
			Point p = points[i];
			Arrays.sort(points, p.slopeOrder());
                        
			for (j = 2; j < points.length; j++) {

				// StdOut.println("i: " + i + " " + points[i]);
				// StdOut.println("j: " + j + " " + points[j]);
				// StdOut.println("Counts: " + connect_counts);
				// StdOut.println("Comp  : " + p.compareTo(points[j - connect_counts + 1]));

				if (p.slopeTo(points[j - 1]) != p.slopeTo(points[j])) { // Check if two
					if ((connect_counts >= 3) && (p.compareTo(points[j - connect_counts]) < 0)) {
						/**
						 * If there are four points at the line and the current point is the smallest
						 * point that add this line segement
						 **/
						lines_arr_list.add(new LineSegment(points[j - 1], p));
					}
					connect_counts = 1; // reset
				} else {
					connect_counts++;
				}

			}
                        
			if ((connect_counts >= 3) && (p.compareTo(points[j - connect_counts]) < 0)) {
				lines_arr_list.add(new LineSegment(points[j - 1], p));
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
