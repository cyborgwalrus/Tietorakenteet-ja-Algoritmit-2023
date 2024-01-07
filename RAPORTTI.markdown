# Raportit tehtävistä

Kirjaa tähän tiedostoon **jokaiseen** tehtävään liittyvät omat raporttisi ja analyysisi. Muista että raportti on myös kurssilla **arvosteltava tehtävä**.

Voit sisällyttää raporttiin tekstimuotoisia taulukoita (tasaukset välilyönnein):

```text
n     Fill     Search   Total
500   7        700      707
1000  9        288      297
```

Ja näihin liittyviä kuvatiedostoja:

![Esimerkkikuva](report-sample-image.png)

Nämä näkyvät sitten VS Coden Preview -näkymässä (tai oman repositorysi webbisivulla) oikein muotoiltuna. Käytä tässä dokumentissa olevia muotoiluja esimerkkinä kun kirjoitat raporttiasi.

Huomaa että jos laitat kuvatiedostot vaikka omaan alihakemistoonsa, Markdown -muotoilussa on oltava suhteellinen polku tiedostoon, esimerkiksi `images/report-sample-image.png`. **Älä** käytä absoluuttisia polkuja `C:\Users\tippaleipa\kurssit\TIRA\kuva.png`, koska nämä eivät tietenkään toimi opettajan koneella. Ei kannata laittaa linkkiä etärepoosikaan, vaan nimenomaan paikalliseen tiedostoon.

Voit myös sisällyttää *lyhyitä* koodinpätkiä vaikkapa Java -formaatilla:

```Java
 @Override
 public int hashCode() {
  // Oma nerokas hajautufunktioni!
 }
```

Tarvittaessa käytä myös paremmin muotoiltuja taulukoita:

| n    | Fill | Search | Total |
| ---- | ---- | ------ | ----- |
| 500  | 7    | 700    | 707   |
| 1000 | 9    | 288    | 297   |

Alaluvut jokaisen tehtävän raportille löydät alta.

## 01-TASK

Hyvää harjoitusta toisten tekemän koodin lukemiseen ja olemassa olevan koodikannan sisällä toimimiseen. Yksikkötestit helpottivat algoritmin suunnittelua huomattavasti. `InsertionSortin` toteutus Wikipedian pseudokoodia soveltaen ei ollut hankalaa eikä ollut myöskään `Reverse` vaikka siihen en katsonut mallia.

Lajittelualgoritmin aikakompleksisuus on luokkaa $O(n^2)$ koska se sisältää kaksi sisäkkäistä silmukkaa jotka pahimmassa tapauksessa käyvät läpi n-kappaletta elementtejä ja vertaavat niitä toisiinsa.

`Reverse`-algoritmissä kaksi indeksiä aloittavat listan eri päistä ja kohtaavat keskellä, mihin kuluu $n/2$ kierrosta. Täten algoritmin aikakompleksisuus on luokkaa $0(n)$.

Nousevassa järjestyksessä oleva taulukko kannattaa lajitella laskevaan järjestykseen `reverse`-algoritmilla koska $O(n)$ luokan algoritmina se skaalautuu paljon paremmin suurien taulukoiden käsittelyyn kuin luokkaa $O(n^2)$ oleva `insertionSort`.

## 02-TASK

### Add-algoritmi

![fill](task-02-graph-fill.png)
Add-algoritmin pitäisi ensisilmäyksellä olla luokkaa $O(n)$ koska se sisältää vain yhden $n$-kokoisen for-silmukan, mutta huomataan että koska `add` lisää jokaisen elementin yksitellen, ajetaan funktio $n$-kokoisella taulukolla $n$-kertaa, ja täten funktion aikakompleksisuus on oikeasti luokkaa $O(n^2)$. Tämä huomataan myös Fill-kaaviosta, jonka trendiviivana on toisen asteen polynomiregressio $R^2$-arvolla $0,966$.

### Search-algoritmi

![search](task-02-graph-search.png)
Kuten tehtävänannossa jo paljastettiin, ovat haku-algoritmit lineaarisia ja koodista olevien yksittäisten n-kokoisten for-silmukoiden perusteella päädymme samaan lopputulokseen, eli $O(n)$. Toisin sanottuna algoritmi käy *lineaarisesti* läpi koko taulukon elementti kerrallaan kunnes se löytää etsimänsä.  

