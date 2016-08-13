#include <cstdlib>
#include <stdio.h>
#include <stdarg.h>

int main() {
	int t;
	scanf("%d", &t);
	for (int j = 0; j < t; j++){
		int *tablica = NULL, n, l;
		scanf("%d", &n);
		tablica = new int[n];
		for (int i = 0; i < n; i++) {
			scanf("%d", &l);
			tablica[i] = l;
		}
		printf("\n");
		for (int i = n - 1; i >= 0; --i) {
			printf("%d ", tablica[i]);
		}
		delete[] tablica;
		printf("\n");
	}
	system("pause");
}
