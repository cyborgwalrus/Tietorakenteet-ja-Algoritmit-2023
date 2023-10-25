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

## 03-TASK

## 04-TASK

## 05-TASK

## 06-TASK

## 07-TASK

## 08-TASK

## 09-TASK