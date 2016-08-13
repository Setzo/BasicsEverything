#include <cstdlib>
#include <stdio.h>
#include <stdarg.h>

struct Point {

	int x;
	int y;
};

int main() {

	Point sr;
	int r, t;

	scanf("%d %d %d", &sr.x, &sr.y, &r);
	scanf("%d", &t);

	for (int i = 0; i < t; i++) {
		Point pkt;
		scanf("%d %d", &pkt.x, &pkt.y);

		/*int x = pow(2, 2);
		int rangex = abs(abs(sr.x) - abs(pkt.x));
		int rangey = abs(abs(sr.y) - abs(pkt.y));

		float vec = sqrt(pow(abs(sr.x) - abs(pkt.x), 2) + pow(abs(sr.y) - abs(pkt.y), 2));*/

		float x = (pkt.x - sr.x)*(pkt.x - sr.x);
		float y = (pkt.y - sr.y)*(pkt.y - sr.y);
		float z = r*r;

		if (x + y < z) {
			puts("I");
		}
		else if (x + y > z) {
			puts("O");
		}
		else {
			puts("E");
		}

	}

	system("pause");
	return 0;
}
