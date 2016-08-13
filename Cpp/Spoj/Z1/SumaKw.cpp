#include <iostream>
#include <math.h>
using namespace std;

int main()
{
    int a,b,x=0;
    cin>>a>>b;
    for(int i=0; i<=(b-a); i++)
    {
        x=x+pow((a+i), 2);
    }
    cout<<x;
}
