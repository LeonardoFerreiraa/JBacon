package br.com.leonardoferreira.jbacon.lazy.factory;

import br.com.leonardoferreira.jbacon.JBacon;
import br.com.leonardoferreira.jbacon.Lazy;
import br.com.leonardoferreira.jbacon.annotation.JBaconTemplate;
import br.com.leonardoferreira.jbacon.lazy.domain.Contact;
import br.com.leonardoferreira.jbacon.lazy.domain.Phone;
import com.github.javafaker.Faker;

public class PhoneFactory extends JBacon<Phone> {

    private final Faker faker;

    private final ContactFactory contactFactory;

    public PhoneFactory(final Faker faker, final ContactFactory contactFactory) {
        this.faker = faker;
        this.contactFactory = contactFactory;
    }

    @Override
    protected Phone getDefault() {
        Phone phone = new Phone();
        phone.setNumber(faker.phoneNumber().phoneNumber());
        phone.setContact(Lazy.of(Contact.class).with(contactFactory::build));
        return phone;
    }

    @Override
    protected Phone getEmpty() {
        return new Phone();
    }

    @Override
    protected void persist(final Phone phone) {
        throw new UnsupportedOperationException();
    }

    @JBaconTemplate("withoutLazy")
    protected Phone withoutLazy() {
        Phone phone = new Phone();

        phone.setNumber(faker.phoneNumber().phoneNumber());
        phone.setContact(contactFactory.build());

        return phone;
    }

}