public class Poli {

    public static void main(String[] args) {


        Plant plant1 = new Plant();
        Tree tree = new Tree();

        Plant plant2 = tree;            //Typ zmiennej Plant, wskazuje na typ Tree, zatem używa tylko metod od Tree

        plant2.grow();
        tree.shedLeaves();

        doGrow(tree);                    //może używać swoich metod (lub override) i metod nadklas
    }

    public static void doGrow(Plant plant) {        //Poli jest użyteczne do wczytywania 1 Typu klas do metody, która wykona inne czynności
        plant.grow();                                //w zależności od referencji do zadeklarowanego typu zmiennej.
        plant.hello();                                //np. klasy typu Tree i Plant pasuja do wywolania metody doGrow(Plant args)
    }                                                //, bo Tree to podklasa Plant, zatem wchodzi w scope doGrow(Plant args)
}                                                    //funkcja grow zostanie pobrana z klasy Tree, bo referencja jest na typ Tree, zatem nastąpi Override
