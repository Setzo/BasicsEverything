#include <algorithm>
#include <cstdlib>
#include <string>
#include <iostream>
#include <cmath>


struct Point {

	std::string name;
	int x, y;
	bool operator<(Point p2) const {
		if (x != p2.x) {
			return x < p2.x;
		}
		else {
			return y < p2.y;
		}
		
	}
};

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

void toString(Point p) {

	std::cout << p.name << " " << p.x << " " << p.y << std::endl;
	return;
}
