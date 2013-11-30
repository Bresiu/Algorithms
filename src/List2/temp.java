package List2;

/**
 * Created by bresiu on 29.10.13.
 */
public class temp {

/*
    public class R4S172778 implements I4 {

        int con = 0;

        int Hash(int key, int m, int i, int bramka) {
            int h1 = key % m;
            if (bramka == 1)
                return (h1 + i) % m;
            else if (bramka == 2)
                return (int) Math.abs((h1 + (i % m) + (con * ((i ^ 2) % m)) % m)) % m;

            return (int) Math.abs(h1 + (i * 1 + (key % (m - 1)))) % m;
        }

        int hashInsert(Z4.Element Tab_hash[], Z4.Element key, int bramka, int m) {
            int pos = 0, i = 0;
            while (i < m) {
                pos = Hash(key.key, m, i, bramka);
                if (Tab_hash[pos] == null) {
                    Tab_hash[pos] = key;
                    return i;
                }
                i++;
            }
            return -1;
        }

        int hashSearch(Z4.Element Tab_hash[], Z4.Element key, int bramka, int m) {
            int pos = 0, i = 0;
            do {
                pos = Hash(key.key, m, i, bramka);
                if (Tab_hash[pos].key == key.key)
                    return i;
                i++;
            } while (i < m && Tab_hash[i] != null);
            return -1;
        }


        public Z4.Wynik testujAdresowanieLiniowe(Z4.Dane dane) {
            Z4.Wynik wynik = new Z4.Wynik();

            int size = (int) (dane.dane.length / dane.alfa) + 1;
            int dl_danych = dane.dane.length;
            Z4.Element Tab_hash[] = new Z4.Element[size];

            int i;
            for (i = 0; i < size; i++)
                Tab_hash[i] = null;

            int tab_srednich_wstaw[] = new int[dl_danych];
            int sred_wstaw = 0;
            //wstawianie do tablicy hashujacej
            for (i = 0; i < dl_danych; i++) {
                Z4.Element key = dane.dane[i];
                tab_srednich_wstaw[i] = hashInsert(Tab_hash, key, 1, size);
            }
            for (i = 0; i < dl_danych; i++)
                sred_wstaw += tab_srednich_wstaw[i];
            //srednia dla hashInsert
            wynik.sredniaDlaWstawiania = sred_wstaw / dl_danych;

            //srednia dla szukania
            int tab_srednich_wyszuk[] = new int[dl_danych];
            int sred_wyszuk = 0;
            for (i = 0; i < dl_danych; i++) {
                Z4.Element key = dane.dane[i];
                tab_srednich_wyszuk[i] = hashSearch(Tab_hash, key, 1, size);
            }
            for (i = 0; i < dl_danych; i++)
                sred_wyszuk += tab_srednich_wyszuk[i];
            //srednia dla hashSearch
            wynik.sredniaDlaSzukania = sred_wyszuk / dl_danych;

            int dewiacja = 0;
            for (i = 0; i < dl_danych; i++)
                dewiacja += (sred_wyszuk - tab_srednich_wyszuk[i]) * (sred_wyszuk - tab_srednich_wyszuk[i]);
            dewiacja = (int) Math.sqrt((int) dewiacja / dl_danych);
            //odchylenie standardowe dla hashSearch
            wynik.odchylenieDlaSzukania = dewiacja;

            wynik.setHashTable(Tab_hash);
            return wynik;
        }


        public Z4.Wynik testujAdresowanieKwadratowe(Z4.Dane dane) {
            Z4.Wynik wynik = new Z4.Wynik();

            int size = (int) ((dane.dane.length / dane.alfa) + 1);
            int dl_danych = dane.dane.length;
            while (size % 16 != 0)
                size++;
            Z4.Element Tab_hash[] = new Z4.Element[size];
            con = size / 8;


            int i;
            for (i = 0; i < size; i++)
                Tab_hash[i] = null;

            //srednia dla wstawiania
            int tab_srednich_wstaw[] = new int[dl_danych];
            int sred_wstaw = 0;
            //wstawianie do tablicy hashujacej
            for (i = 0; i < dl_danych; i++) {
                Z4.Element key = dane.dane[i];
                tab_srednich_wstaw[i] = hashInsert(Tab_hash, key, 2, size);
            }
            for (i = 0; i < dl_danych; i++)
                sred_wstaw += tab_srednich_wstaw[i];
            //srednia dla hashInsert
            wynik.sredniaDlaWstawiania = sred_wstaw / dl_danych;

            //srednia dla szukania
            int tab_srednich_wyszuk[] = new int[dl_danych];
            int sred_wyszuk = 0;
            for (i = 0; i < dl_danych; i++) {
                Z4.Element key = dane.dane[i];
                tab_srednich_wyszuk[i] = hashSearch(Tab_hash, key, 2, size);
            }
            for (i = 0; i < dl_danych; i++)
                sred_wyszuk += tab_srednich_wyszuk[i];
            //srednia dla hashSearch
            wynik.sredniaDlaSzukania = sred_wyszuk / dl_danych;

            int dewiacja = 0;
            for (i = 0; i < dl_danych; i++)
                dewiacja += (sred_wyszuk - tab_srednich_wyszuk[i]) * (sred_wyszuk - tab_srednich_wyszuk[i]);
            dewiacja = (int) Math.sqrt((int) dewiacja / dl_danych);
            //odchylenie standardowe dla hashSearch
            wynik.odchylenieDlaSzukania = dewiacja;

            wynik.setHashTable(Tab_hash);

            return wynik;
        }


        public Z4.Wynik testujHaszowanieDwukrotne(Z4.Dane dane) {
            Z4.Wynik wynik = new Z4.Wynik();

            int size = (int) (dane.dane.length / dane.alfa);
            int dl_danych = dane.dane.length;
            boolean l_pierw = false;
            int temp, i;
            while (l_pierw == false) {
                size++;
                if (size % 2 == 0) continue;
                temp = (int) Math.sqrt(size);
                for (i = 3; i <= temp; i = i + 2) {
                    if (size % i == 0)
                        break;
                    if (i == temp)
                        l_pierw = true;
                }
            }

            Z4.Element Tab_hash[] = new Z4.Element[size];
            for (i = 0; i < size; i++)
                Tab_hash[i] = null;

            //srednia dla wstawiania
            int tab_srednich_wstaw[] = new int[dl_danych];
            int sred_wstaw = 0;
            //wstawianie do tablicy hashujacej
            for (i = 0; i < dl_danych; i++) {
                Z4.Element key = dane.dane[i];
                tab_srednich_wstaw[i] = hashInsert(Tab_hash, key, 0, size);
            }
            for (i = 0; i < dl_danych; i++)
                sred_wstaw += tab_srednich_wstaw[i];
            //srednia dla hashInsert
            wynik.sredniaDlaWstawiania = sred_wstaw / dl_danych;

            //srednia dla szukania
            int tab_srednich_wyszuk[] = new int[dl_danych];
            int sred_wyszuk = 0;
            for (i = 0; i < dl_danych; i++) {
                Z4.Element key = dane.dane[i];
                tab_srednich_wyszuk[i] = hashSearch(Tab_hash, key, 0, size);
            }
            for (i = 0; i < dl_danych; i++)
                sred_wyszuk += tab_srednich_wyszuk[i];
            //srednia dla hashSearch
            wynik.sredniaDlaSzukania = sred_wyszuk / dl_danych;

            int dewiacja = 0;
            for (i = 0; i < dl_danych; i++)
                dewiacja += (sred_wyszuk - tab_srednich_wyszuk[i]) * (sred_wyszuk - tab_srednich_wyszuk[i]);
            dewiacja = (int) Math.sqrt((int) dewiacja / dl_danych);
            //odchylenie standardowe dla hashSearch
            wynik.odchylenieDlaSzukania = dewiacja;

            wynik.setHashTable(Tab_hash);

            return wynik;
        }
        */
}
