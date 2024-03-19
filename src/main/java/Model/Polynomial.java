package Model;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class Polynomial implements Comparable<Polynomial> {

    private HashMap<Integer, Double> coefficient;

    public Polynomial() {
        coefficient = new HashMap<>();
    }

    public Polynomial conversion(String polynomialString) {

        Polynomial result = new Polynomial();
        String[] terms = polynomialString.split("(?=\\+|\\-)");

        for (String term : terms) {
            double coefficient;
            int exponent;

            String[] parts = term.split("x\\^?");

            if (parts.length == 2) {

                coefficient = parseCoefficient(parts[0]);
                exponent = Integer.parseInt(parts[1]);
            } else if (term.contains("x")) {

                coefficient = parseCoefficient(parts[0]);
                exponent = 1;
            } else {

                coefficient = parseCoefficient(term);
                exponent = 0;
            }

            result.getCoefficient().put(exponent, coefficient);
        }

        return result;
    }

    private double parseCoefficient(String term) {
        if (term.equals("+")) {
            return 1;
        } else if (term.equals("-")) {
            return -1;
        } else if (term.isEmpty()) {
            return 0;
        } else {
            return Double.parseDouble(term);
        }
    }

    public Polynomial addition(Polynomial p) {

        Polynomial result = new Polynomial();
        for (int exponent : coefficient.keySet()) {
            double firstCoefficient = coefficient.get(exponent);
            result.coefficient.put(exponent, firstCoefficient);
        }

        for (int exponent : p.coefficient.keySet())
            if (result.coefficient.containsKey(exponent)) {
                double finalCoefficient = result.coefficient.get(exponent) + p.coefficient.get(exponent);
                result.coefficient.put(exponent, finalCoefficient);
            } else {
                double secondCoefficient = p.coefficient.get(exponent);
                result.coefficient.put(exponent, secondCoefficient);
            }

        return result;

    }

    public Polynomial subtraction(Polynomial p) {

        Polynomial result = new Polynomial();
        for (int exponent : coefficient.keySet()) {
            double firstCoefficient = coefficient.get(exponent);
            result.coefficient.put(exponent, firstCoefficient);
        }

        for (int exponent : p.coefficient.keySet())
            if (result.coefficient.containsKey(exponent)) {
                double finalCoefficient = result.coefficient.get(exponent) - p.coefficient.get(exponent);
                result.coefficient.put(exponent, finalCoefficient);
            } else {
                double secondCoefficient = p.coefficient.get(exponent);
                result.coefficient.put(exponent, -secondCoefficient);
            }

        return result;

    }

    public Polynomial derivative() {
        Polynomial result = new Polynomial();

        for (int exponent : coefficient.keySet()) {
            double oldCoefficient = coefficient.get(exponent);
            if (exponent == 0) {
                continue;
            }

            double newCoefficient = oldCoefficient * exponent;
            result.coefficient.put(exponent - 1, newCoefficient);
        }

        return result;
    }


    public Polynomial integration() {
        Polynomial result = new Polynomial();

        for (int exponent : coefficient.keySet()) {
            double oldCoefficient = coefficient.get(exponent);
            int newExponent = exponent + 1;
            double newCoefficient = oldCoefficient / newExponent;
            result.coefficient.put(newExponent, newCoefficient);
        }

        return result;
    }


    public Polynomial multiplication(Polynomial p) {
        Polynomial result = new Polynomial();

        for (Map.Entry<Integer, Double> entry1 : coefficient.entrySet()) {
            int exponent1 = entry1.getKey();
            double coeff1 = entry1.getValue();

            for (Map.Entry<Integer, Double> entry2 : p.getCoefficient().entrySet()) {
                int exponent2 = entry2.getKey();
                double coeff2 = entry2.getValue();

                int newExponent = exponent1 + exponent2;
                double newCoefficient = coeff1 * coeff2;

                result.coefficient.put(newExponent, result.coefficient.getOrDefault(newExponent, 0d) + newCoefficient);
            }
        }

        return result;
    }


    public DivisionResult division(Polynomial divisor) {
        Polynomial dividend = this;
        Polynomial quotient = new Polynomial();
        Polynomial remainder = dividend;

        dividend = orderMonomials(dividend);
        divisor = orderMonomials(divisor);

        while (remainder.compareTo(divisor) >= 0) {

            int dividendDegree = remainder.getDegree();
            int divisorDegree = divisor.getDegree();
            int quotientDegree = dividendDegree - divisorDegree;


            double quotientCoefficient = remainder.coefficient.get(dividendDegree) / divisor.coefficient.get(divisorDegree);
            quotient.coefficient.put(quotientDegree, quotientCoefficient);

            Polynomial product = quotient.multiplication(divisor);

            remainder = remainder.subtraction(product);


            remainder = removeZeroCoefficients(remainder);

            remainder = orderMonomials(remainder);
        }

        DivisionResult result = new DivisionResult(quotient, remainder);
        return result;
    }


    private Polynomial removeZeroCoefficients(Polynomial polynomial) {
        Polynomial result = new Polynomial();
        for (int exponent : polynomial.getCoefficient().keySet()) {
            double coeff = polynomial.getCoefficient().get(exponent);
            if (coeff != 0) {
                result.getCoefficient().put(exponent, coeff);
            }
        }
        return result;
    }

    private Polynomial orderMonomials(Polynomial polynomial) {
        List<Map.Entry<Integer, Double>> sortedEntries = new ArrayList<>(polynomial.getCoefficient().entrySet());
        sortedEntries.sort(Map.Entry.comparingByKey(Comparator.reverseOrder()));

        Polynomial orderedPolynomial = new Polynomial();
        for (Map.Entry<Integer, Double> entry : sortedEntries) {
            orderedPolynomial.getCoefficient().put(entry.getKey(), entry.getValue());
        }
        return orderedPolynomial;
    }


    public String toString() {
        StringBuilder polynomialString = new StringBuilder();
        boolean isFirstTerm = true;

        List<Integer> exponents = new ArrayList<>(coefficient.keySet());
        exponents.sort(Comparator.reverseOrder());

        for (int exponent : exponents) {
            double coeff = coefficient.get(exponent);
            if (coeff != 0) {
                if (!isFirstTerm) {
                    polynomialString.append(coeff > 0 ? " + " : " - ");
                    coeff = Math.abs(coeff);
                } else {
                    isFirstTerm = false;
                }

                if (exponent == 0) {
                    polynomialString.append(coeff);
                } else {
                    if (coeff != 1) {
                        polynomialString.append(coeff);
                    }
                    polynomialString.append("x");

                    if (exponent != 1) {
                        polynomialString.append("^").append(exponent);
                    }
                }
            }
        }

        if (polynomialString.isEmpty()) {
            return "0";
        }
        return polynomialString.toString();
    }


    public void setCoefficient(int exponent, double coefficient) {
        this.coefficient.put(exponent, coefficient);
    }

    public HashMap<Integer, Double> getCoefficient() {
        return coefficient;
    }

    public int compareTo(Polynomial other) {
        int thisDegree = this.getDegree();
        int otherDegree = other.getDegree();
        return Integer.compare(thisDegree, otherDegree);
    }

    private int getDegree() {
        int maxDegree = 0;
        for (int degree : coefficient.keySet()) {
            if (degree > maxDegree) {
                maxDegree = degree;
            }
        }
        return maxDegree;
    }
}