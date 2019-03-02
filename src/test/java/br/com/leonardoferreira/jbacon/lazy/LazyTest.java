package br.com.leonardoferreira.jbacon.lazy;

import br.com.leonardoferreira.jbacon.lazy.domain.Contact;
import br.com.leonardoferreira.jbacon.lazy.domain.Phone;
import br.com.leonardoferreira.jbacon.lazy.factory.ContactFactory;
import br.com.leonardoferreira.jbacon.lazy.factory.PhoneFactory;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LazyTest {

    private ContactFactory contactFactory;

    private PhoneFactory phoneFactory;

    @BeforeEach
    public void setUp() {
        Faker faker = new Faker();
        contactFactory = new ContactFactory(faker);
        phoneFactory = new PhoneFactory(faker, contactFactory);
    }

    @Test
    public void callDependencyBuildOnce() {
        Contact contact = contactFactory.build();

        Phone phone = phoneFactory.build(empty -> {
            empty.setContact(contact);
        });

        Assertions.assertEquals(contact, phone.getContact());
        Assertions.assertEquals(1, contactFactory.getCounter().get());
    }

    @Test
    public void callDependencyBuildTwice() {
        Contact contact = contactFactory.build();

        Phone phone = phoneFactory.build("withoutLazy", empty -> {
            empty.setContact(contact);
        });

        Assertions.assertEquals(contact, phone.getContact());
        Assertions.assertEquals(2, contactFactory.getCounter().get());
    }

    @Test
    public void lazyWithoutOverride() {
        Phone build = phoneFactory.build();
        Contact contact = build.getContact();
        Assertions.assertNotNull(contact.getName());
    }

}