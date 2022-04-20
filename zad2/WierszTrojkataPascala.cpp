#include "WierszTrojkataPascala.hpp"

using namespace std;

int silnia(int n){
    if(n < 0){
         // throw std::invalid_argument("Invalid syntax.");
    }
        int suma = 1;
        for (int i = n; i > 1; i--){
            suma *= i;
        }
        return suma;
}

int dwumianNewtona(int n, int k){
    if(n>k){
        // std::__throw_invalid_argument("Blad dwumianu");
    }
    return silnia(n) / (silnia(k) * silnia(n-k));
}

WierszTrojkataPascala::WierszTrojkataPascala(int n){
    this->tablica = new int[n + 1];
    for (int i = 0; i <= n; i++){
        this->tablica[i] = dwumianNewtona(n, i);
    }
}

int WierszTrojkataPascala::wspolczynnik(int m){
        return this->tablica[m];
}

WierszTrojkataPascala::~WierszTrojkataPascala(){
    delete tablica;
}