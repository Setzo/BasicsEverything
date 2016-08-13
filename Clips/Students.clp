(deftemplate student
	(slot imie(default ?DERIVE))
	(slot nazwisko(default ?DERIVE))
	(slot wiek(range 18 50)(default 19))
	(multislot adres)
	(slot kierunek)
	(slot srednia(default 2.0)))

(deffacts studenci
		(student (imie Mike)(nazwisko Micky)(kierunek informatyka)(srednia 2.0))
		(student (imie Rick)(srednia 3.9)(adres Polna 13))
		(student (imie Joan)(nazwisko Joannie)(srednia 5.1)(kierunek informatyka))
		(student (imie Ryan)(wiek 22)(kierunek nawigacja)(srednia 5.0)))

(defrule srednia
	(podane ?kier)
	(student (imie ?i)(srednia ?x&:(>= ?x 4.0))(kierunek ?kier))
	=>
		(printout t "Student " ?i " ma srednia powyzej 4.0" crlf))

(defrule pytanie
	?mx<-(initial-fact)
	=>
		(retract ?mx)
		(printout t "Podaj kierunek: " crlf)
		(bind ?dt(read))
		(assert (podane ?dt)))
