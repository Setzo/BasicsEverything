#include <algorithm>

int main() {

	while (true) {

		int n;
		bool negate = true;

		scanf_s("%d", &n);

		if (n == 0) {
			break;
		}

		if (n < 0) {
			n = abs(n);
			negate = false;
		}

		char tab[400][400];

		for (int i = 0; i < 2 * n; i++) {
			for (int j = 0; j < 2 * n; j++) {

				tab[i][j] = '.';
			}
		}

		int j = 0;
		for (int i = 0; i < n; i++) {

			for (int x = 1; x + j < n; x++) {
				tab[n + j][n + j + x] = '*';
				tab[n + j + x][n - j - 1] = '*';
				tab[n - j - 1][n - j - 1 - x] = '*';
				tab[n - j - 1 - x][n + j] = '*';
			}

			//POPRZECZNE
			tab[n + j][n - j - 1] = '*';
			tab[n + j][n + j] = '*';
			tab[n - j - 1][n - j - 1] = '*';
			tab[n - j - 1][n + j] = '*';

			j++;
		}

		if (negate) {

			for (int i = 0; i < 2 * n; i++) {
				for (int j = 0; j < 2 * n; j++) {

					printf("%c", tab[i][j]);
				}
				printf("\n");
			}
		}
		else {

			for (int i = 0; i < 2 * n; i++) {
				for (int j = 0; j < 2 * n; j++) {

					printf("%c", tab[j][i]);
				}
				printf("\n");
			}
		}
	}

	return 0;
}