Vaikka kaaviolle parhaan $R^2$ arvon $0,55$ trendiviiva löytyi toisen asteen polynomiregressiolla, se ei ole $R^2$ pienuuden vuoksi kovin vakuuttava. Silmämääräisesti arvioituna trendiviiva poikkeaa lineaarisesta vain marginaalisesti, jos suurimmat piikit hakuajassa jätetään huomioimatta.

### TIRA Coders lajittelu
>
>PhoneBookArray: Reading JSON with  10000 items took 280 ms  
PhoneBookArray: JSON to Coders took 30 ms  
`Valittiin laskeva nimijärjestys`  
PhoneBookArray: **Sorting took 830 ms**  
PhoneBookArray: Added to container & Sorted took 1530 ms  
`Valittiin nouseva nimijärjestys`  
PhoneBookArray: **Reversing took 6 ms**  
`Valittiin laskeva koodarinimijärjestys`  
PhoneBookArray: **Sorting took 885 ms**  
`Valittiin laskeva nimijärjestys`  
PhoneBookArray: **Sorting took 841 ms**  

Lokitiedoista voidaan päätellä, että lajittelu on erittäin nopea kun vaihdetaan lajittelusuuntaa mutta ei lajittelutyyppiä. Kun vaihdetaan lajittelutyypistä toiseen, on koko lajittelualgoritmi ajettava uudestaan ajassa $O(n^2)$, kun taas suuntaa vaihtaessa riittää että ajetaan $O(n)$-luokkaa oleva `reverse`.

### Muita mietteitä

Tehtävä sujui muuten ongelmitta, mutta jämähdin pariksi tunniksi bugiin, jossa koodareiden kaverit olivat kaikki arvoltaan `null null`. Ensiksi tuhlasin aikaani ihmettelemällä miksi breakpointtini eivät toimi. Siihen auttaa että muistaa painaa Run-napin sijasta Debug.  
Tunnin koodisukelluksen ja rivi kerrallaan debugaamisen jälkeen löysin viimein bugin sijainnin. Olin `SimpleContainer.get()` funktiota tehdessäni epähuomiossa palauttanut pelkästään hakua varten luodun id-kenttää lukuun ottamatta tyhjän elementin `element` enkä taulukosta löytynyttä elementtiä `array[i]`. Opinpahan ainakin että joskus mitä yksinkertaisimpienkin bugien etsimiseen voi mennä suhteettoman paljon aikaa.

## 03-TASK

### TIRA koodareiden puolitushaku
>
>PhoneBookArray: Search took 40 ms  
`Hain sukunimellä Öörni`  
PhoneBookArray: Search took 39 ms  
PhoneBookArray: Get coder by index took 0 ms  
PhoneBookArray: Getting friend names took 21 ms  
`Hain Exact haulla Öörnin koko nimen`  
PhoneBookArray: Fast search took 0 ms
PhoneBookArray: Get coder by index took 0 ms  
PhoneBookArray: Getting friend names took 10 ms  
`Hain sukunimellä Aarni`  
PhoneBookArray: Search took 0 ms  
PhoneBookArray: Get coder by index took 0 ms  
PhoneBookArray: Getting friend names took 11 ms  
`Hain Exact haulla Aarnin koko nimen`  
PhoneBookArray: Fast search took 0 ms  

Nopea haku oli 50k aineistollakin niin nopea että se pyöristyy $0$ ms.
Tavallinen haku oli suhteessa paljon hitaampi, johtuen siitä että se toimii lineaarisella haulla joka on luokkaa $O(n)$ kun taas puolitushakuhaku puolittaa hakualueensa joka kierroksella, joten sen luokka on $O(log n)$. Tämä huomataan myös lokitiedoista, missä listan alussa olevan nimen hakeminen oli erittäin nopeaa verrattuna listan lopussa olevaan nimeen.

### Taulukon täyttö ja lajittelu

![fill ja sort](task-3-graph-fill-sort.png)

### Lineaarinen haku

![search](task-02-graph-search.png)

### Puolitushaku

![binSearch](task-3-graph-binSearch.png)

### Analyysi

#### Lineaarinen haku

