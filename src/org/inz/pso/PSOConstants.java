package org.inz.pso;

//Interface z stalymi które nas interesuja
public interface PSOConstants {
	int SWARM_SIZE = 30; // liczebnosc roju
	int MAX_ITER = 100; // maksymalna liczba iteracji
	int DIMENSION = 2; // wymiar problemu
	double C1 = 2.0; // indywidualnie najlepszy przypadek (jesli zero eksploatacja max)

	double C2 = 2.0; // globalnie najlepszy przypadek (jesli zero eksploracja max)

	double W_MAX = 1.0; // inicjalna waga bezwladnosci Max
	double W_MIN = 0.0; // minimalna waga bezwladnosci
	//im mniejsze w tym mniej obserwaacji (eksploracji) dokonowanych jest po naszej dziedzinie algorytmu
	//nie mozna z nim przesadzic ani w gore ani w dol najlepiej jesli stopniowo maleje w celu
	//znalezienia optymalnego przypadku i nie przegapienia zadnej mozliwej opcji
}
