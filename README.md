#Trello Selenium Test – README
Ovaj projekt je mali end‑to‑end test za Trello board, napisan u Javi uz Selenium WebDriver, TestNG i Maven.
Test automatizira login, otvaranje boarda, kreiranje kartice, postavljanje datuma i dodavanje labele putem tipkovnice.

Tehnologije
Java 21 (ili kompatibilni JDK)

Maven

Selenium WebDriver 4

TestNG

WebDriverManager

Google Chrome / Chrome for Testing

IntelliJ IDEA (preporučeno)

Struktura projekta
Relevantne datoteke:

pom.xml – Maven dependenciji (Selenium, TestNG, WebDriverManager...).

src/test/java/org/example/TrelloCreateTaskTest.java – glavna test klasa.

README.md – ova datoteka.

Test klasa: TrelloCreateTaskTest
Sadrži:

@BeforeMethod setupTest()

podiže ChromeDriver preko WebDriverManagera

postavlja putanju do Chrome binary‑ja

maksimizira prozor i inicijalizira WebDriverWait.

@AfterMethod teardownTest()

zatvara preglednik nakon svakog testa.

@Test createTaskWithDueDateAndLabel() – glavni end‑to‑end scenarij:

Otvara https://trello.com/login.

Logira se na Trello (email + lozinka).

Čeka da se pojavi link “Boards” (potvrda uspješnog logina).

Otvara board “Spring Bloom”.

Kreira novu karticu s nazivom TESTIRANJE.

Otvara karticu TESTIRANJE.

Postavlja due date na 2/14/2026 i sprema ga.

Šalje tipku 1 na body kako bi dodao labelu (Trello keyboard shortcut za prvu labelu).

Ovaj test simulira realni korisnički tok na Trellu na jednom boardu.

Preduvjeti
Prije pokretanja:

Instaliraj:

JDK (npr. 21)

Maven

Google Chrome ili Chrome for Testing (odgovarajuća verzija za tvoj ChromeDriver)

Kloniraj repozitorij:

bash
git clone <URL_REPOZITORIJA>
cd <ime_projekta>
U klasi TrelloCreateTaskTest prilagodi sljedeće:

Putanju do Chrome binary‑ja u setupTest():

java
options.setBinary("C:\\Users\\Matej\\IdeaProjects\\chrome-win64\\chrome.exe");
Login podatke za Trello:

java
emailInput.sendKeys("tvoj_email@domena.com");
passwordInput.sendKeys("tvoja_lozinka");
Po želji, CSS selektor za tvoj board ako se razlikuje od:

java
a[href='/b/czsVVp5a/spring-bloom'][title='Spring bloom']
Na Trellu treba postojati board kojim test upravlja (npr. “Spring Bloom”).
Test će automatski kreirati karticu TESTIRANJE u listi gdje se nalazi gumb Add a card na koji cilja selektor button[data-testid='list-add-card-button'].

Pokretanje testova
Opcija 1 – Maven (command line)
U root direktoriju projekta:

bash
mvn test
Maven će:

preuzeti dependencye,

izgraditi projekt,

pokrenuti TestNG testove (uključujući TrelloCreateTaskTest).

Rezultate možeš vidjeti u konzoli i u target/surefire-reports.

Opcija 2 – IntelliJ IDEA
Otvori projekt u IntelliJ‑u.

Pričekaj da Maven povuče sve dependencye.

U src/test/java/org/example/TrelloCreateTaskTest.java:

desni klik na klasu → Run 'TrelloCreateTaskTest'.

Test će pokrenuti Chrome, proći kroz sve korake i zatvoriti preglednik.

Sigurnost i privatni podaci
Za javni GitHub repozitorij ne preporučuje se držati email, lozinku i lokalne putanje hard‑kodirane.
Tipičan pristup:

Izvuci osjetljive podatke u lokalnu konfiguracijsku datoteku (config.properties ili .env).

Tu datoteku dodaj u .gitignore.

U kodu čitaj vrijednosti iz konfiguracije (npr. Config.get("trello.email")).

U ovom repozitoriju primjeri hard‑kodiranih vrijednosti služe isključivo za demonstraciju, a svaki korisnik treba ih lokalno zamijeniti svojim konfiguracijama.