#include <cstdlib>
#include <stdio.h>
#include <stdarg.h>

int main() {
	int t, n, l, tmp, tmp2, tmp3;
	int tablica[100];
	scanf("%d", &t);
	for (int i = 1; i <= t; i++)
	{
		scanf("%d", &n);
		for (int j = 1; j <= n; j++)
		{
			scanf("%d", &l);
			tablica[j] = l;
		}
		tmp3 = tablica[1];
		tmp = tablica[n - 1];
		tablica[n - 1] = tablica[n];
		for (int k = n - 2; k >= 1; k--)
		{
			tmp2 = tablica[k];
			tablica[k] = tmp;
			tmp = tmp2;
		}
		tablica[n] = tmp3;
		for (int t = 1; t <= n; t++)
		{
			printf("%d ", tablica[t]);
		}
		printf("\n");
	}
	system("PAUSE");
	return 0;
}
