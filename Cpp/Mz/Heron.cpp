/*
* Heron.cpp
*
*  Created on: 13 mar 2015
*      Author: Wojciech Pruszak
*/

#include <iostream>
#include <cstdio>
#include <math.h>
#include <Windows.h>

// Ilość testów, jakie wykonamy w celu zmierzenia
// średniej wartości czasu wykonania programu
#define SAMPLE_SIZE 100

// Klasa odpowiedzialna za wszystkie pomiary czasu
class Counter {

public:

	void startCounter();
	double getCounter();

private:

	static double PCFreq;
	static __int64 CounterStart;

};

// Metoda rozpoczynająca odliczanie czasu
void Counter::startCounter() {

	LARGE_INTEGER li;

	if (!QueryPerformanceFrequency(&li)) {
		printf("Assert\n");
	}

	PCFreq = double(li.QuadPart) / 1000.0;

	QueryPerformanceCounter(&li);
	CounterStart = li.QuadPart;
}

// Metoda pobierająca aktualny stan zegara,
// zwraca wartość w double'u
double Counter::getCounter() {

	LARGE_INTEGER li;
	QueryPerformanceCounter(&li);
	return double(li.QuadPart - CounterStart) / PCFreq;
}

double Counter::PCFreq = 0.0;

__int64 Counter::CounterStart = 0;

// Główna metoda programu
int main() {

	// sqrtNum - nasz przyszły wynik
	// num - liczba którą pobierzemy do pierwiastkowania
	// e - przybliżenie
	// n - stopień pierwiastka
	// sigma - średni czas wykonania programu, dla SAMPLE_SIZE wykonań
	double sqrtNum, num, e, n, sigma = 0;

	// cnt - instancja klasy Counter, odpowiadająca za mierzenie pomiarów czasu
	Counter cnt;

	// Wczytywanie startowych wartości
	printf("%22s", "Liczba: "); 				std::cin >> num;
	printf("%22s", "Przyblizenie: "); 			std::cin >> e;
	printf("%22s", "Stopien pierwiastka: "); 	std::cin >> n;

	// Rozpoczęcie odliczania
	cnt.startCounter();

	// Pętla wykonywująca program SAMPLE_SIZE razy
	for (unsigned i = 0; i < SAMPLE_SIZE; ++i) {

		// Pobranie czasu startu
		double startTime = cnt.getCounter();

		sqrtNum = num;

		// Liczenie pierwiastka n-tego stopnia
		// z liczby num, z dokładnością e
		while (abs(num - pow(sqrtNum, n)) > e) {

			sqrtNum = (1.0 / n) * ((n - 1.0) * sqrtNum + (num / (pow(sqrtNum, n - 1.0))));
		}

		// Pobranie czasu stopu
		double stopTime = cnt.getCounter();

		// Dodanie czasu aktualnego przejścia do
		// przyszłego średniego czasu wykonania programu
		sigma += (stopTime - startTime);
	}

	// Podzielenie wartości sumy wszystkich czasów
	// wykonania programu przez ilość wykonań pętli
	sigma /= SAMPLE_SIZE;

	// Wydruk wyniku
	printf("\n\n%22s%.16f\n%22s%.16f\n\n"
		, "Wynik: ", sqrtNum, "Sredni czas: ", sigma);

	system("pause");
	return 0;
}
