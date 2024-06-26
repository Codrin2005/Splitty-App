package server.services;

import commons.Expense;
import commons.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.database.ExpenseRepository;

import java.util.List;
import java.util.UUID;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }
    public Expense addExpense(Expense expense) {
        return expenseRepository.saveAndFlush(expense);
    }
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }
    public Expense getExpenseById(UUID id) {
        return expenseRepository.findById(id).orElse(null);
    }
    public Expense deleteExpense(UUID id) {
        Expense expense = expenseRepository.findById(id).orElse(null);
        if (expense != null) {
            expenseRepository.delete(expense);
        }
        return expense;
    }
    public Expense updateExpense(Expense expense) {
        System.out.println(expense);
        Expense saved = expenseRepository.saveAndFlush(expense);
        if (expenseRepository.findById(saved.getId()).isPresent()) {
            return saved;
        }
        return null;
    }

    public Expense addTag(UUID expenseId, UUID tagId) {
        Tag t = new Tag();
        t.setId(tagId);
        Expense saved = expenseRepository.findById(expenseId).orElse(null);
        if (saved == null) {
            return null;
        }
        saved.addTag(t);
        return updateExpense(saved);
    }
}