Edellisen kappaleen päättelyjen ja viime taskissä kerätyn datan perusteella lineaarinen haku on aikakompleksisuusluokkaa $O(n)$. Algoritmi aloittaa taulukon alusta ja käy yksitellen kaikki sen alkiot läpi kunnes se löytää etsimänsä alkion, joka on pahimmassa tapauksessa listan lopussa paikalla $n$.

#### Puolitushaku

Algoritmin toimintaperiaatteesta ja sen nimestä voidaan päätellä että sen aikakompleksisuusluokka on $O(log n)$. Puolitushaku nimensä mukaisesti puolittaa joka kierroksella hakualueensa. Tämä tekee siitä huomattavasti nopeamman suurilla datamäärillä verrattuna lineaarihakuun.  
Puolitushakun haittapuolena on että se vaatii datan olevan lajiteltuna. Jos data ei ole suuruusjärjestyksessä, puolitushaulla on joka puolituksessa 50% todennäköisyys valita sama puolikas kuin missä etsitty alkio sijaitsee. Lineaarista hakua datan järjestämättömyys ei haittaa.

### Muita mietteitä

Toteutin puolitushaun seuraten luentodiojen pseudokoodia, mikä johti ongelmiin. Toteutukseni jäi useasti loputtomaan silmukkaan tapauksissa, joissa `high - low == 1`. Silloin `(high-low)/2` pyöristyy nollaan ja silmukka jämähtää arvoon `medium = low` ja `low != high` ehto ei koskaan täyty. Ratkaisin ongelman apufunktiolla `max((high-low)/2), 1)` joka varmistaa että viimeisellä kierroksella kun $high == low + 1$, seuraa että $middle = low + 1 \rightarrow low = middle \rightarrow low == high$ joten silmukan katkeamisehto täyttyy.

Toinen ongelma tuli vastaan TIRA Coders sovelluksessa. Kun koodareita haettiin Exact haulla, haku suoritettiin mutta ohjelma ei valinnut löydettyä koodaria. Askel kerrallaan debugaamalla virhe löytyi puolitushaun toteutuksestani. Olin epähuomiossa käyttänyt vertailussa `Coder.id` kenttää tarkastelevaa `.equals()` enkä nimellä vertaavaa `comparator.compare()`. Tästä syystä algoritmi ei koskaan palauttanut löydetyn koodarin indeksiä, joten ohjelma ei voinut myöskään valita löydettyä koodaria listasta.

## 04-TASK

### Aikakompleksisuusvaatimukset

- **capacity()**: yksi return, $O(1)$.
- **push()**: Ei silmukoita, $O(1)$. Jos kutsutaan `reallocate()` yksi `for`-silmukka, $O(n)$.
- **pop()**: Ei silmukoita tai funktiokutsuja, $O(1)$.
- **peek()**: Ei silmukoita tai funktiokutsuja, $O(1)$.
- **size()**: Yksi `if` ja yksi `return`, $O(1)$.
- **isEmpty()**: Yksi `if` ja yksi `return`, $O(1)$.
- **clear()**: Ei silmukoita, $O(1)$.
- **toString()**: Yksi pinon mittainen `for`-silmukka, $O(n)$.

### Muita mietteitä

Jos tiedostossa on liikaa tai liian vähän lainausmerkkejä, tarkistin tulkitsee lainausmerkkien ulkopuolelle tarkoitetun tekstin olevan sen sisällä, johtaen siihen että tarkistin jättää valideja sulkuja laskematta ja heittää `ParenthesisException` kun ne eivät enää menekkään tasan.

Yksityiskohtaisten kommenttien ja luentodiojen pseudokoodin avulla tehtävän tekeminen sujui melko ongelmitta. Eniten päänvaivaa tuotti sopivien Exceptionien hallinta ja heittäminen.

## 05-TASK

### ArrayQueue Aikakompleksisuusvaatimukset

- **capacity()**: yksi return, $O(1)$.
- **enqueue()**: Ei silmukoita, $O(1)$. Jos kutsutaan `reallocate()` yksi `for`-silmukka, $O(n)$.
- **dequeue()**: Ei silmukoita tai funktiokutsuja, $O(1)$.
- **element()**: Ei silmukoita tai funktiokutsuja, $O(1)$.
- **size()**: Yksi `return`, $O(1)$.
- **isEmpty()**: Yksi `if` ja yksi `return`, $O(1)$.
- **clear()**: Ei silmukoita, $O(1)$.
- **toString()**: Yksi jonon mittainen `for`-silmukka, $O(n)$.

