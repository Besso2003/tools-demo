package App;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import ejbs.Calculation;

@Stateless
public class CalculationService {

    @PersistenceContext(unitName="hello")
    private EntityManager entityManager;

    public int performCalculation(int number1, int number2, String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = number1 + number2;
                break;
            case "-":
                result = number1 - number2;
                break;
            case "*":
                result = number1 * number2;
                break;
            case "/":
                result = number1 / number2;
                break;
        }
        return result;
    }

    public void saveCalculation(Calculation calculation) {
        entityManager.persist(calculation);
    }

    public List<Calculation> getAllCalculations() {
        TypedQuery<Calculation> query = entityManager.createQuery("SELECT c FROM Calculation c", Calculation.class);
        return query.getResultList();
    }
}