#include <cstdlib>
#include <stdio.h>
#include <stdarg.h>

void wypWz(int w, int k, int s)
{
	int x = 0;
	for (int i = 0; i < (s * 2) * w; i++) {
		for (int j = 0; j < (s*2) * k; j++) {
			x = abs(i - j);
			if ((i + j) % (s * 2) == (s - 1)) {
				printf("/");
			}
			else if (x % (s * 2) == s) {
				printf("\\");
			}
			else {
				printf(".");
			}
		}
		printf("\n");
	}
}

int main() {
	int t, wi, ko, si;
	scanf("%d", &t);
	for (int i = 0; i < t; i++) {
		scanf("%d %d %d", &wi, &ko, &si);
		wypWz(wi, ko, si);
	}
	system("pause");
	return 0;
}
