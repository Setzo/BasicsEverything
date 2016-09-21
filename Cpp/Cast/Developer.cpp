
#include "Developer.h"
#include <iostream>

Developer::Developer(std::string firstname,
                     std::string lastname,
                     int number,
                     std::string title) : Person(firstname, lastname, number), title(title) {

    std::cout << "Creating: " << this->title << std::endl;
}

Developer::~Developer() {
    std::cout << "Destroying: " << this->title << std::endl;
}