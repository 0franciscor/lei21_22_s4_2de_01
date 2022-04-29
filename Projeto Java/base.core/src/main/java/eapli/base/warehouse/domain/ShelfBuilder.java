package eapli.base.warehouse.domain;

public class ShelfBuilder {

    private Shelf shelf;

    private Aisle aisle;
    private boolean isEmpty;

    public ShelfBuilder(final Aisle aisle, final boolean isEmpty) {
        this.aisle = aisle;
        this.isEmpty = isEmpty;
    }

    private Shelf buildShelf(){
        if(shelf != null)
            return shelf;
        this.shelf = new Shelf(aisle, isEmpty);

        return shelf;
    }

    public Shelf build(){
        final Shelf returnShelf = buildShelf();
        return returnShelf;
    }
}
