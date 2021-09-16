package es.upm.miw.iwvg_devops.lambdas;

import org.apache.logging.log4j.LogManager;
import java.util.Optional;
import java.util.stream.Stream;

public class Searches {

    private Stream<User> filterByUserName(String name) {
        return new UsersDatabase().findAll()
                .filter(user -> name.equals(user.getName()));
    }

    private Stream<Fraction> filterByUserNameAndGetFractions(String name) {
        return filterByUserName(name)
                .flatMap(user -> user.getFractions().stream());
    }

    private Stream<User> filterById(String id) {
        return new UsersDatabase().findAll()
                .filter(user -> id.equals(user.getId()));
    }

    private Stream<Fraction> filterByIdAndGetFractions(String id) {
        return filterById(id)
                .flatMap(user -> user.getFractions()
                        .stream());
    }

    private Stream<User> findByAnyProperFraction() {
        return new UsersDatabase().findAll()
                .filter(user-> user.getFractions()
                        .stream()
                        .anyMatch(Fraction::isProper));
    }

    private Stream<User> findUserByImproperFraction(){
        return new UsersDatabase().findAll()
                .filter(user -> user.getFractions()
                        .stream()
                        .anyMatch(Fraction::isNotProper));
    }

    public Stream<String> findUserFamilyNameByUserNameDistinct(String name) {
        return filterByUserName(name)
                .map(User::getFamilyName)
                .distinct();
    }

    public Stream<Integer> findFractionNumeratorByUserFamilyName(String familyName) {
        return new UsersDatabase().findAll()
                .peek(x -> LogManager.getLogger(this.getClass()).info("before: " + x))
                .filter(user -> familyName.equals(user.getFamilyName()))
                .peek(x -> LogManager.getLogger(this.getClass()).info("after: " + x))
                .flatMap(user -> user.getFractions()
                        .stream())
                .map(Fraction::getNumerator);
    }

    public Stream<String> findUserFamilyNameByFractionDenominator(int fractionDenominator) {
        return new UsersDatabase().findAll()
                .filter(user -> user.getFractions().stream()
                        .anyMatch(fraction -> fractionDenominator == fraction.getDenominator()))
                .map(User::getFamilyName);
    }

    public Stream<String> findUserFamilyNameInitialByAnyProperFraction() {
        return this.findByAnyProperFraction()
                .map(User::getInitialFamilyName);
    }

    public Stream<String> findUserIdByAnyProperFraction() {
        return this.findByAnyProperFraction()
                .map(User::getId);
    }

    public Optional<Fraction> findFractionMultiplicationByUserFamilyName(String familyName) {
        return new UsersDatabase().findAll()
                .filter(user -> familyName.equals(user.getFamilyName()))
                .flatMap(user -> user.getFractions()
                        .stream())
                .reduce(ArithmeticUtils::multiplicate);
    }

    public Optional<Fraction> findFirstFractionDivisionByUserId(String id) {
        return filterByIdAndGetFractions(id)
                .limit(2)
                .reduce(ArithmeticUtils::divide);
    }

    public Double findFirstDecimalFractionByUserName(String name) {
        return filterByUserNameAndGetFractions(name)
                .limit(1)
                .map(Fraction::decimal)
                .findFirst()
                .orElse(Double.NaN);
    }

    public Stream<String> findUserIdByAllProperFraction() {
        return new UsersDatabase().findAll()
                .filter(user-> user.getFractions()
                        .stream()
                        .allMatch(Fraction::isProper))
                .map(User::getId);
    }

    public Stream<Double> findDecimalImproperFractionByUserName(String name) {
        return filterByUserNameAndGetFractions(name)
                .filter(Fraction::isNotProper)
                .map(Fraction::decimal);
    }

    public Optional<Fraction> findFirstProperFractionByUserId(String id) {
        return filterByIdAndGetFractions(id)
                .filter(Fraction::isProper)
                .findFirst();
    }

    public Stream<String> findUserFamilyNameByImproperFraction() {
        return findUserByImproperFraction()
                .map(User::getFamilyName);
    }

    public Optional<Fraction> findHighestFraction() {
        return new UsersDatabase().findAll()
                .flatMap(user->user.getFractions()
                        .stream())
                .max(Fraction::compareTo);
    }

    public Stream<String> findUserNameByAnyImproperFraction() {
        return findUserByImproperFraction()
                .map(User::getName);
    }

    public Stream<String> findDistinctUserFamilyNameByAllNegativeSignFraction() {
        return new UsersDatabase().findAll()
                .filter(user -> user.getFractions()
                        .stream()
                        .allMatch(Fraction::isNegative))
                .map(User::getFamilyName)
                .distinct();
    }

    public Stream<Double> findDecimalFractionByUserName(String name) {
        return filterByUserNameAndGetFractions(name)
                .map(Fraction::decimal);
    }

    public Stream<Double> findDecimalFractionByNegativeSignFraction() {
        return new UsersDatabase().findAll()
                .flatMap(user->user.getFractions()
                        .stream())
                .filter(Fraction::isNegative)
                .map(Fraction::decimal);
    }

    public Optional<Fraction> findFractionAdditionByUserId(String id) {
        return filterByIdAndGetFractions(id)
                .reduce(ArithmeticUtils::addition);
    }

    public Optional<Fraction> findFirstFractionSubtractionByUserName(String name) {
        return filterByUserNameAndGetFractions(name)
                .limit(2)
                .reduce(ArithmeticUtils::substraction);
    }
}
