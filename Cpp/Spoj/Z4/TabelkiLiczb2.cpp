#include <cstdlib>
#include <stdio.h>
#include <stdarg.h>

int main()
{
	int t, w, k, c=0, c1=0;
	int tab[100][100];
	int tmp[100][100];
	scanf("%d", &t);
	for (int i = 0; i < t; i++) {
		scanf("%d %d", &w, &k);
		if (w >= 3 && k <= 100) {
			for (int j = 0; j < w; j++) {
				for (int x = 0; x < k; x++) {
					scanf("%d", &tab[j][x]);
					tmp[j][x] = tab[j][x];
				}
			}

			for (int j = 0; j < w; j++) {
				for (int x = 0; x < k; x++) {
					if (x % 2 != 0) {
						if (j + 1 == w) {
							tab[j][x] = tmp[0][x];
						}
						else if (j + 1 < w) {
							tab[j][x] = tmp[j + 1][x];
						}
					}
					else {
						if (j - 1 < 0) {
							tab[j][x] = tmp[w - 1][x];
						}
						else {
							tab[j][x] = tmp[j - 1][x];
						}
					}
				}
			}

			for (int j = 0; j < k; j++) {
				if (j + 1 == k) {
					if (k % 2 == 0) {
						tab[0][0] = tmp[0][j];
					}
					else {
						tab[0][0] = tmp[w - 1][j];
					}
				}
				else if (j + 1 != k) {
					if (j % 2 == 0) {
						tab[w - 1][j + 1] = tmp[w - 1][j];
					}
					else {
						tab[0][j + 1] = tmp[0][j];
					}
				}
			}
			printf("\n");
			for (int j = 0; j < w; j++) {
				for (int x = 0; x < k; x++) {
					printf("%d ", tab[j][x]);
				}
				printf("\n");
			}
		}
	}
	system("pause");
	return 0;
}
