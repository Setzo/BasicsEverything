#include <iostream>
#include "Person.h"
#include "Developer.h"
#include "status.h"
#include "util.h"

using std::cout;
using std::endl;

template <class T> T max(T &t0, T &t1) {
    return t0 < t1 ? t0 : t1;
}

auto main() -> int {

    Person p0("Mike", "Ross", 231123);
    Person p1("Developer", "Developerski", 456);

    Person pMax = max(p0, p1);

    cout << pMax.getNumber() << endl;

    if (p0 < p1) {
        cout << "less\n";
    } else {
        cout << "more\n";
    }

    Developer d0("Wojciech", "Pruszak", 22, "Software Engineer");

    cout << "Hello, World!" << endl;
    Status s = Pending;
    cout << s << endl;

    cout << is_even(2) << endl;
    cout << is_even(3) << endl;
    return 0;
}