### LinkedListQueue Aikakompleksisuusvaatimukset

- **capacity()**: yksi return, $O(1)$.
- **enqueue()**: Ei silmukoita, $O(1)$.
- **dequeue()**: Ei silmukoita tai funktiokutsuja, $O(1)$.
- **element()**: Ei silmukoita tai funktiokutsuja, $O(1)$.
- **size()**: Yksi `return`, $O(1)$.
- **isEmpty()**: Yksi `if` ja yksi `return`, $O(1)$.
- **clear()**: Ei silmukoita, $O(1)$.
  - Javan oma `LinkedList`-toteutus käyttää $O(n)$ algoritmia joka nullaa yksitellen kaikki `Node`t mutta useiden Stackoverflow-postaajien mukaan roskienkerääjän pitäisi poistaa koko `LinkedList` kun sen `head` nullataan. Jos koodissa olisi `Iterator`-objekteja pitämässä `Node`-referenssejä muistissa, tämä ei toimisi.
  - <https://stackoverflow.com/questions/5658465/javadelete-all-the-elements-from-linked-list>
  - <https://stackoverflow.com/questions/31069894/deleting-a-single-linked-list-by-just-making-head-null>
  - <https://stackoverflow.com/questions/33935980/java-best-practice-regarding-clearing-a-linked-list>
- **toString()**: Yksi jonon mittainen `while`-silmukka, $O(n)$.

### LinkedListQueue vs ArrayQueue

Aikakompleksisuuden suhteen toteutukset ovat melkein tasaväkisiä. `ArrayQueue` jää hieman jälkeen kun se joutuu varaamaan lisää tilaa, pudottaen sen $O(1)$-luokasta luokkaan $O(n)$. `LinkedList`in läpikäyminen on hitaampaa kuin `Array`n, mutta jonossa tätä meidän ei onneksi tarvitse tehdä, koska meitä kiinnostaa vain jonon ensimmäinen ja viimeinen elementti.

Muistikompleksisuudessa `LinkedList` häviää `Array`lle koska sen pitää jokaisessa `Node`ssa säilyttää muistissa viittauksen edelliseen ja seuraavaan `Node`en.

Olettaisin myös että `ArrayQueue` saa pientä lisänopeutta paremmasta rekisterivälimuistien hyödyntämisestä koska sen data on muistissa peräkkäisissä muistiosoitteissa, mikä vähentää cache missejä.

### Muita mietteitä

`ArrayQueue`n toteutus sujui ongelmitta. Oli mielenkiintoista päästä käyttämään `LinkedList`iä oikeassa koodissa, tähän mennessä se on tullut vastaan vain pienessä C-kielen harjoitustehtävässä. Huomasin vasta TIRA coders sovelluksessa pienen bugin joka oli päässyt läpi yksikkötesteistä. Kun jonoa käytiin läpi, ei jäljellä olevien koodarien lukumäärä pienentynyt. Ohjelma ei kuitenkaan kaatunut kun päästiin jonon loppuun. Olin unohtanut pienentää `count`-muuttujaa kun `LinkedList`-jonosta poistettiin alkioita.

#### 3.11.2023 korjaus

Koodissa olikin bugeja. Molemmissa `toString()`-metodeissa oli loputon silmukka, minkä vuoksi suurin osa niitä hyödyntävistä testeistä epäonnistuivat hiljaa ilman että VSCode tajusi merkitä niitä hylätyiksi, joten puujärjestyksessä olevat testit näyttivät läpäistyiltä, vaikka vain ensimmäinen testi oli läpi. Ongelma korjaantui lisäämällä silmukoihin puuttuvat silmukkamuuttujien suurennokset. Korjauksen jälkeen paljastui että puolet testeistä eivät mennetkään läpi.  

`ArrayQueue.reallocate()` oli epähuomiossa tehty vain kopioimalla vanha array isompaan, kirjoitin sen uudestaan käyttäen `dequeue()`-metodia.

## 06-TASK

### InsertionSort Mittaukset

![InsertionSort graph](task-06-graph-InsertionSort.png)
![InsertionSort table](task-06-table-InsertionSort.png)

### QuickSort Mittaukset

