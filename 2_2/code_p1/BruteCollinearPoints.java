
import java.util.ArrayList;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
	LineSegment[] lines;
	int size;

	public BruteCollinearPoints(Point[] points) {
		ArrayList<LineSegment> lines_arr_list = new ArrayList<>();
		if (points == null)
			throw new java.lang.IllegalArgumentException("NULL argument");

		for (int x = 0; x < points.length; x++) {
			for (int y = 0; y < points.length; y++) {
				if (x == y)
					continue;
				for (int z = 0; z < points.length; z++) {
					if (y == z || x == z)
						continue;

					for (int w = 0; w < points.length; w++) {
						if (w == z || y == w || x == w)
							continue;

						int x_y = points[x].compareTo(points[y]);
						int y_z = points[y].compareTo(points[z]);
						int z_w = points[z].compareTo(points[w]);

						if (x_y == 0 || y_z == 0 || z_w == 0)
							throw new java.lang.IllegalArgumentException("Repeated Points");
						if (x_y < 0 || y_z < 0 || z_w < 0)
							continue;

						double x_to_y = points[x].slopeTo(points[y]);
                                                double y_to_z = points[y].slopeTo(points[z]);
						double z_to_w = points[z].slopeTo(points[w]);

						if (x_to_y == y_to_z && y_to_z == z_to_w) {
							lines_arr_list.add(new LineSegment(points[x], points[w]));
						}

					}
				}
			}
		}
		size = lines_arr_list.size();
		lines = new LineSegment[size];
		for (int i = 0; i < size; i++) {
			lines[i] = lines_arr_list.get(i);
		}

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
		BruteCollinearPoints collinear = new BruteCollinearPoints(points);
		for (LineSegment segment : collinear.segments()) {
			StdOut.println(segment);
			segment.draw();
		}
		StdDraw.show();
	}
}
