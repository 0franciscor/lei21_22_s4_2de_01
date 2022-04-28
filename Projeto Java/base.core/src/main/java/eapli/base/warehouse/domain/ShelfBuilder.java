package eapli.base.warehouse.domain;

public class ShelfBuilder {

    private Shelf shelf;

    private boolean isEmpty;

    public ShelfBuilder(final boolean isEmpty){
        this.shelf = new Shelf();
        withEmpty(isEmpty);
    }

    private ShelfBuilder withEmpty(boolean isEmpty){
        this.isEmpty = isEmpty;
        return this;
    }

    private Shelf buildShelf(){
        if(shelf != null)
            return shelf;
        this.shelf = new Shelf(isEmpty);

        return shelf;
    }

    public Shelf build(){
        final Shelf returnShelf = buildShelf();
        return returnShelf;
    }
}
