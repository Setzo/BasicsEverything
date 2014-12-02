#include <cstdlib>
#include <stdio.h>
#include <stdarg.h>

void wypWz(int w, int k, int h, int l)
{	
	int w1 = 0;
	for (int x = 0; x < h; x++) {
		for (int i = 0; i < k; i++) {
			for (int j = 0; j < l; j++) {
				printf(".");
			}
			printf("|");			}
		for (int j = 0; j < l; j++) {
			printf(".");
		}
		printf("\n");
	}
	while (w-1!=w1) {
		for (int i = 0; i < k; i++) {
			for (int j = 0; j < l; j++) {
				printf("-");
			}
			printf("+");
		}
		for (int i = 0; i < l; i++) {
			printf("-");
		}
		printf("\n");
		for (int x = 0; x < h; x++) {
			for (int i = 0; i < k; i++) {
				for (int j = 0; j < l; j++) {
					printf(".");
				}
				printf("|");
			}
			for (int j = 0; j < l; j++) {
				printf(".");
			}
			printf("\n");
		}
		w1++;
	}
	for (int i = 0; i < k; i++) {
		for (int j = 0; j < l; j++) {
			printf("-");
		}
		printf("+");
	}
	for (int i = 0; i < l; i++) {
		printf("-");
	}
	printf("\n");
	for (int x = 0; x < h; x++) {
		for (int i = 0; i < k; i++) {
			for (int j = 0; j < l; j++) {
				printf(".");
			}
			printf("|");
		}
		for (int j = 0; j < l; j++) {
			printf(".");
		}
		printf("\n");
	}
}

int main() {
	int t, wi, ko, he, le;
	scanf("%d", &t);
	for (int i = 0; i < t; i++) {
		scanf("%d %d %d %d", &wi, &ko, &he, &le);
		wypWz(wi, ko, he, le);
	}
	system("pause");
	return 0;
}
