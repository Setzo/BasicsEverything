/*
 * Heap.cpp
 *
 *  Created on: 4 maj 2015
 *      Author: Wojciech Pruszak
 */

#include <iostream>
#include <string>
#include <vector>
#include <stdlib.h>
#include <stdio.h>
#include <sstream>
#include <windows.h>
#include <ctime>

// Zdefiniowanie typu Heap, który jest po prostu wektorem
// dodatnich int'ów.
// *** W kodzie, gdy miałem na myśli kopiec starałem się używać	***
// *** typu Heap. Gdy natomiast miałem na myśli sam wektor,		***
// *** który jeszcze nie jest kopcem używałem					***
// *** std::vector<unsigned int>.								***
typedef std::vector<unsigned int> Heap;

// Maksymalna liczba - 1, jaką może przyjąć element,
// który wczytujemy do wektora do posortowania.
// np. dla NUM_RANGE 10000, maksymalnie element może
// przyjąć wartość 9999 (10000 - 1). Zakres wartośći
// elementów wektora wyniesie zatem : <0; 9999>.
#define NUM_RANGE 10000

// Liczba prób dla metod insert() i
// getTopHeapValue().
#define SAMPLE_SIZE 100

// *******************		COUNTER		*******************

// Klasa odpowiadająca za mierzenie czasu.
class Counter {

public:

	void startCounter();
	double getCounter();

private:

	static double PCFreq;
	static __int64 CounterStart;

};

// Metoda rozpoczynająca odliczanie czasu.
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
// zwraca wartość w double'u.
double Counter::getCounter() {

	LARGE_INTEGER li;
	QueryPerformanceCounter(&li);
	return double(li.QuadPart - CounterStart) / PCFreq;
}

double Counter::PCFreq = 0.0;

__int64 Counter::CounterStart = 0;

//******************		HEAP_OPS		******************

// Klasa odpowiadająca za wszystkie operacje na kopcach.
class HeapOps {

public:

	HeapOps(Heap heap);
	HeapOps(Heap heap, bool isMaxHeap);
	HeapOps(Heap heap, bool isMaxHeap, bool isSortedMinToMax);

	void rebuildHeap(unsigned int index);
	void buildHeap();
	unsigned int getTopHeapValue();
	void insert(unsigned int valueToInsert);
	std::vector<unsigned int> heapSort();

	std::string toString() const;
	std::string toString(std::vector<unsigned int> numVector) const;
	void showHeap() const;
	void showHeap(std::vector<unsigned int> numVector) const;

	void setHeap(Heap heap);
	Heap getHeap() const;

	static bool isMaxHeap;			// Default - True
	static bool isSortedMinToMax;	// Default - True

private:

	Heap heap;

	unsigned int rootIndex(unsigned int index) const;
	unsigned int leftChildIndex(unsigned int index) const;
	unsigned int rightChildIndex(unsigned int index) const;

	void swap(unsigned int from, unsigned int to);
	void swap(unsigned int from, unsigned int to, std::vector<unsigned int> &numVector);

};

// Konstruktor definiujący :
// -- startowy wektor w kopcu
HeapOps::HeapOps(Heap heap) {

	this->heap = heap;
}

// Konstruktor definiujący :
// -- startowy wektor w kopcu
// -- typ kopca (max lub min)
HeapOps::HeapOps(Heap heap, bool isMaxHeap) {

	this->heap = heap;
	this->isMaxHeap = isMaxHeap;
}

// Konstruktor definiujący :
// -- startowy wektor w kopcu
// -- typ kopca (max lub min)
// -- jak mamy sortować kopiec (od najmniejszych do największych
// 	  lub odwrotnie)
HeapOps::HeapOps(Heap heap, bool isMaxHeap, bool isSortedMinToMax) {

	this->heap = heap;
	this->isMaxHeap = isMaxHeap;
	this->isSortedMinToMax = isSortedMinToMax;
}

