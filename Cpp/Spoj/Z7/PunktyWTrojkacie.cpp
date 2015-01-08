
#include <cstdlib>
#include <stdio.h>
#include <stdarg.h>
#include <cmath>

struct Point {

	int x;
	int y;
};

bool switching(Point s, Point tr[3]);

float area(int x0, int y0, int x1, int y1, int x2, int y2);

char isInside(int x0, int y0, int x1, int y1, int x2, int y2, int x, int y);


int main() {

	Point pkt;
	Point tri[3];
	int t;

	while (true) {
		
		scanf_s("%d %d %d %d %d %d %d %d", 
			&tri[0].x, &tri[0].y, 
			&tri[1].x, &tri[1].y, 
			&tri[2].x, &tri[2].y, 
			&pkt.x, &pkt.y);

		if (switching(pkt, tri)) {
			break;
		}

		printf("%c\n", isInside(tri[0].x, tri[0].y, tri[1].x, tri[1].y, tri[2].x, tri[2].y, pkt.x, pkt.y));

	}

	system("pause");
	return 0;
}

bool switching(Point s, Point tr[3]) {

	if ((s.x == 0) && (s.y == 0)
		&& (tr[0].x == 0) && (tr[0].y == 0)
		&& (tr[1].x == 0) && (tr[1].y == 0)
		&& (tr[2].x == 0) && (tr[2].y == 0)) {
		return true;
	}

	return false;
}

float area(int x0, int y0, int x1, int y1, int x2, int y2) {

	return abs((x0*(y1 - y2) + x1*(y2 - y0) + x2*(y0 - y1)) / 1.0);
}

char isInside(int x0, int y0, int x1, int y1, int x2, int y2, int x, int y) {

	float A = area(x0, y0, x1, y1, x2, y2);

	float A0 = area(x, y, x1, y1, x2, y2);

	float A1 = area(x0, y0, x, y, x2, y2);

	float A2 = area(x0, y0, x1, y1, x, y);

	if ((A0 == 0) || (A1 == 0) || (A2 == 0)) {
		return 'E';
	}
	else if (A == A0 + A1 + A2) {
		return 'I';
	}
	else {
		return 'O';
	}
} /*spoj nie przyjmuje*/
