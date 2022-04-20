#pragma once
#include <string>

using namespace std;

class WierszTrojkataPascala : public exception{
    private:
        int *tablica;
    public:
        //metoda
        int wspolczynnik(int m);
        //konstuktor
        WierszTrojkataPascala(int n) ;
        ~WierszTrojkataPascala();
};