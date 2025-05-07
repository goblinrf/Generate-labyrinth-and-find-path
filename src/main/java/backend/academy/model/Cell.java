package backend.academy.model;

import backend.academy.enums.State;
import backend.academy.enums.SurfaceType;
import backend.academy.enums.Type;

public record Cell(int row, int col, Type type, State state, SurfaceType surfaceType) {

    public Cell(int row, int col, Type type) {
        this(row, col, type, State.EXTERNAL, SurfaceType.NORMAL);
    }

    public Cell withSurfaceType(SurfaceType newSurfaceType) {
        return new Cell(this.row, this.col, this.type, this.state, newSurfaceType);
    }
}
