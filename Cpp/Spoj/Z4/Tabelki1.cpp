#include<cstdio>
#include<cstdlib>

int main()
{
	int t, w, k;
	int tab[101][101];
	scanf_s("%d", &t); 
	for (int i = 0; i < t; i++) {
		scanf_s("%d %d", &w, &k);
		for (int j = 0; j < w; j++) {
			for (int x = 0; x < k; x++) {
				scanf_s("%d", &tab[j][x]);
			}
		}
		int tmp = tab[0][0];

		for (int i = 1; i < k; i++) tab[0][i - 1] = tab[0][i];
		for (int i = 1; i < w; i++) tab[i - 1][k - 1] = tab[i][k - 1];
		for (int i = 1; i < k; i++) tab[w - 1][k - i] = tab[w - 1][k - i - 1];
		for (int i = 1; i < w - 1; i++) tab[w - i][0] = tab[w - i - 1][0];
		tab[w - 2][0] = tmp;

		for (int j = 0; j < w; j++) {
			for (int x = 0; x < k; x++) {
				printf("%d ", tab[j][x]);
			}
			printf("\n");
		}
	}
	system("pause");
	return 0;
}
         
