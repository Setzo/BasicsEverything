#include <cstdlib>
#include <stdio.h>
#include <stdarg.h>
void wypWz(int w, int k)
{
	if (w == 1) {
		if (k == 1) {
			printf("*\n");
		}
		else if (k > 1) {
			for (int i = 0; i < k; i++) {
				printf("*");
			}
			printf("\n");
		}
	}
	else if (w > 1) {
		if (k == 1) {
			for (int i = 0; i < w; i++) {
				printf("*\n");
			}
		}
		else if (k > 1) {
			for (int i = 0; i < k; i++) {
				printf("*");
			}
			printf("\n");
			for (int j = 0; j < w-2; j++) {
				for (int i = 0; i < k; i++) {
					if ((i == 0) || (i + 1 == k)) {
						printf("*");
					}
					else {
						printf(".");
					}
				}
				printf("\n");
			}
			for (int i = 0; i < k; i++) {
				printf("*");
			}
			printf("\n");
		}
	}
}

int main() {
	int t, wi, ko;
	scanf("%d", &t);
	for (int i = 0; i < t; i++) {
		scanf("%d %d", &wi, &ko);
		wypWz(wi, ko);
	}
	system("pause");
	return 0;
}
