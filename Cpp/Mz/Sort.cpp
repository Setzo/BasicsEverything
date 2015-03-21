/*
 * Sort.cpp
 *
 *  Created on: 21 mar 2015
 *      Author: Setzo
 */

#include <iostream>
#include <stdlib.h>
#include <stdio.h>
#include <windows.h>
#include <vector>
#include <ctime>
#include <math.h>

// Maksymalna liczba - 1, jaką może przyjąć element,
// który wczytujemy do wektora do posortowania.
// np. dla NUM_RANGE 10000, maksymalnie element może
// przyjąć wartość 9999 (10000 - 1). Zakres wartośći
// elementów wektora wyniesie zatem : <0; 9999>.
#define NUM_RANGE 10000

// *******************		COUNTER		*******************

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

// *******************		SORT_SELECT		*******************

// Klasa odpowiadająca za sortowanie algorytmem
// sortowania przez selekcję.
class SortSelect {

public:

	void selectSort(std::vector<unsigned> &numVector);

private:

	unsigned findMinimum(std::vector<unsigned> numVector, unsigned currentElement) const;

};

// Metoda, która posortuje wektor numVector zgodnie
// z zasadami sortowania przez selekcję.
void SortSelect::selectSort(std::vector<unsigned> &numVector) {

	for(unsigned currentIteration = 0; currentIteration < numVector.size(); currentIteration++) {

		unsigned tmp;
		unsigned min = SortSelect::findMinimum(numVector, currentIteration);

		if(min != currentIteration) {

			tmp = numVector[currentIteration];
			numVector[currentIteration] = numVector[min];
			numVector[min] = tmp;
		}
	}
}

// Metoda znajdująca pozycje najmniejszego elementu w wektorze.
unsigned SortSelect::findMinimum(std::vector<unsigned> numVector, unsigned currentElement) const {

	unsigned min = currentElement;

	for(unsigned currentIteration = currentElement + 1; currentIteration < numVector.size(); currentIteration++) {

		if(numVector[currentIteration] < numVector[min]) {
			min = currentIteration;
		}
	}

	return min;
}

// *******************		SORT_INSERT		*******************

// Klasa odpowiadająca za sortowanie algorytmem
// sortowania przez wstawianie.
class SortInsert {

public:

	void insertSort(std::vector<unsigned> &numVector);

};

// Metoda, która posortuje wektor numVector zgodnie
// z zasadami sortowania przez wstawianie.
void SortInsert::insertSort(std::vector<unsigned> &numVector) {

	for(unsigned currentElement = 1; currentElement < numVector.size(); currentElement++) {

		unsigned tmp = numVector[currentElement];
		int previousElementIndex = currentElement - 1;

		while(previousElementIndex >= 0 && numVector[previousElementIndex] > tmp) {

			numVector[previousElementIndex + 1] = numVector[previousElementIndex];
			previousElementIndex--;
		}

		numVector[previousElementIndex + 1] = tmp;
	}
}

// *******************		SORT_MERGE		*******************

// Klasa odpowiadająca za sortowanie algorytmem
// sortowania przez scalanie.
class SortMerge {

public:

	void mergeSort(std::vector<unsigned> &numVector, int p, int r);

private:

	void merge(std::vector<unsigned> &numVector, int p, int q, int r);

};

// Metoda, która posortuje wektor numVector zgodnie
// z zasadami sortowania przez scalanie.
// numVector - wektor do posortowania
// p - indeks elementu, od którego mamy zacząć sortować
// r - indeks elementu, do którego mamy posortować wektor numVector
void SortMerge::mergeSort(std::vector<unsigned> &numVector, int p, int r) {

	if(p < r) {

		// q - środkowy element wektora numVector.
		int q = static_cast<int> ((p + r) / 2);

		// Rekurencyjne sortowanie wektora od elementu
		// p (element początkowy), do elementu
		// q (element środkowy)
		SortMerge::mergeSort(numVector, p, q);
		// Rekurencyjne sortowanie wektora od elementu
		// q + 1 (element środkowy + 1), do elementu
		// r (element końcowy)
		SortMerge::mergeSort(numVector, q + 1, r);

		// Scalanie i sortowanie powstałych połówek
		// wektorów w jeden wektor.
		SortMerge::merge(numVector, p, q, r);
	}
}

// Metoda scalająca i sortująca wektor.
// p - indeks elementu, od którego mamy zacząć sortować
// q - indeks elementu środkowego wektora
// r - indeks elementu, do którego mamy posortować wektor numVector
void SortMerge::merge(std::vector<unsigned> &numVector, int p, int q, int r) {

	unsigned tmp[numVector.size()];

	int i = p;
	int j = q + 1;
	int k = 0;

	while (i <= q && j <= r) {

		if (numVector[j] < numVector[i]) {

			tmp[k] = numVector[j];
			j++;

		} else {

			tmp[k] = numVector[i];
			i++;
		}
		k++;
	}

	if (i <= q) {

		while (i <= q) {

			tmp[k] = numVector[i];
			i++;
			k++;
		}
	} else {

		while (j <= r) {

			tmp[k] = numVector[j];
			j++;
			k++;
		}
	}

	for (i = 0; i <= r - p; i++) {

		numVector[p + i] = tmp[i];
	}

	free(tmp);
}

