# Raportit tehtävistä

Kirjaa tähän tiedostoon **jokaiseen** tehtävään liittyvät omat raporttisi ja analyysisi. Muista että raportti on myös kurssilla **arvosteltava tehtävä**.

Voit sisällyttää raporttiin tekstimuotoisia taulukoita (tasaukset välilyönnein):

```
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

| n	| Fill	| Search	| Total |
|-----|--------|--------|-------|
| 500	 | 7	| 700	| 707 |
| 1000 |	9	| 288	| 297 | 

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

## 04-TASK

## 05-TASK

## 06-TASK

## 07-TASK

## 08-TASK

## 09-TASK