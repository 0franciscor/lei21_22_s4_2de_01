package eapli.base.warehouse.domain;

public class ShelfBuilder {

    private Shelf shelf;

    private ShelfId Id;
    private boolean isEmpty;

    public ShelfBuilder(){}

    public ShelfBuilder withId(final ShelfId Id){
        this.Id = Id;
        return this;
    }

    public ShelfBuilder withEmpty(boolean isEmpty){
        this.isEmpty = isEmpty;
        return this;
    }

    private Shelf buildShelf(){
        if(shelf != null)
            return shelf;
        this.shelf = new Shelf(Id, isEmpty);

        return shelf;
    }

    public Shelf build(){
        final Shelf returnShelf = buildShelf();
        return returnShelf;
    }
}
