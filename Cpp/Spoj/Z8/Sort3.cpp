#include <algorithm>
#include <cstdlib>
#include <string>
#include <iostream>
#include <cmath>

#define SIZE 11
#define MP 1000

struct cena{
	int zloty;
	unsigned int groszy;
};


struct towar{
	cena cen;
	char kod[SIZE];

	bool operator<(towar p) const {

		if (cen.zloty == p.cen.zloty) {
			return cen.groszy < p.cen.groszy;
		}
		else {
			return cen.zloty < p.cen.zloty;
		}
	}
};

void toString(towar p);

int main() {

	int t, n;
	scanf_s("%d", &t);
	for (int cond = 0; cond < t; cond++) {

		scanf_s("%d", &n);
		towar prd[MP];

		for (int i = 0; i < n; i++) {
			std::cin >> prd[i].kod >> prd[i].cen.zloty >> prd[i].cen.groszy;
		}
		printf("\n");

		std::sort(prd, prd + n);

		for (int i = 0; i < n; i++) {
			toString(prd[i]);
		}
		printf("\n");

	}
	system("pause");
	return 0;
}

void toString(towar p) {

	std::cout << p.kod << " " << p.cen.zloty << " " << p.cen.groszy << std::endl;
	return;
}
