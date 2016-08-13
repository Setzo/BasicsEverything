#include <cstdlib>
#include <stdio.h>
#include <stdarg.h>

void moveWr(int ta[10000], int n, int k);

int main(){
	int n, k;
	int tab[10000];
	scanf("%d %d", &n, &k);
	k = k % n;
	for (int x = 0; x<n; x++) {
		scanf("%d", &tab[x]);
	}
	printf("\n");
	moveWr(tab, n, k);
	return 0;
}

void moveWr(int ta[10000], int n, int k) {

	int tmp[10000];
	for (int i = 0; i<n; i++) {
		tmp[i] = ta[i];
	}

	for (int i = 1; i<n; i++) {
		if (i + k < n) {
			ta[i] = tmp[i+k];
		}
		else if (i + k >= n) {
			ta[i] = tmp[(i + k) - n];
		}
	}
	ta[0] = tmp[k];
	for (int i = 0; i<n; i++) {
		printf("%d ", ta[i]);
	}
}
