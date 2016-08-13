#include <vector>
#include <algorithm>
#include <cmath>
#include <cstdlib>

int main ()
{

    int x, y, z;
    while (scanf("%d %d %d", &x, &y, &z)!=EOF) {

        int tab[2];
		tab[0] = x;
		tab[1] = y;
		tab[2] = z;
        std::sort(tab, tab+3);
        if (tab[0] + tab[1] <= tab[2]) {
            printf("brak\n");
        } else if(tab[0] * tab[0] + tab[1] * tab[1] == tab[2] * tab[2] ) {
            printf("prostokatny\n");
        } else if(tab[0] * tab[0] + tab[1] * tab[1] > tab[2] * tab[2] ) {
            printf("ostrokatny\n");
        } else {
            printf("rozwartokatny\n");
        }
        
    }
    return 0;
}
