#include <iostream>
#include "Person.h"
#include "Developer.h"

int main() {

    Person p0("Mike", "Ross", 231123);
    Developer d0("Wojciech", "Pruszak", 22, "Software Engineer");

    std::cout << "Hello, World!" << std::endl;
    return 0;
}