package Splitwise;

import Splitwise.SplitStrategy.SplitStrategy;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class Expense {
    private final String id;
    private  final String description;
    private  final Double amount;
    private final User paidBy;
    private final List<Split> splits;
    private final LocalDateTime timeStamp;


    public Expense(ExpenseBuilder builder) {
        this.id = builder.id;
        this.description = builder.description != null ? builder.description : "";
        this.amount = builder.amount;
        this.paidBy = builder.paidBy;
        this.timeStamp = LocalDateTime.now();
        this.splits = builder.splitStrategy.calculateSplits(builder.amount, builder.paidBy, builder.participants, builder.splitValues);
    }

    public String getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public List<Split> getSplits() {
        return splits;
    }


    public static class ExpenseBuilder {

        private String id;
        private String description;
        private double amount;
        private User paidBy;
        private List<User> participants;
        private SplitStrategy splitStrategy;
        private List<Double> splitValues;

        public ExpenseBuilder setId(String id) {
            this.id = id;
            return this;
        }

        public ExpenseBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public ExpenseBuilder setAmount(double amount) {
            this.amount = amount;
            return this;
        }

        public ExpenseBuilder setPaidBy(User paidBy) {
            this.paidBy = paidBy;
            return this;
        }

        public ExpenseBuilder setParticipants(List<User> participants) {
            this.participants = participants;
            return this;
        }

        public ExpenseBuilder setSplitStrategy(SplitStrategy splitStrategy) {
            this.splitStrategy = splitStrategy;
            return this;
        }

        public ExpenseBuilder setSplitValues(List<Double> splitValues) {
            this.splitValues = splitValues;
            return this;
        }

        public Expense build() {
            // Basic validations
            if (id == null || id.isEmpty()) {
                throw new IllegalStateException("Expense ID is required");
            }
            if (paidBy == null) {
                throw new IllegalStateException("PaidBy user is required");
            }
            if (participants == null || participants.isEmpty()) {
                throw new IllegalStateException("Participants list cannot be empty");
            }
            if (amount <= 0) {
                throw new IllegalStateException("Amount must be positive");
            }
            if (splitStrategy == null) {
                throw new IllegalStateException("Split strategy is required");
            }

            // Additional validation for splitValues length matching participants for EXACT and PERCENTAGE
            if (splitStrategy.requiresSplitValues() && (splitValues == null || splitValues.size() != participants.size())) {
                throw new IllegalStateException("Split values must be provided and match participants count");
            }

            return new Expense(this);
        }

    }

}