// Funkcja odbudowująca własności kopca.
void HeapOps::rebuildHeap(unsigned int index) {

	// Lewy potomek naszego kopca[index].
	unsigned int left = this->leftChildIndex(index);
	// Prawy potomek naszego kopca[index].
	unsigned int right = this->rightChildIndex(index);

	// Dla kopca typu max [tmp] to indeks w kopcu, dla którego nasz kopiec
	// przyjmuje największą wartość wśród szukanych.
	// Dla kopca typu min [tmp] to indeks w kopcu, dla którego nasz kopiec
	// przyjmuje najmniejszą wartość wśród szukanych.
	unsigned int tmp = 0;

	if((left < this->heap.size() && this->heap[index] < this->heap[left] && this->isMaxHeap)
			|| (left < this->heap.size() && this->heap[index] > this->heap[left] && !this->isMaxHeap)) {

		tmp = left;

	} else {
		tmp = index;
	}

	if((right < this->heap.size() && this->heap[tmp] < this->heap[right] && this->isMaxHeap)
			|| (right < this->heap.size() && this->heap[tmp] > this->heap[right] && !this->isMaxHeap)) {

		tmp = right;
	}

	// Jeżeli wykryliśmy nieprawidłowość i którykolwiek z potomków
	// naszego kopca[index] ma większą wagę, niż sam kopiec[index],
	// to zamień je miejscami i rozwiń gałąź kopca, która wydaje się nieprawidłowa
	// naprawiając w niej właśności kopca.
	if(tmp != index) {

		this->swap(tmp, index);
		index = tmp;

		this->rebuildHeap(index);
	}
}

// Budowanie kopca wg naszych parametrów.
// isMaxHeap = true  ---> zbuduj kopiec typu max
// isMaxHeap = false ---> zbuduj kopiec typu min
void HeapOps::buildHeap() {

	unsigned int heapSize = this->heap.size();

	for(unsigned int currentIteration = heapSize / 2; currentIteration > 0; currentIteration--) {

		this->rebuildHeap(currentIteration);
	}

	this->rebuildHeap(0);
}

// Metoda zwracająca element na samym szczycie kopca.
// W przypadku kopca typu max będzie to największy element
// w całym kopcu. Jeżeli zaś kopiec jest typu min, to będzie
// to najmniejszy element w całym kopcu.
// Poza tym metoda zmniejsza wielkość kopca o 1 (usuwa
// ona element na samym szczycie i go zwraca).
unsigned int HeapOps::getTopHeapValue() {

	unsigned int top = this->heap[0];
	this->heap[0] = this->heap[this->heap.size() - 1];
	this->heap.erase(this->heap.begin());
	this->rebuildHeap(0);

	return top;
}

// Metoda wstawiająca element [valueToInsert] do naszego
// kopca.
// valueToInsert - element do wstawienia do kopca
void HeapOps::insert(unsigned int valueToInsert) {

	this->heap.push_back(valueToInsert);
	unsigned int tmp = this->heap.size() - 1;

	// Prosty algorytm do poruszania się po kopcu, jako grafie.
	// -- dla kopca typu min -- im mniejsza wartość elementu, tym większa waga
	// -- dla kopca typu max -- im większa wartość elementu, tym większa waga
	// Porównuje on w wartości heap[tmp] i heap[rodzic[tmp]]
	// Jeżeli heap[tmp] ma większą wagę, niż heap[rodzic[tmp]], to zamienia
	// je miejscami.
	if(this->isMaxHeap) {

		while(tmp > 0 && this->heap[tmp] > this->heap[this->rootIndex(tmp)]) {

			this->swap(tmp, this->rootIndex(tmp));
			tmp = this->rootIndex(tmp);
		}

	} else {

		while(tmp > 0 && this->heap[tmp] < this->heap[this->rootIndex(tmp)]) {

			this->swap(tmp, this->rootIndex(tmp));
			tmp = this->rootIndex(tmp);
		}
	}
}

// Implementacja sortowania przez kopcowanie.
std::vector<unsigned int> HeapOps::heapSort() {

	// Zbudowanie kopca, by można go sortować.
	this->buildHeap();

	// Przyszły posortowany wektor (już nie kopiec).
	std::vector<unsigned int> sorted;

	// Pętla sortująca przez kopcowanie.
	for(unsigned int tmp = this->heap.size() - 1; tmp >= 0; tmp--) {

		this->swap(0, tmp);
		sorted.push_back(this->heap.back());
		this->heap.pop_back();
		this->rebuildHeap(0);

		if(tmp == 0) {
			break;
		}
	}

	// Jeżeli kopiec :
	// -- był typu max i miał zostać posortowany od elementów
	//    największych do najmniejszych
	// -- był typu min i miał zostać posortowany od elementów
	//    najmniejszych do największych
	// to odwróć wektor [sorted], bo jest posortowany odwrotnie
	// do tego jak być powinien wg naszych wymogów.
	if((this->isMaxHeap && this->isSortedMinToMax)
			|| (!this->isMaxHeap && !this->isSortedMinToMax)) {

		unsigned int end = sorted.size() - 1;

		for(unsigned int begin = 0; begin <= end; begin++, end--) {

			this->swap(begin, end, sorted);
		}
	}

	return sorted;
}

