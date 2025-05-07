package backend.academy.service.impl.generator.helper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UnionFindTest {
    private UnionFind unionFind;

    @BeforeEach
    void setUp() {
        unionFind = new UnionFind(10);
    }

    @Test
    @DisplayName("Given an initialized UnionFind, when find is called, then it should return the correct root")
    void givenInitializedUnionFind_whenFindCalled_thenReturnsCorrectRoot() {
        // when
        unionFind.union(1, 2);

        // then
        assertEquals(unionFind.find(1), unionFind.find(2), "Элементы 1 и 2 должны иметь одинаковый корень.");
    }

    @Test
    @DisplayName("Given two disjoint sets, when union is called, then they should be connected")
    void givenDisjointSets_whenUnionCalled_thenShouldConnectSets() {
        // when
        unionFind.union(3, 4);

        // then
        assertEquals(unionFind.find(3), unionFind.find(4), "Элементы 3 и 4 должны иметь одинаковый корень.");
    }

    @Test
    @DisplayName("Given a union of two elements, when find is called on one, then it should return the same root for the other")
    void givenUnionedElements_whenFindCalled_thenShouldReturnSameRoot() {
        // then
        unionFind.union(5, 6);

        // when
        assertEquals(unionFind.find(5), unionFind.find(6), "Элементы 5 и 6 должны иметь одинаковый корень.");
    }

    @Test
    @DisplayName("Given a union of multiple elements, when find is called, then all should return the same root")
    void givenUnionOfMultipleElements_whenFindCalled_thenAllShouldReturnSameRoot() {
        // when
        unionFind.union(7, 8);
        unionFind.union(8, 9);

        // then
        assertEquals(unionFind.find(7), unionFind.find(8), "Элементы 7 и 8 должны иметь одинаковый корень.");
        assertEquals(unionFind.find(8), unionFind.find(9), "Элементы 8 и 9 должны иметь одинаковый корень.");
        assertEquals(unionFind.find(7), unionFind.find(9), "Элементы 7 и 9 должны иметь одинаковый корень.");
    }

    @Test
    @DisplayName("Given a union operation, when find is called on an element not involved in the union, then it should return its own root")
    void givenUnionOperation_whenFindCalledOnNonUnionedElement_thenShouldReturnItsOwnRoot() {
        // when
        unionFind.union(0, 1);

        // then
        assertNotEquals(unionFind.find(2), unionFind.find(0), "Элемент 2 не должен быть объединен с 0.");
    }
}
