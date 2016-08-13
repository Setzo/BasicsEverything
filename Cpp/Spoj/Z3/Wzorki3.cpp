#include <cstdlib>
#include <stdio.h>
#include <stdarg.h>
void wypKr(int k) {
	for (int i = 0; i <= (k * 3); i++) {
		printf("*");
	}
	printf("\n");
}

void wypSr(int k) {
	for (int i = 0; i < k; i++) {
		printf("*..");
	}
	printf("*\n");
}

int main() {
	int t, wi, ko;
	scanf("%d", &t);
	for (int i = 0; i < t ; i++) {
		scanf("%d %d", &wi, &ko);
		for (int j = 0; j < wi; j++) {
			wypKr(ko);
			wypSr(ko);
			wypSr(ko);
		}
		wypKr(ko);
		printf("\n");
	}
	system("pause");
	return 0;
}