// Metoda zwracająca łańcuch znaków, który zawiera wszystkie elementy
// naszego aktualnego kopca w kolejności w jakiej są one w kopcu.
std::string HeapOps::toString() const {

	std::stringstream toReturn;

	// Przejście przez elementy kopca i zapisanie ich w stringstream'ie.
	for(unsigned int currentIteration = 0; currentIteration < this->heap.size(); currentIteration++) {

		toReturn << this->heap[currentIteration] << ", ";
	}

	return toReturn.str();
}

// Metoda zwracająca łańcuch znaków, który zawiera wszystkie elementy
// wektora [numVector] w kolejności w jakiej są one w wektorze.
// numVector - wektor, którego elementy chcemy zapisać w string'u
std::string HeapOps::toString(std::vector<unsigned int> numVector) const {

	std::stringstream toReturn;

	// Przejście przez elementy wektora i zapisanie ich w stringstream'ie.
	for(unsigned int currentIteration = 0; currentIteration < numVector.size(); currentIteration++) {

		toReturn << numVector[currentIteration] << ", ";
	}

	return toReturn.str();
}

// Metoda wypisująca aktualne elementy kopca.
void HeapOps::showHeap() const {

	std::cout << this->toString() << std::endl;
}

// Metoda wypisująca elementy wektora [numVector].
void HeapOps::showHeap(std::vector<unsigned int> numVector) const {

	std::cout << this->toString(numVector) << std::endl;
}

// Metoda ustawiająca kopiec na podany wektor.
// heap - nowy kopiec
void HeapOps::setHeap(Heap heap) {

	this->heap = heap;
}

// Metoda zwracająca kopiec.
Heap HeapOps::getHeap() const {

	return this->heap;
}

// Zmienna definiująca czy nasz kopiec jest kopcem typu max, czy typu min.
// Gdy przyjmuje wartość [true] nasz kopiec jest kopcem typu max.
// Gdy przyjmuje wartość [false] nasz kopiec jest kopcem typu min.
// np.
// isMaxHeap = true		heap = {7134, 5511, 2672, 4213, 586, 206, 793, 5174, 2257, 5659}
// buildHeap() = 7134, 5659, 5511, 5174, 2672, 206, 793, 4213, 2257, 586
//
// isMaxHeap = false	heap = {9134, 6066, 2511, 2661, 8189, 9084, 911, 1280, 8059, 1989}
// buildHeap() = 911, 1280, 1989, 2661, 2511, 9084, 9134, 6066, 8059, 8189
bool HeapOps::isMaxHeap = true;

// Zmienna, która definiuje jak kopiec ma być posortowany.
// Domyślnie przyjmuje wartość [true], zatem po wykonaniu metody
// heapSort() kopiec będzie zawsze posortowany od elementów najmniejszych
// do największych. Gdy ta zmienna przyjmie wartość [false] kopiec zacznie być
// sortowany od elementów największych do najmniejszych.
// np.
// isSortedMinToMax = true		heap = {4, 3, 1, 2}
// heapSort() = 1, 2, 3, 4
//
// isSortedMinToMax = false		heap = {4, 3, 1, 2}
// heapSort() = 4, 3, 2, 1
bool HeapOps::isSortedMinToMax = true;

// Funkcja zwracająca indeks rodzica w kopcu dla indeksu [index].
// index - indeks w kopcu, dla którego szukamy indeksu rodzica
unsigned int HeapOps::rootIndex(unsigned int index) const {

	return index / 2;
}

// Funkcja zwracająca indeks lewego potomka w kopcu dla indeksu [index].
// index - indeks w kopcu, dla którego szukamy indeksu lewego potomka
unsigned int HeapOps::leftChildIndex(unsigned int index) const {

	return index * 2;
}

// Funkcja zwracająca indeks prawego potomka w kopcu dla indeksu [index].
// index - indeks w kopcu, dla którego szukamy indeksu prawego potomka
unsigned int HeapOps::rightChildIndex(unsigned int index) const {

	return index * 2 + 1;
}

// Funkcja która zamienia wartości heap[from] i heap[to] miejscami.
// from - indeks elementu do zamienienia miejscem w kopcu
// to - indeks elementu do zamienienia miejscem w kopcu
void HeapOps::swap(unsigned int from, unsigned int to) {

	unsigned int tmp = this->heap[from];
	this->heap[from] = this->heap[to];
	this->heap[to] = tmp;
}

