#include <cstdlib>
#include <stdio.h>
#include <stdarg.h>

int main(){
	int t, w, k, s, z;
	scanf("%d", &t);
	for (int a = 0; a < t; a++){
		scanf("%d %d %d", &w, &k, &s);
		z = ++s;
		for (int i = 1; i <= s * w; i++){
			for (int j = 1; j <= (s * k + 1); j++){
				if ((i - 1) % s == 0 || (j - 1) % s == 0)
				{
					printf("*");
				}
				else if (((i - 1) / s + (j - 1) / s) % 2 == 0)
				{
					if ((i - 1) % s == (j - 1) % s){
						printf("\\");
					}
					else  printf(".");
				}
				else
				{
					if (j % s == (z + 1) % s){
						printf("/");
					}
					else  printf(".");
				}
			}
			printf("\n");
			z--;
			if (z == 0) {
				z = s;
			}
		}
		for (int j = 1; j <= (s * k + 1); j++) {
			printf("*");
		}
		printf("\n");
	}
	system("pause");
	return 0;
}
