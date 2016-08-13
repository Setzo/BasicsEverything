(deffacts tydzien
	(dzien poniedzialek 1)
	(dzien wtorek 2)
	(dzien sroda 3)
	(dzien czwartek 4)
	(dzien piatek 5)
	(dzien sobota 6)
	(dzien niedziela 7))

(defrule pytanie
	?mx<-(initial-fact)
	=>
		(retract ?mx)
		(printout t "Podaj dzien tygodnia: " crlf)
		(bind ?dt(read))
		(assert (dzis ?dt)))

(defrule oblicz
	(dzis ?nazwa)
	(dzien ?nazwa ?liczba)
	=>
		(bind ?wynik(- 7 ?liczba))
		(printout t "Do niedzieli zostalo " ?wynik " dni." crlf))

(defrule weekend
	(or (dzis sobota)
		(dzis niedziela))
	=>
		(printout t "Weekend" crlf))

(defrule weekend2
	(dzis sobota|niedziela)
	=>
		(printout t "Weekend" crlf))

(defrule weekend3
	(dzis ?dzien&sobota|niedziela)
	=>
		(printout t "A w ogóle to dzis " ?dzien "!" crlf))

(defrule biblioteka
	(dzis ~poniedzialek)
	=>
		(printout t "Biblioteka nieczynna w poniedzialki." crlf))