![QuickSort graph](task-06-graph-QuickSort.png)
![QuickSort table](task-06-table-QuickSort.png)

### Analyysi

#### InsertionSort

Mittausdatasta näkyy selvästi että InsertionSort ei ainakaan ole lineaarinen eli $O(n)$. Etenkin `ms/element` kentässä huomataan ajan tuplaantumista testien välillä, ja testistä 4 eteenpäin ajoaika hyppää niin massiivisesti että epäilen läppärini vaihtaneen RAMin täytyttyä pagefilen puolelle.  
Kaavion trendiviivan tulkinta vaikeuttaa mittauspisteiden harvuus testialueen loppupuolella. Kuten kuvata näkee, suurin osa mittauspisteistä on ryppäänä käyrän alkupäässä joiden jälkeen on vain 2 mittauspistettä mikä luo illuusion käyrän lineaarisuudesta. Kun mittausaluetta rajaa alku- ja loppupäästä, käyrän eksponentiaalinen kasvu näkyy paljon selvemmin. Kuten Task 1 raportissa totesin, on `InsertionSort`in aikakompleksisuus luokkaa $O(n^2)$ koska se sisältää kaksi sisäkkäistä silmukkaa.

#### QuickSort

Jo pelkästään testien lukumäärästä huomataan että `QuickSort` skaalautuu `InsertionSort`ia paremmin. Siinä missä `InsertionSort` sakkaa kuudennen testin kohdalla, `QuickSort` pääsee ongelmitta kahdeksanteen testiin asti. Myös sen `ms/element` on monta kertaluokkaa pienempi ja kasvaa mitättömän vähän testien välillä.  
Kaaviosta näkyy selvästi että mittaukset asettuvat lineaariselle trendiviivalle $R^2$-arvolla $0.999$. Tämä on ensisilmäyksellä ristiriidassa koodin (n-kokoisen for-loopin sisällä toinen joka kierroksella puolittuva silmukka) ja teorian kanssa, joiden mukaan `QuickSort`in aikakompleksisuus on $O(n*log(n))$, mutta kun huomioidaan että aikakompleksisuuden $n$-termi kasvaa $log(n)$-termiä nopeammin, on selvää että suurilla $n$-arvoilla $O(n*log(n))$ lähestyy asymptoottisesti luokkaa $O(n)$.

### Muita Mietteitä

Koitin ensin tehdä `QuickSort`ia iteratiivisella toteutuksella mutta en saanut sitä toimimaan, joten siirryin helpompaan rekursiiviseen toteutukseen. Toteutus sujui ongelmitta muutamaa lajittelujärjestyksen pilaavaa off-by-one-erroria lukuunottamatta.

## 07-TASK

### Algoritmit

- **add(K key, V value)**: Silmukka jonka sisällä "pontteri" sukeltaa puuhun etsien oikeaa paikkaa lisättävälle alkiolle. Valitsee vasemman tai oikean lapsen comparatorilla hyödyntäen puun järjestystä. Hakualue puolittuu joka kierroksella. $O(log(n))$.
- **get(K key)**: Sama toimintaperiaate kuin `add()`. $O(log(n))$.
- **find(Predicate<V> searcher)**: Rekursiivinen funktio sukeltaa puuhun InOrder-järjestyksessä. Koska `searcher` osaa vain yhtäsuuruuden, on jokainen alkio testattava yksitellen, joten pahimmassa tapauksessa funktio käy jokaisen alkion läpi. $O(n)$
- **size()**: Laskee puun solmujen lukumäärän hyödyntämällä juurisolmun lasten lukumäärää, joka päivittyy `add()`-funktiossa. $O(1)$.
- **clear()**: Asettaa `root`-solmun `null`iksi. $O(1)$.
- **toArray()**: Rekursiivinen funktio käy kaikki puun solmut läpi InOrder-järjestyksessä lisäten ne samalla taulukkoon. $O(n)$.
- **indexOf(K itemKey)** Rekursiivinen funktio käy puun läpi InOrder-järjestyksessä kasvattaen samalla `AtonicInteger` indeksiä ja palauttaa sen jos `itemKey`tä vastaava alkio löytyy. $O(n)$.
- **getIndex(int index)**: Sama toimintaperiaate kuin `indexOf()` mutta palauttaa solmun kun ehto `AtomicIdex == index` on saavutettu. $O(n)$.
- **findIndex(Predicate<V> searcher)**: Käytännössä sama funktio kuin `indexOf` mutta comparatorin sijasta käytetään predikaattia. $O(n)$.
- **accept(Visitor<K, V> visitor)**: $O(1)$.

