#include <algorithm>
#include <cstdlib>
#include <cmath>
#include <iostream>

int main() {

	int t;
	scanf_s("%d", &t);

	int x[101];
	int y[101];
	int z[101];

	//long perm = t *(t - 1) / 2;
	
	//double* res = (double*) malloc (perm * sizeof(double*));
	double res[100000];

	for (int i = 0; i < t; i++) {

		std::cin >> x[i] >> y[i] >> z[i];
	}
	printf("\n");
	int iter = 0;

	for (int i = 0; i < t; i++) {
		for (int j = i + 1; j < t; j++) {

			res[iter] = sqrt(((x[i] - x[j]) *(x[i] - x[j])) + ((y[i] - y[j]) *(y[i] - y[j])) + ((z[i] - z[j]) *(z[i] - z[j])));
			if (res[iter] != 0) {
				iter++;
			}
		}
	}

	std::sort(res, res + iter);

	for (int i = 0; i < iter; i++) {
		if (res[i] != 0.0) {
			printf("%.2f\n", res[i]);
		}
	}

	system("pause");
	return 0;
}

