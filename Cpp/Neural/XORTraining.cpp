/*
 * CreateTrainingData.cpp
 *
 *  Created on: 12 mar 2015
 *      Author: Setzo
 */

#include <iostream>
#include <math.h>
#include <cstdlib>

int main() {

	std::cout<< "topology: 2 4 1" << std::endl;

	for(int i = 0; i < 2000; i++) {

		int in1 = (int)(2.0 * rand() / double(RAND_MAX));
		int in2 = (int)(2.0 * rand() / double(RAND_MAX));

		int t = in1 ^ in2;

		std::cout<< "in: " << in1 << ".0 " << in2 << ".0 " << std::endl;

		std::cout<< "out: " << t << ".0" << std::endl;
	}

	return 0;
}

