package commons;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "tag_id")
    @Expose
    private UUID id;

    @Expose
    private String tag;

    @Expose
    private String color;

    @ManyToOne (fetch = FetchType.EAGER)
    @JsonBackReference("event-tags")
    @JoinColumn (name = "event_id")
    private Event event;

    @ManyToMany(mappedBy = "tags")
    @JsonIgnore
    private List<Expense> expenses;

    public Tag() {
    }

    public Tag(String tag, Event event) {
        this();
        this.tag = tag;
        this.event = event;
        this.expenses = new ArrayList<>();
    }

    public Tag(String tag, String color, Event e) {
        this(tag, e);
        this.color = color;
    }

    public Tag(String tagName) {
        this.tag = tagName;
    }

    public String getTag() {
        return tag;
    }

    public UUID getId() {
        return id;
    }

    public void addExpense(Expense e) {
        expenses.add(e);
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Tag tag1 = (Tag) o;
        return Objects.equals(tag, tag1.getTag());
    }

    @Override
    public int hashCode() {
        return Objects.hash(tag);
    }
}
