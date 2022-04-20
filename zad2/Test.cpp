#include <iostream>
#include <cstdlib>
#include "WierszTrojkataPascala.hpp"

using namespace std;

bool czyLiczba(const char *pStr){
        if(*pStr == '\0')
            return false;

        int licznikMinusow = 0;
        
        while (*pStr){
            switch(*pStr){
                case '-':
                    if(++licznikMinusow > 1)
                        return false;
                    break;
                default:
                    if(*pStr < '0' || *pStr > '9')
                        return false;
            }
            pStr++;
        }
    return true;   
}

int main(int argc, char *argv[]){
    try{
        if(atoi(argv[1]) < 0 || !czyLiczba(argv[1])) throw std::invalid_argument("Nieprawidlowy numer wiersza!");
        WierszTrojkataPascala *Tp = new WierszTrojkataPascala(atoi(argv[1]));
            for (int i = 2; i < argc; i++){
                try{
                    if(atoi(argv[i])< 0 || atoi(argv[i]) > atoi(argv[1])) throw std::invalid_argument("liczba spoza zakresu");
                    if(!czyLiczba(argv[i])) throw std::invalid_argument("niepawidlowa dana");
                    int n = atoi(argv[i]);
                    cout << Tp->wspolczynnik(n) << endl;
                } catch(const std::invalid_argument& e){
                    cout << argv[i] << " - " << e.what() << endl;
                }
            } 
    } catch(const std::invalid_argument& e){
        cout << argv[1] << " - " << e.what() << endl;
    }
}