
#include <iostream>
#include <cstdio>
#include <math.h>
#include <Windows.h>

void startCounter();
double getCounter();

double PCFreq = 0.0;
__int64 CounterStart = 0;

int main() {
	 
	double sqrtNum, divider = 0, num, e;
	std::cin >> num;
	std::cin >> e;

	startCounter();
	double startTime = getCounter();

	sqrtNum = sqrt(num);

	while(! (abs(divider - sqrtNum) < e)) {
	
		divider = sqrtNum;
		sqrtNum = (num / divider + divider) / 2;
	}

	double stopTime = getCounter();

	printf("\n\nWynik:       %10.10f\nCzas startu: %.10f\nCzas stopu:  %.10f\nCzas:        %.10f\n\n"
		, sqrtNum, startTime, stopTime, stopTime - startTime);

	system("pause");
	return 0;
}

void startCounter() {

    LARGE_INTEGER li;

    if(!QueryPerformanceFrequency(&li)) {
		printf("Assert\n");
	}

    PCFreq = double(li.QuadPart)/1000.0;

    QueryPerformanceCounter(&li);
    CounterStart = li.QuadPart;
}
double getCounter() {

    LARGE_INTEGER li;
    QueryPerformanceCounter(&li);
    return double(li.QuadPart-CounterStart)/PCFreq;
}