### Mittaukset

#### Simple Container

![simple container add time](<task-07-Simple Container Add Time per Item.png>)
![compare simple-container](task-07-compare-simple-container.png)

#### Binary Search Tree

![BST get(index)](<task-07-BST Get(index) Time per Item.png>)
![compare bst](task-07-compare-bst.png)

### Analyysi

#### Puun syvyys

![tree analytics](<task-07-tree analytics.png>)

Visitorin avulla kerätyn datan perusteella hakupuu suoriutui varsin hyvin verrattuna ideaaliin, ottaen huomioon että ideaalin syvyyden laskukaava $log_{2}(n)$ olettaa että puuhun järjestettävät alkiot ovat jakautuneet tasaisesti, mikä ei tietenkään päde ihmisten nimille.  

Tämä huomataan etenkin vertaamalla ideaalia syvyyttä minimi- ja maksimisyvyyksiin. Maksimisyvyys kertoo meille että nimien aakkosjärjestyksessä löytyy ainakin yksi klusteri jonka järjestäminen hakupuuhun johtaa yli tuplasti ideaalia syvempään oksaan.  
Minimisyvyys puolestaan kertoo alueesta joissa aakkosjärjestettyjä nimiä on niin harvassa että niiden järjestämiseen vaadittavan oksan syvyys on puolet ideaalista.

#### BST vs Simple Container (Array)

- **Add**: `BST.add()` on $O(log(n))$ algoritmina nopeampi suuremmillakin datamäärillä. Simple Container aloittaa $O(n)$-luokassa käyden sisäisen taulukkonsa läpi alusta loppuun kopioiden varalta ja lisää uuden alkion arrayn loppuun jos niitä ei löydy. Suuremmilla datamäärillä tarvitaan myös `reallocate()`-kutsua joka on $O(n)$, joten Simple Container on käytännössä puhdasta $O(n)$-algoritmia hitaampi.
- **ToArray+Sort** BST edelleen nopeampi. Koska BST on luonnostaan lajiteltu, riittää että sen käydään läpi InOrder-järjestyksessä kopioiden sen alkiot samalla palautettavaan taulukkoon ajassa $O(n)$. Simple Container ei välttämättä ole järjestyksessä, joten se pitää ensin lajitella QuickSort-algoritmilla ajassa $O(n*log(n))$ ja sen jälkeen kopioida taulukosta toiseen alkio kerrallaan ajassa $O(n)$.
- **Search** Tasapeli. Molemmat joutuvat käymään alkionsa läpi yksi kerrallaan ajassa $O(n)$ kunnes hakuehtoa vastaava alkio löytyy.
- **getIndex** Simple Container voittaa suurella marginaalilla. Jos Simple Container on järjestetty, on indeksissä olevan alkion haku $O(1)$-operaatio, ja järjestyksenkin kanssa vain $O(n*log(n))$. Paremmalla toteutuksella BST olisi ollut $O(log(n))$ koska lapsisolmujen määrää hyödyntämällä indeksin haku saataisiin puolitettua joka askeleella. Oma toteutukseni indeksin hausta oli vain paljon hitaampi $O(n)$ koska se joutuu hakemaan järjestyksessä ensimmäisen alkion ja käymään ne yksitellen läpi kunnes haluttu indeksi löytyy.

### Valinnaiset tehtävät

- Tein `BSTAnalyzerVisitor`

### Muista mietteitä

- Binäärisen hakupuun järjestyksen hahmottamisen vaikeus tuotti ongelmia toteutuksien kanssa.
- En aluksi muistanut AtomicIntegeriä joten rekursiivisien algoritmien toteutus aiheutti päänvaivaa.
- `BSTAnalyzerVisitor` vaikutti ensin helpolta mutta en saanut `.visit()`-metodin overloadausta toimimaan, vaan kaikki luokat kutsuivat jostain syystä ainoastaan `visit(Visitable<K, V> visitable)`-versiota eivätkä omia metodejaan. Tuhlattuani useita tunteja kymmenien stackoverflow-postausten ymmärtämiseen, tajusin viimein mistä vika johtui. Tekemäni overloadatut metodit puuttuivat `Visitor`-interfacesta minkä vuoksi `Visitable`-interfacea toteuttavat luokat eivät tienneet muiden kuin geneerisen `.visit()` metodin olemassaolosta.

