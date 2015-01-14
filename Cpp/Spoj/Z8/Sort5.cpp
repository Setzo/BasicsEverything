#include <algorithm>
#include <cstdlib>
#include <string>
#include <iostream>
#include <cmath>

#define SIZE 8
#define MP 1001

int toInt(char c);

struct liczba{

	char li[SIZE];

	bool operator<(liczba p) const {
		
		int sum1 = 0, sum2 = 0;

		for (int j = 0; j < strlen(li); j++) {
			sum1 = sum1 + toInt(li[j]);
		}

		for (int j = 0; j < strlen(p.li); j++) {
			sum2 = sum2 + toInt(p.li[j]);
		}

		if (sum1 != sum2) {
			return sum1 < sum2;
		}
		else {
			return strcmp(li, p.li) <0;
		}
	}
};

void toString(liczba p);

int main() {

	int t;
	scanf_s("%d", &t);
	liczba num[MP];

	for (int i = 0; i < t; i++) {
		std::cin >> num[i].li;
	}
	printf("\n");

	std::sort(num, num + t);

	for (int i = 0; i < t; i++) {
		toString(num[i]);
	}
	printf("\n");

	system("pause");
	return 0;
}

void toString(liczba p) {

	std::cout<< p.li << std::endl;
	return;
}

int toInt(char c) {
	return c - 48;
}
