#include <iostream>
#include <cstdio>
#include <math.h>
#include <Windows.h>

void startCounter();
double getCounter();

double PCFreq = 0.0;
__int64 CounterStart = 0;

int main() {

	double sqrtNum, divider = 0, num, e, n;

	std::cout << "Liczba:              "; std::cin >> num;
	std::cout << "Przyblizenie:        "; std::cin >> e;
	std::cout << "Stopien pierwiastka: "; std::cin >> n;

	startCounter();
	double startTime = getCounter();

	sqrtNum = num;

	while (abs(num - pow(sqrtNum, n)) > e) {
		
		sqrtNum = (1.0 / n) * ((n - 1.0) * sqrtNum + (num / (pow(sqrtNum, n - 1.0))));
	}

	double stopTime = getCounter();

	printf("\n\nWynik:       %10.10f\nCzas startu: %.10f\nCzas stopu:  %.10f\nCzas:        %.10f\n\n"
		, sqrtNum, startTime, stopTime, stopTime - startTime);

	system("pause");
	return 0;
}

void startCounter() {

	LARGE_INTEGER li;

	if (!QueryPerformanceFrequency(&li)) {
		printf("Assert\n");
	}

	PCFreq = double(li.QuadPart) / 1000.0;

	QueryPerformanceCounter(&li);
	CounterStart = li.QuadPart;
}

double getCounter() {

	LARGE_INTEGER li;
	QueryPerformanceCounter(&li);
	return double(li.QuadPart - CounterStart) / PCFreq;
}