## 08-TASK

### Algoritmit

#### Coder

- **HashCode()**: Toteutettu *Fibonacchi hashing*illä
  1. `Coder.getId()` pilkotaan palasiksi
  2. palaset XOR-ataan keskenään
  3. lopputulos kerrotaan valmiiksi lasketulla `FIBONACCHI_MULTIPLIER`-vakiolla ja tulon annetaan ylivuotaa
- `FIBONACCHI_MULTIPLIER`: Lasketaan kaavalla $2^{30}/\phi$, missä $\phi$ on kultaisen leikkauksen likiarvo.

#### HashTableContainer

- **calculateIndex(K key, int i)**: Laskee lisättävälle alkiolle indeksin kaavalla  
 `((key.hashCode() + 29*i + 11*i*i) & 0x7fffffff) % arraySize`:
  1. `key.hashCode()` on `Coder`-oliolla *fibonacchi hash*
  2. `+ 29*i + 11*i*i` *Fibonacchi hashing* yksinään johtaa indeksien kasaantumiseen, joten sitä muokataan *Quadratic Probing*illa, missä $29$ ja $11$ ovat mielivaltaisesti valittuja alkulukuja ja $i$ on yleensä arvoltaan $1$ mutta sitä voidaan suurentaa jos $i=1$ johtaa *hash collision*iin.
  3. `& 0x7fffffff` Bitwise AND bittimaskilla `0x7fffffff` nolla luvun suurimman bitin mikä muuttaa negatiiviset luvut positiiviseksi, koska Javassa `int` on *two's complement*-formaatissa.
  4. `% arraySize` kohdissa 1 ja 2 laskettu hash on välillä $[0, 2^{31}]$ joten se pienennetään modulolla välillä $[0, arraySize - 1]$.
- **add(K key, V value)**: Jos uuden alkion lisäys saa taulukon koon ylittämään rajan `capacity() * REALLOCATION_THRESHOLD=0.75`, tuplataan taulukon koko metodilla `ensureCapacity()`. Sen jälkeen `while`-silmukassa etsitään `calculateIndex()`-metodin avulla lisättävälle alkiolle tyhjä paikka. Parhaimmillaan $O(1)$ mutta $O(n)$ jos joudutaan ajamaan `ensureCapacity()`.
- **get(K key)**: Laskee `calculateIndex()`-metodilla `key`-argumenttia vastaavan indeksin ja vertaa sitä indeksissä olevaan alkioon. Jos `key`t täsmäävät, palauttaa indeksissä olevan alkion `value`n. Jos `key`t eivät täsmää, suurennetaan `calculateIndex()`-metodin argumenttia `i` ja yritetään uudestaan. $O(1)$.
- **remove(K key)**: Sama toimintaperiaate kuin `get(K key)`, mutta palauttamisen lisäksi nullaa alkion. $O(1)$.
- **find(Predicate<V> searcher)**: Koska hajautustaulun indeksi lasketaan `key`stä eikä `value`sta, on koko taulukko käytävä manuaalisesti läpi kunnes predikaattia vastaava `value` löytyy. $O(n)$.
- **size()** ja **capacity()**: palauttavat valmiiksi lasketun attribuutin. $O(1)$.
- **ensureCapacity(int capacity)**: Koska taulukon koon muuttaminen vaikuttaa `calculateIndex()`-metodissa laskettuihin indekseihin, alkiot eivät päädy erikokoisissa taulukoissa samoihin indekseihin. Siksi taulukon koota muuttaessa on jokainen taulukon alkio indeksoitava uudestaan. $O(n)$.
- **clear()**: Korvaa taulukon samankokoisella tyhjällä taulukolla. $O(1)$.
- **toArray()**: Käy hajautustaulukon läpi ja kopio jokaisen alkion yksi kerrallaan uuteen palautettavaan taulukkoon. $O(n)$.

### Mittaukset

#### Simple Container

![compare simple-container](task-07-compare-simple-container.png)

