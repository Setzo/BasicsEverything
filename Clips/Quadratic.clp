(defrule poczatek
	?mx <- (initial-fact)
	=>
		(retract ?mx)
	
		(printout t "Program rozwiazujacy rownania kwadratowe" crlf)
	
		(printout t "Podaj wspolczynnik a" crlf)
		(bind ?a (read))
		(printout t "Podaj wspolczynnik b" crlf)
		(bind ?b (read))
		(printout t "Podaj wspolczynnik c" crlf)
		(bind ?c (read))
	
		(assert (a ?a)(b ?b)(c ?c)))

(defrule delta
	(a ?a)
	(b ?b)
	(c ?c)
	=>
		(bind ?delta (-(* ?b ?b)(* 4 ?a ?c)))
		(assert (delta ?delta)))

(defrule brakpierw
	(delta ?delta&:(< ?delta 0))
	=>
		(printout t "Brak pierwiastkow rzeczywistych." crlf))

(defrule jedenpierw
	(delta 0)(b ?b)(a ?a)
	=>
		(bind ?x1 (/(* ?b -1)(* 2 ?a)))
		(assert (x1 ?x1)(x2 ?x1)))

(defrule wypisz
	(x1 ?x1)
	(x2 ?x2)
	=>
		(printout t " x1: " ?x1 ", x2: " ?x2 crlf))

(defrule dwapierw
	(a ?a) (b ?b) (delta ?delta)
	(delta ?delta&:(> ?delta 0))
	=>
		(bind ?x1 (/ (-(* ?b -1)(sqrt ?delta) )(* 2 ?a)))
		(bind ?x2 (/ (+(* ?b -1)(sqrt ?delta) )(* 2 ?a)))
		(assert (x1 ?x1)(x2 ?x2)))
