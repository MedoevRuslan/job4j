package ru.job4j.chess.figures.white;

import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */
public class RookWhite extends Figure {

    public RookWhite(Cell position) {
        super(position);
    }

    @Override
    public Cell[] way(Cell source, Cell dest) {
        if (dest.x == position.x || dest.y == position.y) {
            throw new ImpossibleMoveException("That figure can't move that way");
        }
        return new Cell[] {dest};
    }

    @Override
    public Figure copy(Cell dest) {
        return new RookWhite(dest);
    }
}
