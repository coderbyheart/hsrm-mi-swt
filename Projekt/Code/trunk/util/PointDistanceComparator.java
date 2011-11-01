package util;

import java.util.Comparator;

public class PointDistanceComparator implements Comparator<PointDistance> {
	@Override
	public int compare(PointDistance p1, PointDistance p2) {
		if (p1.getDistance() == p2.getDistance())
			return 0;
		return (p1.getDistance() < p2.getDistance()) ? -1 : 1;
	}
}