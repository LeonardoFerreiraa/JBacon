package br.com.leonardoferreira.jbacon.lazy.factory;

import br.com.leonardoferreira.jbacon.JBacon;
import br.com.leonardoferreira.jbacon.lazy.domain.Contact;
import com.github.javafaker.Faker;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.Getter;

public class ContactFactory extends JBacon<Contact> {

    private final Faker faker;

    @Getter
    private final AtomicInteger counter;

    public ContactFactory(final Faker faker) {
        this.faker = faker;
        counter = new AtomicInteger();
    }

    @Override
    protected Contact getDefault() {
        counter.incrementAndGet();

        Contact contact = new Contact();
        contact.setName(faker.gameOfThrones().character());
        return contact;
    }

    @Override
    protected Contact getEmpty() {
        return new Contact();
    }

    @Override
    protected void persist(final Contact contact) {
        throw new UnsupportedOperationException();
    }

}