package es.upm.miw.iwvg_devops.lambdas;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchesTest {

    @Test
    void testFindUserFamilyNameByUserNameDistinct() {
        assertEquals(List.of("Torres"), new Searches().findUserFamilyNameByUserNameDistinct("Paula")
                .collect(Collectors.toList()));
    }

    @Test
    void testFindFractionNumeratorByUserFamilyName() {
        assertEquals(List.of(2, 4, 0, 1, 1), new Searches().findFractionNumeratorByUserFamilyName("Torres")
                .collect(Collectors.toList()));
    }

    @Test
    void testFindFamilyNameByFractionDenominator() {
        assertEquals(List.of("Lopez", "Torres"), new Searches().findUserFamilyNameByFractionDenominator(2)
                .collect(Collectors.toList()));
    }

    @Test
    void testFindUserFamilyNameInitialByAnyProperFraction() {
        assertEquals(List.of("F","B","L","B"),new Searches().findUserFamilyNameInitialByAnyProperFraction()
                .collect(Collectors.toList()));
    }

    @Test
    void testFindUserIdByAnyProperFraction() {
        assertEquals(List.of("1","2","3","5"), new Searches().findUserIdByAnyProperFraction()
                .collect(Collectors.toList()));
    }

    @Test
    void testFindFractionMultiplicationByUserFamilyName() {
        assertEquals(Optional.of(new Fraction(12, -240)), new Searches().findFractionMultiplicationByUserFamilyName("Lopez"));
    }

    @Test
    void testFindFirstFractionDivisionByUserId() {
        assertEquals(Optional.of(new Fraction(10,-1)),new Searches().findFirstFractionDivisionByUserId("2"));
    }

    @Test
    void testFindFirstDecimalFractionByUserName() {
        assertEquals(2.0,new Searches().findFirstDecimalFractionByUserName("Ana"));
    }

    @Test
    void testFindUserIdByAllProperFraction() {
        assertEquals(List.of(),new Searches().findUserIdByAllProperFraction()
                .collect(Collectors.toList()));
    }

    @Test
    void testFindDecimalImproperFractionByUserName() {
        assertEquals(List.of(Double.POSITIVE_INFINITY),new Searches().findDecimalImproperFractionByUserName("Paula")
                .collect(Collectors.toList()));
    }

    @Test
    void testFindFirstProperFractionByUserId() {
        assertEquals(Optional.of(new Fraction(-1,5)),new Searches().findFirstProperFractionByUserId("2"));
    }

    @Test
    void testFindUserFamilyNameByImproperFraction() {
        assertEquals(List.of("Fernandez","Blanco","Lopez","Blanco","Torres"),new Searches().findUserFamilyNameByImproperFraction()
                .collect(Collectors.toList()));
    }

    @Test
    void testFindHighestFraction() {
        assertEquals(Optional.of(new Fraction(0,0)),new Searches().findHighestFraction());
    }

    @Test
    void testFindUserNameByAnyImproperFraction() {
        assertEquals(List.of("Oscar","Ana","Oscar","Antonio","Paula"),new Searches().findUserNameByAnyImproperFraction()
                .collect(Collectors.toList()));
    }

    @Test
    void testFindDistinctUserFamilyNameByAllNegativeSignFraction() {
        assertEquals(List.of(),new Searches().findDistinctUserFamilyNameByAllNegativeSignFraction()
                .collect(Collectors.toList()));
    }

    @Test
    void testFindDecimalFractionByUserName() {
        assertEquals(List.of(1.0,1.0,Double.NaN, Double.POSITIVE_INFINITY,1.0),new Searches().findDecimalFractionByUserName("Paula")
                .collect(Collectors.toList()));
    }

    @Test
    void testFindDecimalFractionByNegativeSignFraction() {
        assertEquals(List.of(-0.2,-0.5),new Searches().findDecimalFractionByNegativeSignFraction()
                .collect(Collectors.toList()));
    }

    @Test
    void testFindFractionAdditionByUserId() {
        assertEquals(Optional.of(new Fraction(72,60)),new Searches().findFractionAdditionByUserId("3"));
    }

    @Test
    void testFindFirstFractionSubtractionByUserName() {
        assertEquals(Optional.of(new Fraction(0,4)),new Searches().findFirstFractionSubtractionByUserName("Paula"));
    }
}
