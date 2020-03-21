# Projekt wybierający dane z excela na WAD do dr Ławickiego - zadanie drugie.

## Żeby odpalić projekt, potrzebujecie:
1. Java w wersji >11
2. Środowisko uruchomieniowe (IntelliJ)
3. Postman

## Wykonanie programu:
1. Zaimportować projekt z gita do IntelliJ (Import from Version Control -> Git)
2. Dodać jako projekt mavenowy (add as Maven Project)
3. Zbudować projekt komendą mvn clean install
4. Odpalić projekt zieloną strzałką przy Run Configurations
5. W postmanie:
+ wybrać nowy request - jako metodę - POST
+ adresem pod który strzelamy jest: localhost:8080/upload
+ wybrać zakładkę body, z listy rozwijalnej wybrać form-data
+ w form-data wprowadzić trzy parametry. Pierwszy - nazwa "file" o typie File - postman pozwoli Wam wybrać plik z dysku
Drugi - "baseDate" data, od której zaczyna się zestawienie kursów
Trzeci - "path" - ścieżka pliku, do którego ma się zapisać wynik
