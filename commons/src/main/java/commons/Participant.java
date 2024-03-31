package commons;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.google.gson.annotations.Expose;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "participants")
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "participant_id")
    private UUID id;

    @Expose
    private String name;

    @Expose
    private String email;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    @JsonBackReference ("event-participants")
    private Event event; //event part of field does not actually work

    @OneToMany(mappedBy = "participant", orphanRemoval = true)
    @JsonManagedReference ("participant-debts")
    @Expose
    private List<Debt> debts;

    @OneToMany(mappedBy = "paidBy", orphanRemoval = true)
    @JsonIgnore
    @Expose
    private List<Expense> paidFor;

    @Expose
    private String iban;

    @Expose
    private String bic;

    public Participant() {
        this.paidFor = new ArrayList<>();
    }

    public Participant(String name, Event event, String iban, String email, String bic) {
        this();
        this.name = name;
        this.event = event;
        this.iban = iban;
        this.email = email;
        this.bic = bic;
    }

    public String getIban() {
        return iban;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Participant that = (Participant) o;
        System.out.println(Objects.equals(id, that.id));
        System.out.println(Objects.equals(name, that.name));
        System.out.println(Objects.equals(iban, that.iban));
        System.out.println(Objects.equals(event, that.event));
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(iban, that.iban) && Objects.equals(event, that.event);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, iban, event);
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public Participant(String name) {
        this();
        this.name = name;
    }

    public Participant(String name, Event event) {
        this();
        this.name = name;
        this.event = event;
    }


    public void setEventPartOf(Event event) {
        this.event = event;
    }

    public List<Debt> getDebts() {
        return debts;
    }

    public void setDebts(List<Debt> debts) {
        this.debts = debts;
    }



    public UUID getId() {
        return id;
    }

    public void payFor(Expense e) {
        this.paidFor.add(e);
    }

    public List<Expense> getPaidFor() {
        return paidFor;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Event getEvent() {
        return event;
    }



    @Override
    public String toString() {
        return "Participant{"
                +"id=" + id
                +", name='" + name + '\''
                +", iban='" + iban + '\''
                +", bic='" + bic + '\''
                +", email='" + email + '\''
                +'}';
    }
}