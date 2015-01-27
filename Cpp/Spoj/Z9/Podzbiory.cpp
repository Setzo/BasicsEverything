#include <algorithm>
#include <stdlib.h>

void findSubset(int x0, int index, int numCount, int limit, int tab[20]);

int main() {

	int t, limit, numCount;
	int tab[20];

	scanf("%d", &t);

	for (int cond = 0; cond < t; cond++) {

		scanf("%d %d", &limit, &numCount);

		for (int i = 0; i < limit; i++) {
			tab[i] = i + 1;
		}

		findSubset(0, 0, numCount, limit, tab);

	}
	system("pause");
	return 0;
}

void findSubset(int x0, int index, int numCount, int limit, int tab[20]) {

	int i, j;

	if (index - x0 + 1 == numCount) {

		if (numCount == 1) {

			for (i = 0; i < limit; i++) {
				printf("%d\n", tab[i]);
			}
		}
		else {

			for (j = index; j < limit; j++) {
				for (i = x0; i < index; i++) {

					printf("%d ", tab[i]);
				}
				printf("%d\n", tab[j]);
			}

			if (x0 != limit - numCount) {
				findSubset(x0 + 1, x0 + 1, numCount, limit, tab);
			}
		}
	}

	else {
		findSubset(x0, index + 1, numCount, limit, tab);
	}
}

/*for (j = index; j < limit; j++) {
				for (i = x0; i < index; i++) {

					printf("%d ", tab[i]);
				}
				printf("%d\n", tab[j]);
	}
w tej czesci kodu jest gdzies blad
*/
			
