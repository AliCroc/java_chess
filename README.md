Założenia 

- Rozplanowanie funkcjonalności programu “szachy”  

- suchy opis działania  

- podział wg GRASP programu na segmenty  

- opisanie klas, ich inherencji, ich relacji i metod 

- Wykonanie schematu blokowego budowy programu 

- Implementacja kodu klas najwyższych i przygotowanie szablonu metod do ich implemetacji na kolejnych zajęciach 

Wymagania co do programu 

Sucho opisane wymagania co do programu podane podczas wykładu:  

- plansza 

- rozłożenie klas 

- stworzenie 2 graczy i by był ruch turowy (najpierw player 1, potem 2) 

- zbijanie 

- poprawny ruch odpowiednich figur 

- roszada 

- 2 sposoby przeprowadzania rozgrywki do wyboru (tekstowo na 4.0 max lub graficzny do 5.0)  

Dodatkowo: 

Uniknąć sieciówki, napisane w JAVA, wersja dowolna, środowisko desktopowe w JAVA (!), można kombinować ale jak najmniej rzeczy spoza JAVA by “obiektówka była jak najprawdziwsza”  

Co chciałbym żeby wyglądał końcowy projekt 

Chciałbym by moje szachy miały interfejs graficzny, nie generowany w konsolce.  

Ruch wykonywany będzie albo przez interaktywne UI (klikamy figurę → podświetlają się możliwe ruchy → odklikujemy / wybieramy gdzie rusza się figura) 

Program posiada planszę widzianą z góry z dynamicznym UI (zmiana szerokości okna reaguje na generowane UI) 

Figury mają swoje przedstawienie graficzne, które pozwala je od siebie odróżnić (wektorowe? Draw real time?) 

Maszyna stanowa będzie utrzymywać logikę zmian ruchu i sprawdzać stan gry (czy jest szach, czy jest szach mat, czy ruch jest prawidłowy, czy jest pat (remis)) 

Figury i ich zasady: 

Pion – porusza się „w przód”, przy ruchu z pola startowego ma możliwość poruszyć się o dwa pola do przodu (co jest definiowane przez?), bije na ukos, bije (TYLKO) PIONKI przeciwnika w przelocie „En passant”  

Goniec – porusza się na skos o zasięgu ograniczonym tylko przez inne figury i planszę, bić może pierwszą figurę spotkaną na swoim możliwym torze ruchu 

Król - może poruszać się o jedno pole w każdym kierunku, może inicjować roszadę, jest obiektem zainteresowanie względem matu, patu i szach matu 

Królowa - może poruszać się we wszystkie strony o zasięgu ograniczonym tylko przez inne figury i planszę, może bić pierwszą figurę napotkaną na swoim torze ruchu 

Koń - porusza się ruchem L, bić może w miejscu "wylądowania", ignoruje napotkane po drodze figury przeskakując nad nimi 

Wieża - może poruszać się pionowo lub poziomo ograniczonym tylko przez inne figury i planszę, może bić pierwszą figurę napotkaną na swoim torze, może być przedmiotem roszady 

 

Tura gracza niech dzieli się na 4 etapy:  

1. Wybranie figury którą chce się poruszyć – na tym etapie program sprawdza, czy dana figura może w ogóle wykonać jakikolwiek ruch 

Jeżeli tak: oznacz obiekt figury w selectedFigure I przejdź do etapu 2 

Jeżeli NIE: wróć do etapu 1 

2. Podświetl możliwe pola do których ruch może dana figura dokonać (definiowane przez zasady figury) i przygotuj się na etap 3. (przygotuj/załaduj zasady specjalne danej figury) 

Jeżeli kliknie się w prawy przycisk myszy lub ESC: anuluj ruch (wyczyść selectedFigure i wróć do etapu 1) 

Jeżeli kliknie lewym przyciskiem myszy na jedno z pól możliwych do wykonania ruchu: przejdź do etapu 3. 

3. Figura przesuwa się w wybrane pole: 

Jeżeli nie ma tam figury: umieść figurę na danym miejscu 

jeżeli jest tam jakaś figura: wykonaj bicie 

Jeżeli stosują się zasady specjalne; roszada (to będzie raczej właściwością wieży I króla), bicie w locie, szach, szach mat, promocja: WYKONAJ 

4.Zakończenie tury – zastosuj zmiany na planszy i zmień gracza, który wykonuje turę 

(kontynuacja z lekcji lab 2)

„Front” programu ma zostać zachowany jako głupi, gdzie pełną odpowiedzialność za zmiany i decyzje w nim ma mieć „backend” 

Front i back będą komunikować się wydarzeniami 

Zastosuję taką formę komunikacji: Backbone jest odpowiedzialny za logikę gry i generalną władzę sądowniczą. Służy za repozytorium Backbone i jest Inicjowany przez Controller. 

Controler to klasa budująca, kontrolująca i opiekująca się programem – weryfikuje logikę samego programu i daje polecenia View i Backbone. Ma absolutną władzę nad uruchamianiem programu, nad przerwaniami, nad wszystkimi funkcjami programu wykraczającymi poza grę właściwą w szachy. Posiada w sobie maszynę stanową, która mówi 

BoardView jest UI programu. Nie ma żadnej „władzy wykonawczej” za wyjątkiem przekazywania wejścia od użytkownika. Komunikuje się wyłącznie z Controllerem za pomocą eventów.  

Zastosowanie czegoś ala MVC, asynchronicznie z komunikatami eventowymi. 

W porządku, czas to uporządkować. Skupię się na początku na podziale z poziomu górnego.