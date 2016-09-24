#include <iostream>
#include "Person.h"
#include "Developer.h"
#include "status.h"
#include "util.h"

using std::cout;
using std::endl;

int main() {

    Person p0("Mike", "Ross", 231123);
    Developer d0("Wojciech", "Pruszak", 22, "Software Engineer");

    cout << "Hello, World!" << endl;
    Status s = Pending;
    cout << s << endl;

    cout << is_even(2) << endl;
    cout << is_even(3) << endl;
    return 0;
}