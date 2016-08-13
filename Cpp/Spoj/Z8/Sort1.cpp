#include <algorithm>
#include <cstdlib>
#include <string>
#include <iostream>
#include <cmath>


struct Point {

	std::string name;
	int x, y;
	bool operator<(Point p2) const {

		return sqrt(pow(x, 2) + pow(y, 2)) < sqrt(pow(p2.x, 2) + pow(p2.y, 2));
	}
};

double vec(int x, int y);

bool acompare(Point x, Point y);

void toString(Point p);

int main() {

	int t, n;
	scanf("%d", &t);
	for (int cond = 0; cond < t; cond++) {

		scanf("%d", &n);
		Point pkt[1001];

		for (int i = 0; i < n; i++) {
			std::cin >> pkt[i].name;
			scanf("%d %d", &pkt[i].x, &pkt[i].y);
		}
		printf("\n");

		std::sort(pkt, pkt + n);

		for (int i = 0; i < n; i++) {
			toString(pkt[i]);
		}
		printf("\n");

	}
	return 0;
}

double vec(int x, int y) {

	return sqrt(pow(x, 2) + pow(y, 2));
}

void toString(Point p) {

	std::cout << p.name << " " << p.x << " " << p.y << std::endl;
	return;
}
