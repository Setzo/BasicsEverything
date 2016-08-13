#include <cstdlib>
#include <stdio.h>
#include <stdarg.h>

void wypWz(int w, int k, int h, int l)
{	
	int w1 = 0;
	do {
		for (int i = 0; i < (k*l) + k + 1; i++) {
			printf("*");
		}
		printf("\n");
		for (int i = 0; i < h; i++) {
			printf("*");
			for (int j = 0; j < k; j++) {
				if (l == 0) {
					printf("*");
				}
				else{
					for (int x = 0; x < l; x++) {
						printf(".");
					}
					printf("*");
				}
			}
			printf("\n");
		}
		w1++;
	} while (w1 != w);
	for (int i = 0; i < (k*l) + k + 1; i++) {
		printf("*");
	}
	printf("\n");
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