// Funkcja która zamienia dwie wartości wektora [numVector] miejscami.
// from - indeks elementu do zamienienia miejscem w wektorze
// to - indeks elementu do zamienienia miejscem w wektorze
void HeapOps::swap(unsigned int from, unsigned int to, std::vector<unsigned int> &numVector) {

	unsigned int tmp = numVector[from];
	numVector[from] = numVector[to];
	numVector[to] = tmp;
}

//******************		MAIN		******************

// Funkcja, która zwróci wektor o [numElements] losowych elementach.
std::vector<unsigned int> randomizedVector(unsigned int numElements) {

	std::vector<unsigned int> numVector;

	// Pętla wcztująca [numElements] losowych elementów do wektora [numVector].
	for(unsigned currentElement = 0; currentElement < numElements; currentElement++) {

		numVector.push_back(rand() % NUM_RANGE);
	}

	return numVector;
}

// Główna metoda programu.
int main() {

	// Po wykonaniu tej instrukcji, funkcja rand() zacznie zawsze
	// zwracać nam wartości losowe.
	srand(time(0));

	// Instancja klasy Counter, odpowiadająca za licznie czasu.
	Counter cnt;

	// Liczba elementów ciągu do wygenerowania i posortowania.
	unsigned int numElements;

	// Przyszłe wyniki pomiarów czasu.
	double buildHeapTime;
	double getTopHeapValueTime;
	double insertTime;
	double heapSortTime;

	// Wczytanie liczby elementów do wygenerowania i posortowania.
	printf("Podaj liczbę elementów ciągu: ");
	std::cin >> numElements;

	// Rozpoczęcie pracy zegara.
	cnt.startCounter();

	// Stworzenie instancji klasy HeapOps, odpowiadającej za wszystkie
	// operacje na kopcach.
	HeapOps heapOps(randomizedVector(numElements));

	// Zbudowanie kopca i zmierzenie czasu wykonia metody buildHeap().
	buildHeapTime = cnt.getCounter();
		heapOps.buildHeap();
	buildHeapTime = cnt.getCounter() - buildHeapTime;

	// Dodawanie elementów do zbudowanego kopca o [numElements] elementach,
	// oraz zmierzenie czasu wykonania metody insert(). Jako iż metoda ta wykonuje się
	// strasznie szybko, wykonamy ją SAMPLE_SIZE razy.
	insertTime = cnt.getCounter();

		for(unsigned int currentIteration = 0; currentIteration < SAMPLE_SIZE; currentIteration++) {

			heapOps.insert(rand() % NUM_RANGE);
		}

	insertTime = cnt.getCounter() - insertTime;

	// Zmniejszenie wielkości kopca z [numElements + SAMPLE_SIZE], do [numElements],
	// tak by wszystkie pomiary czasu były jak najdokładniejsze, dla poszczególnej
	// liczby elementów ciągu.
	for(unsigned int currentIteration = 0; currentIteration < SAMPLE_SIZE; currentIteration++) {

		heapOps.getTopHeapValue();
	}

	// Zabranie wartości z korzenia kopca (wartości z indeksem 0) i zmierzenie
	// czasu wykonania metody getTopHeapValue(). Jako iż metoda ta wykonuje się
	// strasznie szybko, wykonamy ją SAMPLE_SIZE razy.
	getTopHeapValueTime = cnt.getCounter();

		for(unsigned int currentIteration = 0; currentIteration < SAMPLE_SIZE
				&& currentIteration < numElements; currentIteration++) {

			heapOps.getTopHeapValue();
		}

	getTopHeapValueTime = cnt.getCounter() - getTopHeapValueTime;

	// Zamiana starego kopca na nowy, jeszcze nie sprowadzony do kopca i nieposortowany
	// wektor.
	heapOps.setHeap(randomizedVector(numElements));

	// Sortowanie przez kopcowanie oraz pomiar czasu dla metody
	// heapSort(), odpowiadającej za sortowanie kopca.
	heapSortTime = cnt.getCounter();
		heapOps.heapSort();
	heapSortTime = cnt.getCounter() - heapSortTime;

	// *******************		TIMERS		*******************

	// Wypisanie wszystkich czasów, dla poszczególnych sortowań.
	printf("\n%26s%d\n%27s\n%27s%-.10f\n%27s%-.10f\n%27s%-.10f\n%27s%-.10f\n",
			"Liczba elementów w ciągu : ", numElements,
			"Czasy : ",
			"buildHeap : ", 			buildHeapTime,
			"insert : ", 				insertTime,
			"getTopHeapValue : ", 	getTopHeapValueTime,
			"heapSort : ", 				heapSortTime);

	return 0;
}