// *******************		MAIN		*******************

// Główna metoda programu
int main() {

	// Instancja klasy Counter, odpowiadająca za licznie czasu.
	Counter cnt;

	// Instancje klas odpowiadających za sortowanie, z użyciem
	// wybranych algorytmów.
	SortSelect selSort;
	SortInsert insSort;
	SortMerge merSort;

	// Przyszłe wyniki pomiarów czasu.
	double selSortStart;
	double selSortEnd;
	double insSortStart;
	double insSortEnd;
	double merSortStart;
	double merSortEnd;

	// Liczba elementów ciągu do wygenerowania i posortowania.
	unsigned numElements;
	// Wektor, który będzie przechowywał elementy do posortowania.
	std::vector<unsigned> numVector;

	// Wczytanie liczby elementów do wygenerowania i posortowania.
	printf("Podaj liczbe elementow ciagu: ");
	std::cin>> numElements;

	// Po wykonaniu tej instrukcji, funkcja rand() zacznie zawsze
	// zwracać nam wartości losowe.
	srand(time(NULL));

	// Rozpoczęcie pracy zegara.
	cnt.startCounter();

	// *******************		SORT_SELECT		*******************

	printf("\n%20s\nElementy na wejściu:\n", "SORT_SELECT:");

	// Pętla wcztująca [numElements] losowych elementów do wektora [numVector].
	for(unsigned currentElement = 0; currentElement < numElements; currentElement++) {

		// Wypisywanie nieposortowanych elementów wektora.
		numVector.push_back(rand() % NUM_RANGE);
		printf("%d, ", numVector.back());
	}

	// Pobranie czasu startu, dla sortowania przez selekcję.
	selSortStart = cnt.getCounter();
	// Wywołanie funkcji sortującej przez selekcję.
	selSort.selectSort(numVector);
	// Pobranie czasu stopu, dla sortowania przez selekcję.
	selSortEnd = cnt.getCounter();

	printf("\nElementy na wyjściu:\n");

	for(unsigned currentElement = 0; currentElement < numElements; currentElement++) {

		// Wypisywanie już posortowanych elementów wektora.
		printf("%d, ", numVector[currentElement]);
	}

	// *******************		SORT_INSERT		*******************

	// Usunięcie wszystkich elementów wektora.
	numVector.clear();

	printf("\n\n%20s\nElementy na wejściu:\n", "SORT_INSERT:");

	// Pętla wcztująca [numElements] losowych elementów do wektora [numVector].
	for(unsigned currentElement = 0; currentElement < numElements; currentElement++) {

		// Wypisywanie nieposortowanych elementów wektora.
		numVector.push_back(rand() % NUM_RANGE);
		printf("%d, ", numVector.back());
	}

	// Pobranie czasu startu, dla sortowania przez wstawianie.
	insSortStart = cnt.getCounter();
	// Wywołanie funkcji sortującej przez wstawianie.
	insSort.insertSort(numVector);
	// Pobranie czasu stopu, dla sortowania przez wstawianie.
	insSortEnd = cnt.getCounter();

	printf("\nElementy na wyjściu:\n");

	for(unsigned currentElement = 0; currentElement < numElements; currentElement++) {

		// Wypisywanie już posortowanych elementów wektora.
		printf("%d, ", numVector[currentElement]);
	}

	// *******************		SORT_MERGE		*******************

	// Usunięcie wszystkich elementów wektora.
	numVector.clear();

	printf("\n\n%20s\nElementy na wejściu:\n", "SORT_MERGE:");

	// Pętla wcztująca [numElements] losowych elementów do wektora [numVector].
	for(unsigned currentElement = 0; currentElement < numElements; currentElement++) {

		// Wypisywanie nieposortowanych elementów wektora.
		numVector.push_back(rand() % NUM_RANGE);
		printf("%d, ", numVector.back());
	}

	// Pobranie czasu startu, dla sortowania przez scalanie.
	merSortStart = cnt.getCounter();
	// Wywołanie funkcji sortującej przez scalanie.
	merSort.mergeSort(numVector, 0, numVector.size() - 1);
	// Pobranie czasu stopu, dla sortowania przez scalanie.
	merSortEnd = cnt.getCounter();

	printf("\nElementy na wyjściu:\n");

	for(unsigned currentElement = 0; currentElement < numElements; currentElement++) {

		// Wypisywanie już posortowanych elementów wektora.
		printf("%d, ", numVector[currentElement]);
	}

	// Usunięcie wszystkich elementów wektora.
	numVector.clear();

	// *******************		TIMERS		*******************

	// Wypisanie wszystkich czasów, dla poszczególnych sortowań.
	printf("\n\n%26s%d\n%25s\n%26s%-.10f\n%26s%-.10f\n%26s%-.10f\n",
			"Liczba elementów w ciągu: ", numElements,
			"Czasy:",
			"Sort_Select: ", selSortEnd - selSortStart,
			"Sort_Insert: ", insSortEnd - insSortStart,
			"Sort_Merge : ", merSortEnd - merSortStart);

	return 0;
}
