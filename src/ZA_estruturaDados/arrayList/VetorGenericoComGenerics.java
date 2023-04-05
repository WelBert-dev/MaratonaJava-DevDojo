package ZA_estruturaDados.arrayList;

import java.lang.reflect.Array;

public class VetorGenericoComGenerics<T> {
    private T[] elements;
    private int size;

    public VetorGenericoComGenerics() {
        this(10);
    }

    public VetorGenericoComGenerics(int capacity) {
        this.elements = (T[]) new Object[capacity]; // mais elegante
        this.size = 0;
    }

    public VetorGenericoComGenerics(int capacity, Class<T> classType) {
        this.elements = (T[]) Array.newInstance(classType, capacity); // menos elegante
        this.size = 0;
    }

    public T[] getAll() {
        return this.elements;
    }

//    public void add(T element){
//        for(int i=0; i<elements.length; i++){
//            if(this.elements[i] == null){
//                this.elements[i] = element;
//                break;
//            }
//        }
//    }

//    public void add(T element) throws Exception {
//        if(this.size < elements.length){
//            this.elements[this.size] = element;
//            this.size++;
//        } else {
//            throw new Exception("Vetor esta cheio, não é possível adicionar mais elementos!");
//        }
//    }

    public boolean add(T element) {

        // Duplica a capacidade do array (caso size ultrapasse o length definido inicialmente)
        // Tornando a capacidade do array dinâmica
        this.multiplyCapacity();

        if (this.size < elements.length) {
            this.elements[this.size] = element;
            this.size++;
            return true;
        }

        return false;
    }


    // 0 1 2 3 4 5 6 (size é 5)
    // B C E F G + +
    public boolean add(T element, int index) {
        if (!(index >= 0 && index < this.size)) {
            throw new IllegalArgumentException("Posição fora do range possível");
        }

        // Duplica a capacidade do array (caso size ultrapasse o length definido inicialmente)
        // Tornando a capacidade do array dinâmica
        this.multiplyCapacity();

        // move os elementos apartir do fim, +1 (esquerda) casa até chegar no index passado
        for (int i = this.size-1; i >= index; i--) {
            this.elements[i+1] = this.elements[i];
        }

        // após liberado a casa/index, insere o elemento
        this.elements[index] = element;

        this.size ++; // size real agora aumenta +1 (trabalhando com tamanho real, não com o definido na criação do array

        return true;
    }

    // 0 1 2 3 4 5 6 7 8 9 (size é 7)
    // A B C D E F G + + +

    public boolean removeElementByIndex(int index) {

        if (!(index >= 0 && index < this.size)) {
            throw new IllegalArgumentException("Posição fora do range possível");
        }

        for (int i = index; i<this.size-1; i++) {
            // começa no index que será removido, e atribuí o próximo elemento nele (um a um)

            this.elements[i] = this.elements[i+1];
        }

        // Removeu 1 elemento, logo o tamanho real abaixou (pois sempre trabalhamos com o tamanho real ao invés do tamanho definido na criação do array)
        this.size = this.size - 1;

        return true;
    }

    public boolean removeElementByElement(T element) {

        int index = this.indexOf(element);

        if (index >= 0) {
            for (int i = index; i<this.size-1; i++) {
                // começa no index que será removido, e atribuí o próximo elemento nele (um a um)

                this.elements[i] = this.elements[i+1];
            }
            // Removeu 1 elemento, logo o tamanho real abaixou (pois sempre trabalhamos com o tamanho real ao invés do tamanho definido na criação do array)
            this.size = this.size - 1;

            return true;
        }

        return false;
    }

    public T getElement(int indexOf) {
        if (!(indexOf >= 0 && indexOf < this.size)) {
            throw new IllegalArgumentException("Posição fora do range possível");
        }

        return this.elements[indexOf];
    }

    public int indexOf(T element) {

        for (int i = 0; i < this.size; i++) {// trabalhando com o tamanho real do vetor, não com o tamanho definido pelo user
            if (this.elements[i].equals(element)) {
                return i;
            }
        }

        return -1;
    }

    public int lastIndexOf(T element) {

        for (int i = this.size-1; i >= 0; i--) {// trabalhando com o tamanho real do vetor, não com o tamanho definido pelo user
            if (this.elements[i].equals(element)) {
                return i;
            }
        }

        return -1;
    }

    public void clear() {
        // opção 1
        //this.elements = (T[]) new Object[this.elements.length];
        // opção 2
        //this.size = 0;
        // opção 3
        for (int i=0; i<this.size; i++) {
            this.elements[i] = null;
        }
        this.size = 0;

    }

    public int getSize() {
        return this.size;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("[");

        for (int i = 0; i < this.size-1; i++) {
            s.append(this.elements[i]);
            s.append(", ");
        }

        if (this.size > 0) { // evita exeção
            s.append(this.elements[this.size-1]);
        }

        s.append("]");

        return s.toString();
    }

    private void multiplyCapacity() {
        if (this.size == this.elements.length) {
            T[] elementsNew = (T[]) new Object[this.elements.length * 2];

            for (int i = 0; i<this.elements.length; i++) {
                elementsNew[i] = this.elements[i];
            }

            this.elements = elementsNew;
        }
    }
}