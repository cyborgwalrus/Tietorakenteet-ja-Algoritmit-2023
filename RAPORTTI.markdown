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

#### Algoritmit

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

#### BST vs Simple Container

### Valinnaiset tehtävät

- Tein `BSTAnalyzerVisitor`

### Muista mietteitä

## 08-TASK

## 09-TASK
