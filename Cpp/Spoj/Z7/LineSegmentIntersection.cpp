#include <iostream>
#include <algorithm>
#include <cstdlib>

struct Point {

	int x;
	int y;
};

bool switching(Point o1[2], Point o2[2]);

bool isCutting(Point odc00, Point odc01, Point odc10, Point odc11);

int orientation(Point o1, Point pkt, Point o2);

bool onSegment(Point o1, Point pkt, Point o2);

int main()
{

	Point odc1[2];
	Point odc2[2];

	while (true) {

		scanf_s("%d %d %d %d %d %d %d %d",
			&odc1[0].x, &odc1[0].y,
			&odc1[1].x, &odc1[1].y,
			&odc2[0].x, &odc2[0].y,
			&odc2[1].x, &odc2[1].y);

		if (switching(odc1, odc2)) {
			break;
		}

		if (isCutting(odc1[0], odc1[1], odc2[0], odc2[1])) {
			printf("T\n");

		} else {
			printf("N\n");
		}
	}

	return 0;
}

bool switching(Point o1[2], Point o2[2]) {

	if ((o1[0].x == 0) && (o1[0].y == 0)
		&& (o1[1].x == 0) && (o1[1].y == 0)
		&& (o1[0].x == 0) && (o2[0].y == 0)
		&& (o2[1].x == 0) && (o2[1].y == 0)) {
		return true;
	}

	return false;
}

bool isCutting(Point odc00, Point odc01, Point odc10, Point odc11) {

	int o1 = orientation(odc00, odc01, odc10);
	int o2 = orientation(odc00, odc01, odc11);
	int o3 = orientation(odc10, odc11, odc00);
	int o4 = orientation(odc10, odc11, odc01);

	if (o1 != o2 && o3 != o4)
		return true;

	if (o1 == 0 && onSegment(odc00, odc10, odc01)) {
		return true;
	}

	if (o2 == 0 && onSegment(odc00, odc11, odc01)) {
		return true;
	}

	if (o3 == 0 && onSegment(odc10, odc00, odc11)) {
		return true;
	}

	if (o4 == 0 && onSegment(odc10, odc01, odc11)) {
		return true;
	}

	return false;
}

int orientation(Point o1, Point pkt, Point o2) {

	int val = (pkt.y - o1.y) * (o2.x - pkt.x) - (pkt.x - o1.x) * (o2.y - pkt.y);

	if (val == 0) return 0;

	return (val > 0) ? 1 : 2;
}

bool onSegment(Point o1, Point pkt, Point o2) {

	if (pkt.x <= std::max(o1.x, o2.x) && pkt.x >= std::min(o1.x, o2.x) &&
		pkt.y <= std::max(o1.y, o2.y) && pkt.y >= std::min(o1.y, o2.y)) {

		return true;
	}

	return false;
}
