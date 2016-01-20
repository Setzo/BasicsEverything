(defrule wybierz_liczby
	(liczba ?x&:(and(>= ?x 3)(<= ?x 5)))
	=>
		(printout t "Liczba " ?x "nalezy do wybranego przedzialu." crlf))

(deffacts liczby
	(liczba -2)
	(liczba 1.5)
	(liczba 12)
	(liczba 3.7)
	(liczba 5)
	(liczba 5.1)
	(liczba 4)
	(liczba 3)
	(liczba 8))

(defrule nieparzyste
	(liczba ?x&:(and (numberp ?x)(oddp ?x)))
	=>
		(printout t "Liczba " ?x " jest liczba nieparzysta" crlf))
