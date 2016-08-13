/*
 * CreateTrainingData.cpp
 *
 *  Created on: 12 mar 2015
 *      Author: Setzo
 */

#include <iostream>
#include <math.h>
#include <cstdlib>

//******************		XOR		******************

void xorTrainingSamples() {

	std::cout<< "topology: 2 4 1" << std::endl;

	for(int i = 0; i < 2000; i++) {

		int in0 = (int)(2.0 * rand() / double(RAND_MAX));
		int in1 = (int)(2.0 * rand() / double(RAND_MAX));

		int t = in0 ^ in1;

		std::cout<< "in: " << in0 << ".0 " << in1 << ".0 " << std::endl;

		std::cout<< "out: " << t << ".0 " << std::endl;
	}
}

//******************		ADDING		******************

void addTrainingSamples() {

	std::cout<< "topology: 4 4 4 4" << std::endl;

	for(int i = 0; i < 10000; i++) {

		int in0[2];
		in0[0] = (int)(2.0 * rand() / double(RAND_MAX));
		in0[1] = (int)(2.0 * rand() / double(RAND_MAX));

		int in1[2];
		in1[0] = (int)(2.0 * rand() / double(RAND_MAX));
		in1[1] = (int)(2.0 * rand() / double(RAND_MAX));

		std::cout<< "in: " << in0[0] << ".0 " << in0[1] << ".0 " << in1[0] << ".0 " << in1[1] << ".0 " << std::endl;

		int out[4];

		if(in0[0] == 0 && in0[1] == 0 && in1[0] == 0 && in1[1] == 0) {	// 0 0 0 0

			out[0] = 0;
			out[1] = 0;
			out[2] = 0;
			out[3] = 0;
		}

		if((in0[0] == 1 && in0[1] == 0 && in1[0] == 0 && in1[1] == 1)	// 1 0 0 1
				|| (in0[0] == 0 && in0[1] == 1 && in1[0] == 1 && in1[1] == 0)) {	//0 1 1 0

			out[0] = 0;
			out[1] = 0;
			out[2] = 1;
			out[3] = 1;
		}

		if((in0[0] == 0 && in0[1] == 0 && in1[0] == 0 && in1[1] == 1)	// 0 0 0 1
				|| (in0[0] == 0 && in0[1] == 1 && in1[0] == 0 && in1[1] == 0)) { // 0 1 0 0

			out[0] = 0;
			out[1] = 0;
			out[2] = 0;
			out[3] = 1;
		}

		if((in0[0] == 0 && in0[1] == 0 && in1[0] == 1 && in1[1] == 1)	// 0 0 1 1
				|| (in0[0] == 1 && in0[1] == 1 && in1[0] == 0 && in1[1] == 0)) { // 1 1 0 0

			out[0] = 0;
			out[1] = 0;
			out[2] = 1;
			out[3] = 1;
		}

		if((in0[0] == 0 && in0[1] == 0 && in1[0] == 1 && in1[1] == 0)	// 0 0 1 0
				|| (in0[0] == 1 && in0[1] == 0 && in1[0] == 0 && in1[1] == 0)){	//1 0 0 0

			out[0] = 0;
			out[1] = 0;
			out[2] = 1;
			out[3] = 0;
		}

		if(in0[0] == 0 && in0[1] == 1 && in1[0] == 0 && in1[1] == 1) { // 0 1 0 1

			out[0] = 0;
			out[1] = 0;
			out[2] = 1;
			out[3] = 0;
		}

		if((in0[0] == 0 && in0[1] == 1 && in1[0] == 1 && in1[1] == 1)	 // 0 1 1 1
				|| (in0[0] == 1 && in0[1] == 1 && in1[0] == 0 && in1[1] == 1)){  // 1 1 0 1

			out[0] = 0;
			out[1] = 1;
			out[2] = 0;
			out[3] = 0;
		}

		if(in0[0] == 1 && in0[1] == 0 && in1[0] == 1 && in1[1] == 0) {	// 1 0 1 0

			out[0] = 0;
			out[1] = 1;
			out[2] = 0;
			out[3] = 0;
		}

		if((in0[0] == 1 && in0[1] == 0 && in1[0] == 1 && in1[1] == 1)	// 1 0 1 1
				|| (in0[0] == 1 && in0[1] == 1 && in1[0] == 1 && in1[1] == 0)) {	// 1 1 1 0

			out[0] = 0;
			out[1] = 1;
			out[2] = 0;
			out[3] = 1;
		}

		if(in0[0] == 1 && in0[1] == 1 && in1[0] == 1 && in1[1] == 1) { // 1 1 1 1

			out[0] = 0;
			out[1] = 1;
			out[2] = 1;
			out[3] = 0;
		}

		std::cout<< "out: " << out[0] << ".0 " << out[1] << ".0 " << out[2] << ".0 " << out[3] << ".0 " << std::endl;
	}
}

//******************		MAIN		******************

int main() {

	addTrainingSamples();

	return 0;
}
