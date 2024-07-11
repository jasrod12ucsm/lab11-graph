package list;
public interface TDAList<S> {
    boolean isEmpty();

    int search(S ele);

    int lenght();

    void insertFirst(S ele);

    void insertLast(S ele);

    void remove(S ele);

    void deleteDuplicates();

    void insertNth(S data, int position);

    void deleteNth(int position);
}