#### BST

![compare bst](task-07-compare-bst.png)

#### Simple Keyed Container

![compare simple keyed container](task-08-table-compare-simple-keyed-container.png)

#### Hashtable

![compare hashtable](task-08-table-compare-hashtable.png)

### Analyysi

---

#### Add

1. Simple Keyed Container ja Hashtable $O(1)$
2. BST $O(log(n))$
3. Simple Container $O(n)$

SKC toimii jonona jonka tarvitsee vain lisätä uusi alkio jonon jatkoksi. Hashtablen tarvitsee vain laskea alkiolle indeksi. BST sen sijaan joutuu hakemaan puusta järjestyksen mukaista paikkaa alkolle, mutta hakualue puolittuu joka askeleella. Hitain Simple Container joutuu käymään taulukkoaan läpi alkio kerrallaan kunnes se löytää vapaan paikan.

---

#### ToArray ja sort

1. BST $O(n)$
2. Muut $O(n*log(n)) + O(n)$

Kaikki datarakenteet suorittavat `toArray()`-metodin kopioimalla jokaisen alkion yksi kerrallaan palautettavaan taulukkoon, mutta vain BST on valmiiksi järjestetty. Muut datarakenteet on lajiteltava *QuickSort*-algoritmillä ennen tai jälkeen kopioinnin.

---

#### Key Search

1. Hashtable $O(1)$
2. BST ja Simple Container $O(log(n))$
3. Simple Keyed Container $O(n)$

Hashtablen ei tarvitse etsiä avaimia, koska avain toimii `calculateIndex()`-metodin avulla suoraan taulukkoindeksinä.  
BST voi hyödyntää sisäistä järjestystään avainta haettaessa, puolittaen hakualueen jokaisella askelella alas puuta.  
Samaan aikakompleksisuuteen yltää Simple Container, koska järjestämisen jälkeen voidaan hyödyntää `indexOf()`-metodia joka on toteutettu $O(log(n))$ binäärihaku-algoritmilla. Löydetyllä indeksillä saadaan `get(int index)`-metodilla haluttu alkio ajassa $O(1)$.  
Simple Keyed Containerille ei ole toteutettu `IndexOf()`-metodia eikä se ole hakuvaiheessa järjestyksessä muutenkaan, joten sen haku on suoritettava hitaammalla `get(K key)`-metodilla ajassa $O(n)$.

### Muita Mietteitä

- `calculateIndex()`-metodi hyödynsi aluksi vain `Coder.hashCode()`-metodissa laskettua *Fibonacchi hash*iä mikä johti indeksien klusteroitumiseen ja hash collisioneihin mitkä hidastivat alkioiden lisäämistä. Korjasin ongelman lisäämällä `hashCode()`n perään alkulukuja hyödyntävän *Quadratic Probing*-algoritmin minkä jälkeen alkiot jakautuivat paljon tasaisemmin taulukkoon.
- `ensureCapacity()`-metodin toteutuksessä törmäsin epäintuitiiviseen bugiin missä `get(K key)` ei enään löytänytkään alkioita sen jälkeen kun `ensureCapacity(int capacity)` oli ajettu ensimmäisen kerran. Syyksi selvisi että olin epähuomiossa laittanut koodinpätkän `arraySize = capacity;` indeksien uudelleenlaskun jälkeen, minkä vuoksi alkioiden indeksit uudessa isomassa taulukossa oli laskettu vanhan eikä uuden `arraySize`n avulla. Kun `get(K key)` ajettiin seuraavan kerran, oli `arraySize` jo päivitetty minkä vuoksi `calculateIndex()`in laskema indeksi ei enään täsmännytkään alkion oikean indeksin kanssa.
- Mittaustulosten analysointi tuotti harmaita hiuksia. Luulin ensin mittausdatassani olevan virheitä koska BST oli yhtä nopea kuin Simple Container, jonka `get(K key)`-metodi on luokkaa $O(n)$. Kesti hetki vertailla kaikkien neljän datarakenteen metodeja ja testejä keskenään ennen kuin tajusin että Simple Containerin haku olikin tehty hyödyntämällä `indexOf()` ja `get(int index)`-metodeja eikä hitaammalla `get(E element)`-metodilla kuten Simple Keyed Container.

## 09-TASK
