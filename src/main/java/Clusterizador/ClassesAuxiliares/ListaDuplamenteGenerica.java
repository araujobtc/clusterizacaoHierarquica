package Clusterizador.ClassesAuxiliares;

public class ListaDuplamenteGenerica<T extends Comparable<T>> {
  // coloquei isso de extends pq o equals() tava dando erro por causa disso

  protected Elo prim;
  private int tam;

  protected class Elo {
    protected T dado;
    protected Elo ant;
    protected Elo prox;

    public Elo() {
      ant = null;
      prox = null;
    }

    public Elo(T elem) {
      dado = elem;
      ant = null;
      prox = null;
    }

    public Elo(T elem, Elo antElem, Elo proxElem) {
      dado = elem;
      ant = antElem;
      prox = proxElem;
    }
  }

  public ListaDuplamenteGenerica() // O(1)
  {
    prim = null;
  }

  public boolean vazia() // O(1)
  {
    return prim == null;
  }

  public void insere(T novo) // O(1)
  {
    Elo p;

    p = new Elo(novo);

    p.prox = prim;

    p.ant = null;

    if (prim != null)
      prim.ant = p;

    prim = p;
    setTam(1);
  }

  private Elo busca(T elem) // O(n)
  {
    Elo p = null;

    for (p = prim; ((p != null) && (!p.dado.equals(elem))); p = p.prox)
      ; // O(n)

    return p;
  }

  public T busca(int index) // O(n)
  {
    Elo p = null;
    int i = 0;

    for (p = prim; ((p != null) && (i != index)); p = p.prox, i++)
      ; // O(n)

    return p.dado;
  }

  public boolean remove(T elem) // O(n)
  {
    Elo p = null;
    p = busca(elem); // O(n)

    if (p == null)
      return false;

    if (p == prim)
      prim = prim.prox;
    else
      p.ant.prox = p.prox;

    if (p.prox != null)
      p.prox.ant = p.ant;

    p = null;

    setTam(-1);
    return true;
  }

  public boolean remove() // O(1)
  {
    Elo p = this.prim;

    if (p == null)
      return false;

    prim = prim.prox;
    prim.ant = null;
    p.prox = null;

    p = null;

    setTam(-1);
    return true;
  }

  public void imprime() // O(n)
  {
    Elo p;

    System.out.println("Elementos da lista:");

    for (p = prim; p != null; p = p.prox) // O(n)
    {
      System.out.println(p.dado.toString() + " ");
    }

    System.out.println();
  }

  private void setTam(int i) // O(1)
  {
    this.tam = tam + i;
  }

  public int getTam() // O(1)
  {
    return tam;
  }

  public T getDadoPrim() {
    return prim.dado;
  }
}
